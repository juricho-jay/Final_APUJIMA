package apus.domain;

import java.sql.Date;


public class MailBox {

  private int no;  // 아이디 별로 1번부터 보이는 번호
  private Member sender;
  private String title;
  private String content;
  private Member receiver;
  private Date sentTime;
  private Date receivedTime;


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

  public Date getSentTime() {
    return sentTime;
  }
  public void setSentTime(Date sentTime) {
    this.sentTime = sentTime;
  }

  public Date getReceivedTime() {
    return receivedTime;
  }
  public void setReceivedTime(Date receivedTime) {
    this.receivedTime = receivedTime;
  }
  public Member getSender() {
    return sender;
  }
  public void setSender(Member sender) {
    this.sender = sender;
  }
  public Member getReceiver() {
    return receiver;
  }
  public void setReceiver(Member receiver) {
    this.receiver = receiver;
  }





}
