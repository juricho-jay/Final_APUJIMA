package apus.domain;

import java.sql.Date;
import apus.domain.Member;

public class Plant {

  private int no;
  private int level;
  private String plantName;
  private int exp;
  private String shape;
  private Date registeredDate;
  private Member ownerName;


  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getPlantName() {
    return plantName;
  }
  public void setPlantName(String plantName) {
    this.plantName = plantName;
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
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }
  public Member getOwnerName() {
    return ownerName;
  }
  public void setOwnerName(Member ownerName) {
    this.ownerName = ownerName;
  }





}
