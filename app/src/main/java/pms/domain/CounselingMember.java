package pms.domain;

import java.sql.Date;

public class CounselingMember {

  private String name;
  private String tel;
  private String disease;
  private String content;
  private String title;
  private String sex;
  private int counselingStatus;
  private int counselorStatus;
  private Date registeredDate;
  private String stateLabel;
  private String stateLabel2;

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
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
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getSex() {
    return sex;
  }
  public void setSex(String sex) {
    this.sex = sex;
  }

  public int getCounselingStatus() {
    return counselingStatus;
  }
  public void setCounselingStatus(int counselingStatus) {
    this.counselingStatus = counselingStatus;
  }

  public int getCounselorStatus() {
    return counselorStatus;
  }
  public void setCounselorStatus(int counselorStatus) {
    this.counselorStatus = counselorStatus;
  }
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }
  public String getStateLabel() {
    return stateLabel;
  }
  public void setStateLabel(String stateLabel) {
    this.stateLabel = stateLabel;
  }
  public String getStateLabel2() {
    return stateLabel2;
  }
  public void setStateLabel2(String stateLabel2) {
    this.stateLabel2 = stateLabel2;
  }



}
