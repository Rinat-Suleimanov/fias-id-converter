package ru.cg.dao;

import java.util.List;
import javax.sql.DataSource;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import ru.cg.model.AoIdData;

/**
 * @author Rinat Suleymanov
 */
public class SourceDao {

  private final DSLContext dsl;

  public SourceDao(DataSource dataSource) {
    dsl = DSL.using(dataSource, SQLDialect.POSTGRES);
  }

  public long countRecordsToConvert(String table, String aoIdColumn, String fiasIdColumn) {
    return (long) dsl
        .resultQuery(QueryFactory.count(table, aoIdColumn, fiasIdColumn))
        .fetchOne("count");
  }

  public List<AoIdData> selectAoId(String idColumn, String aoIdColumn, String table, String fiasIdColumn) {
    return dsl
        .resultQuery(QueryFactory.selectAllAoId(idColumn, aoIdColumn, table, fiasIdColumn))
        .fetch(record -> new AoIdData(
            record.get(idColumn, Long.class),
            record.get(aoIdColumn, String.class))
        );
  }

  public void updateFiasId(String idColumn, String fiasIdColumn, String table, String fiasId, Long id) {
    dsl
        .resultQuery(QueryFactory.updateFiasId(idColumn, fiasIdColumn, table), fiasId, id)
        .execute();
  }
}