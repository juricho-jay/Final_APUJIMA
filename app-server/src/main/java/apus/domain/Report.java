package apus.domain;

import java.io.Serializable;

public class Report implements Serializable{
  private int no;
  private String reason;
  private Member requester;
  private Board requestBoard;



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



}

