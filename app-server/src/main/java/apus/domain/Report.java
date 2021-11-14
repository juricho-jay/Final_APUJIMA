package apus.domain;

import java.io.Serializable;

public class Report implements Serializable{
  private int no;
  private String reason;
  private Member requester;
  private Board requestBoard;
  private int check; // 새로 등록된 신고게시판에 대한 관리자 확인 여부 => 1 : 체크  0 : 미체크



  public int getCheck() {
    return check;
  }
  public void setCheck(int check) {
    this.check = check;
  }
  public Member getRequester() {
    return requester;
  }
  public void setRequester(Member requester) {
    this.requester = requester;
  }
  public Board getRequestBoard() {
    return requestBoard;
  }
  public void setRequestBoard(Board requestBoard) {
    this.requestBoard = requestBoard;
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getReason() {
    return reason;
  }
  public void setReason(String reason) {
    this.reason = reason;
  }




}

