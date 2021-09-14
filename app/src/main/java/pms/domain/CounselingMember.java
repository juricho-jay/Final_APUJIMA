package pms.domain;

import java.io.Serializable;

public class CounselingMember implements Serializable {

  private String disease;
  private String content;
  private String name;
  private String phoneNum;
  private int counselingStatus;
  private int counselorStatus;
  private String stateLabel;
  private String stateLabel2;



  public String getPhoneNum() {
    return phoneNum;
  }
  public void setPhoneNum(String phoneNum) {
    this.phoneNum = phoneNum;
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
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
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
