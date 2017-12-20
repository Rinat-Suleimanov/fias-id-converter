package ru.cg.model;

/**
 * @author Rinat Suleymanov
 */
public class JsonId {

  private String version;
  private String aoid;
  private String aoid_p1;
  private String aoid_p2;
  private String aoid_p3;
  private String aoid_p4;
  private String aoid_p5;
  private String guid;

  public JsonId() {

  }

  public JsonId(String version, String aoid, String aoid_p1, String aoid_p2, String aoid_p3, String aoid_p4, String aoid_p5, String guid) {
    this.version = version;
    this.aoid = aoid;
    this.aoid_p1 = aoid_p1;
    this.aoid_p2 = aoid_p2;
    this.aoid_p3 = aoid_p3;
    this.aoid_p4 = aoid_p4;
    this.aoid_p5 = aoid_p5;
    this.guid = guid;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getAoid() {
    return aoid;
  }

  public void setAoid(String aoid) {
    this.aoid = aoid;
  }

  public String getAoid_p1() {
    return aoid_p1;
  }

  public void setAoid_p1(String aoid_p1) {
    this.aoid_p1 = aoid_p1;
  }

  public String getAoid_p2() {
    return aoid_p2;
  }

  public void setAoid_p2(String aoid_p2) {
    this.aoid_p2 = aoid_p2;
  }

  public String getAoid_p3() {
    return aoid_p3;
  }

  public void setAoid_p3(String aoid_p3) {
    this.aoid_p3 = aoid_p3;
  }

  public String getAoid_p4() {
    return aoid_p4;
  }

  public void setAoid_p4(String aoid_p4) {
    this.aoid_p4 = aoid_p4;
  }

  public String getAoid_p5() {
    return aoid_p5;
  }

  public void setAoid_p5(String aoid_p5) {
    this.aoid_p5 = aoid_p5;
  }

  public String getGuid() {
    return guid;
  }

  public void setGuid(String guid) {
    this.guid = guid;
  }
}
