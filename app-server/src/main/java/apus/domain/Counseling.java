package apus.domain;

import java.io.Serializable;

public class Counseling implements Serializable {

  private int no;
  private String disease;
  private String content;
  private Member counselor; // 상담사(의사)
  private Member client;




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




}
