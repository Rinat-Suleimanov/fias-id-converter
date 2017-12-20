package ru.cg.dao;

import javax.sql.DataSource;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

/**
 * @author Rinat Suleymanov
 */
public class FiasDao {

  private final DSLContext dsl;

  public FiasDao(DataSource dataSource) {
    this.dsl = DSL.using(dataSource, SQLDialect.POSTGRES);
  }

  public Result<Record> findHistorical(String aoId) {
    return dsl.resultQuery(QueryFactory.findHistorical(), aoId).fetch();
  }

  public Record findActual(String guId) throws Exception {
    return dsl.resultQuery(QueryFactory.findActual(), guId).fetchOne();
  }
}
