package ru.cg.model;

/**
 * @author Rinat Suleymanov
 */
public enum Project {
  ZAGS("np_address", "zags_ibd", "ao_id", "fias_id"),
  GAI_APPLICANT("applicant", "gai", "fias_uuid", "fias_address"),
  GAI_DIVISION_ADDRESS("division_address", "gai", "fias_uuid", "fias_address"),
  GAI_OBJECT_ADDRESS("object_address", "gai", "fias_uuid", "fias_address"),
  GAI_ORGANIZATION_VERSION_ADDRESS("organization_version_address", "gai", "fias_uuid", "fias_address"),;

  private final String table;
  private final String schema;
  private final String aoId;
  private final String fiasId;


  Project(String table, String schema, String aoId, String fiasId) {
    this.table = table;
    this.schema = schema;
    this.aoId = aoId;
    this.fiasId = fiasId;
  }

  public String table() {
    return table;
  }

  public String schema() {
    return schema;
  }

  public String aoId() {
    return aoId;
  }

  public String fiasId() {
    return fiasId;
  }
}
