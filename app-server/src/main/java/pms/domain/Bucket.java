package pms.domain;

import java.sql.Date;

public class Bucket {

  private int no;
  private String title;
  private String content;
  private String check;
  private String writer;
  private boolean complete;
  private Date registeredDate;
  public static int lastIndex;



  public String getWriter() {
    return writer;
  }
  public void setWriter(String writer) {
    this.writer = writer;
  }
  public String getCheck() {
    return check;
  }
  public void setCheck(String check) {
    this.check = check;
  }

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


}
