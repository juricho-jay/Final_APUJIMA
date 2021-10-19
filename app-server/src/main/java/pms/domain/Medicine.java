package pms.domain;

import java.io.Serializable;

public class Medicine implements Serializable{
  private int no;
  private String name;
  private int ageLimit;
  private String shape;
  private String color;
  private String effect;
  public static int lastIndex;


  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public static int getLastIndex() {
    return lastIndex;
  }
  public static void setLastIndex(int lastIndex) {
    Board.lastIndex = lastIndex;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getAgeLimit() {
    return ageLimit;
  }
  public void setAgeLimit(int ageLimit) {
    this.ageLimit = ageLimit;
  }
  public String getShape() {
    return shape;
  }
  public void setShape(String shape) {
    this.shape = shape;
  }
  public String getColor() {
    return color;
  }
  public void setColor(String color) {
    this.color = color;
  }
  public String getEffect() {
    return effect;
  }
  public void setEffect(String effect) {
    this.effect = effect;
  }

  //add , list , search, update, delete
}




