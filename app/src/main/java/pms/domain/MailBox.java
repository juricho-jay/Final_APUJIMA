package pms.domain;

import java.sql.Date;

public class MailBox {

  //  private int mailNo;
  private Member sender;
  private String title;
  private String content;
  //  private Member memberreceiver;
  private String receiver;
  private Date sendingTime;


  //  public int getMailNo() {
  //    return mailNo;
  //  }
  //  public void setMailNo(int mailNo) {
  //    this.mailNo = mailNo;
  //  }
  public Member getSender() {
    return sender;
  }
  public void setSender(Member sender) {
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
  //  public Member getMemberreceiver() {
  //    return memberreceiver;
  //  }
  //  public void setMemberreceiver(Member memberreceiver) {
  //    this.memberreceiver = memberreceiver;
  //  }
  public String getReceiver() {
    return receiver;
  }
  public void setReceiver(String receiver) {
    this.receiver = receiver;
  }





}
