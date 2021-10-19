package pms.domain;

import java.sql.Date;


public class MailBox {

  private int no;  // 아이디 별로 1번부터 보이는 번호
  private int mailNo; // 메일박스 고유 번호
  private String sender;
  private String title;
  private String content;
  private String receiver;
  private Date sendingTime;
  private Date receivedTime;


  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public int getMailNo() {
    return mailNo;
  }
  public void setMailNo(int mailNo) {
    this.mailNo = mailNo;
  }
  public String getSender() {
    return sender;
  }
  public void setSender(String sender) {
    this.sender = sender;
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

  public Date getSendingTime() {
    return sendingTime;
  }
  public void setSendingTime(Date sendingTime) {
    this.sendingTime = sendingTime;
  }
  public String getReceiver() {
    return receiver;
  }
  public void setReceiver(String receiver) {
    this.receiver = receiver;
  }
  public Date getReceivedTime() {
    return receivedTime;
  }
  public void setReceivedTime(Date receivedTime) {
    this.receivedTime = receivedTime;
  }





}
