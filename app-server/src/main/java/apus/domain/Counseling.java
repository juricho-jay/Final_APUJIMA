package apus.domain;

import java.io.Serializable;
import java.sql.Date;

public class Counseling implements Serializable {

  private int no;
  private String disease;
  private String content;
  private Member counselor; // 상담사(의사)
  //  private Member counselorGender; // 상담사(의사)
  private Member client;
  private Member clientTel;
  //  private String gender;
  private Date registeredDate;

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public Member getCounselor() {
    return counselor;
  }
  public void setCounselor(Member counselor) {
    this.counselor = counselor;
  }
  public Member getClient() {
    return client;
  }
  public void setClient(Member client) {
    this.client = client;
  }
  public String getDisease() {
    return disease;
  }
  public void setDisease(String disease) {
    this.disease = disease;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }
  //  public String getGender() {
  //    return gender;
  //  }
  //  public void setGender(String gender) {
  //    this.gender = gender;
  //  }
  public Member getClientTel() {
    return clientTel;
  }
  public void setClientTel(Member clientTel) {
    this.clientTel = clientTel;
  }
  //  public Member getCounselorGender() {
  //    return counselorGender;
  //  }
  //  public void setCounselorGender(Member counselorGender) {
  //    this.counselorGender = counselorGender;
  //  }



}
