package apus.domain;

import java.io.Serializable;
import java.sql.Date;

public class Bucket implements Serializable {

  private int no;
  private String title;
  private String content;
  private String check;
  private Member writer;
  private boolean complete;
  private Date registeredDate;
  private Date completedDate;
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getCheck() {
    return check;
  }
  public void setCheck(String check) {
    this.check = check;
  }
  public Member getWriter() {
    return writer;
  }
  public void setWriter(Member writer) {
    this.writer = writer;
  }
  public boolean isComplete() {
    return complete;
  }
  public void setComplete(boolean complete) {
    this.complete = complete;
  }
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }
  public Date getCompletedDate() {
    return completedDate;
  }
  public void setCompletedDate(Date completedDate) {
    this.completedDate = completedDate;
  }


}
