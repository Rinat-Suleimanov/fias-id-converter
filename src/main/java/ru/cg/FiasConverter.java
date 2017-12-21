package ru.cg;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import javax.sql.DataSource;

import org.jooq.Record;
import org.jooq.Result;
import org.jooq.tools.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.cg.dao.FiasDao;
import ru.cg.dao.SourceDao;
import ru.cg.model.AoIdData;
import ru.cg.model.JsonId;
import ru.cg.model.Project;
import ru.cg.utils.FiasIdUtils;

/**
 * @author Rinat Suleymanov
 */
public class FiasConverter {

  private static final Logger log = LoggerFactory.getLogger(FiasConverter.class);

  private static final String ID_COLUMN_TEMPLATE = "%s_id";
  private static final String SOURCE_TABLE_TEMPLATE = "%s.%s";

  private long count;
  private AtomicLong complete;
  private AtomicLong wrong;

  private String idColumn;
  private String aoIdColumn;
  private String fiasIdColumn;
  private String sourceTable;
  private Project project;

  private SourceDao sourceDao;
  private FiasDao fiasDao;

  private StopWatch stopWatch = new StopWatch();


  public FiasConverter(Project project, DataSource projectDataSource, DataSource fiasDataSource) {
    initNames(project);
    this.project = project;
    this.sourceDao = new SourceDao(projectDataSource);
    this.fiasDao = new FiasDao(fiasDataSource);

    this.count = sourceDao.countRecordsToConvert(sourceTable, aoIdColumn, fiasIdColumn);
    this.complete = new AtomicLong();
    this.wrong = new AtomicLong();
  }

  private void initNames(Project project) {
    this.idColumn = String.format(ID_COLUMN_TEMPLATE, project.table());
    this.aoIdColumn = project.aoId();
    this.fiasIdColumn = project.fiasId();
    this.sourceTable = String.format(SOURCE_TABLE_TEMPLATE, project.schema(), project.table());
  }

  public void convert() {

    stopWatch.splitInfo("Project " + project.toString() + ": receiving ao_id completed, ao_id size: " + count);
    stopWatch.splitInfo("Project " + project.toString() + ": start converting");

    while (count > complete.get() + wrong.get()) {

      List<AoIdData> aoIdData =
          sourceDao.selectAoId(idColumn, aoIdColumn, sourceTable, fiasIdColumn);

      aoIdData
          .parallelStream()
          .forEach(
              data -> {
                try {
                  String fiasId = convertId(data);

                  sourceDao.updateFiasId(idColumn, fiasIdColumn, sourceTable, fiasId, data.getId());
                  complete.incrementAndGet();

                }
                catch (FiasException ignored) {
                }
              }
          );

      stopWatch.splitInfo("Project " + project.toString() + ": convert " + complete.get() + " fias_id out of " + count + ". Bad data: " + wrong.get());
    }
    stopWatch.splitInfo("Project " + project.toString() + ": converting completed");
  }

  private String convertId(AoIdData data) throws FiasException {
    String aoId = data.getAoId();
    Record record = null;
    Result<Record> addresses = fiasDao.findHistorical(aoId);
    if (addresses.size() > 0) {
      record = addresses.get(0);
    }
    else {
      try {
        record = fiasDao.findActual(aoId);
      }
      catch (Exception e) {
        log.warn("Found more, than one result of guid [{}]", aoId);
        wrong.incrementAndGet();
      }
    }

    if (record == null) {
      log.warn("Result of guid [{}] not found", aoId);
      wrong.incrementAndGet();
      throw new FiasException();
    }
    else {
      JsonId jsonId = new JsonId(
          "1.0",
          record.get("aoid", String.class),
          record.get("aoid_p1", String.class),
          record.get("aoid_p2", String.class),
          record.get("aoid_p3", String.class),
          record.get("aoid_p4", String.class),
          record.get("aoid_p5", String.class),
          record.get("guid", String.class)
      );
      return FiasIdUtils.convertToString(jsonId);
    }
  }
}