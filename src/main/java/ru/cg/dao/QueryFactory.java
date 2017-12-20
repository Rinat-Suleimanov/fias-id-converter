package ru.cg.dao;

import java.util.SplittableRandom;

/**
 * @author Rinat Suleymanov
 */
public class QueryFactory {

  private static final String SELECT_ALL = "SELECT * FROM %s;";
  private static final String SELECT_ALL_AO_ID =
      "SELECT\n" +
          "  a.%1$s,\n" +
          "  a.%2$s\n" +
          "FROM %3$s a\n" +
          "WHERE a.%2$s IS NOT NULL AND a.%4$s ISNULL\n" +
          "GROUP BY a.%1$s\n" +
          "LIMIT 10000;\n";
  private static final String INSERT_SERVICE_WRONG = "INSERT INTO %s (source_id, ao_id) VALUES (?, ?);";
  private static final String INSERT_SERVICE_COMPLETE = "INSERT INTO %s (source_id, ao_id, fias_id) VALUES (?, ?, ?);";
  private static final String UPDATE_FIAS_ID = "UPDATE %s SET %s = ? WHERE %s = ?;";
  private static final String HISTORICAL_ADDRESS =
      "SELECT *\n" +
          "FROM\n" +
          "  (SELECT f.*\n" +
          "   FROM\n" +
          "     fias.address_object fao,\n" +
          "     fias.historical_address_data_full(fao) f\n" +
          "   WHERE\n" +
          "     fao.ao_id = ?) t\n";
  private static final String ACTUAL_ADDRESS =
      "SELECT *\n" +
          "FROM\n" +
          "  (SELECT f.*\n" +
          "   FROM\n" +
          "     fias.address_object fao,\n" +
          "     fias.actual_address_data_full(fao) f\n" +
          "   WHERE\n" +
          "     fao.ao_guid = ?) t\n";

  private static final String COUNT =
      "SELECT count(*)\n" +
          "FROM %s a\n" +
          "WHERE a.%s IS NOT NULL\n" +
          "      AND a.%s ISNULL;";

  public static String selectAll(String table) {
    return String.format(SELECT_ALL, table);
  }

  public static String count(String table, String aoIdColumn, String fiasIdColumn) {
    return String.format(COUNT, table, aoIdColumn, fiasIdColumn);
  }

  public static String selectAllAoId(String idColumn, String aoIdColumn, String table, String fiasIdColumn) {
    return String.format(SELECT_ALL_AO_ID, idColumn, aoIdColumn, table, fiasIdColumn);
  }

  public static String insertIntoServiceWrong(String table) {
    return String.format(INSERT_SERVICE_WRONG, table);
  }


  public static String insertIntoServiceComplete(String table) {
    return String.format(INSERT_SERVICE_COMPLETE, table);
  }

  public static String updateFiasId(String idColumn, String fiasIdColumn, String table) {
    return String.format(UPDATE_FIAS_ID, table, fiasIdColumn, idColumn);
  }

  public static String findActual() {
    return ACTUAL_ADDRESS;
  }

  public static String findHistorical() {
    return HISTORICAL_ADDRESS;
  }
}
