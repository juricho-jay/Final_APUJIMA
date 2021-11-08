package apus.domain;

import java.io.Serializable;

public class Medicine implements Serializable{
  private int no;
  private String name;
  private int ageLimit;
  private String shape;
  private String color;
  private String effect;
  private int active; // 활성화 여부 => 1 : 활성화  0 : 비활성화
  private int check; // 새로 등록된 약품에 대한 관리자 확인 여부 => 1 : 체크  0 : 미체크
  private Member requester; // 약품 요청자(의사)




  public Member getRequester() {
    return requester;
  }
  public void setRequester(Member requester) {
    this.requester = requester;
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
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

  public int getActive() {
    return active;
  }
  public void setActive(int active) {
    this.active = active;
  }
  public int getCheck() {
    return check;
  }
  public void setCheck(int check) {
    this.check = check;
  }

}




