package ru.cg.model;

/**
 * @author Rinat Suleymanov
 */
public class AoIdData {

  private Long id;
  private String aoId;

  public AoIdData(Long id, String aoId) {
    this.id = id;
    this.aoId = aoId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAoId() {
    return aoId;
  }

  public void setAoId(String aoId) {
    this.aoId = aoId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AoIdData)) return false;

    AoIdData aoIdData = (AoIdData) o;

    if (!id.equals(aoIdData.id)) return false;
    return aoId.equals(aoIdData.aoId);
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + aoId.hashCode();
    return result;
  }
}
