package pms.domain;

import java.sql.Date;

public class Plant {

  private String PlantName;
  private int level;
  private int exp;
  private int noOfplant;
  private String shape;
  private Date registeredDate;
  private String ownerName;

  public String getPlantName() {
    return PlantName;
  }
  public void setPlantName(String plantName) {
    PlantName = plantName;
  }
  public String getShape() {
    return shape;
  }
  public void setShape(String shape) {
    this.shape = shape;
  }

  public int getLevel() {
    return level;
  }
  public void setLevel(int level) {
    this.level = level;
  }

  public int getExp() {
    return exp;
  }
  public void setExp(int exp) {
    this.exp = exp;
  }
  public int getNoOfplant() {
    return noOfplant;
  }
  public void setNoOfplant(int noOfplant) {
    this.noOfplant = noOfplant;
  }
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }
  public String getOwnerName() {
    return ownerName;
  }
  public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
  }




}
