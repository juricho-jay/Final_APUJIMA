package pms.domain;

import java.io.Serializable;
import java.sql.Date;

public class Comment implements Serializable {
  private int no;
  private Board boardNo;
  private Member commenter;
  private String content;
  private int whichBoard;
  private Date registeredDate;

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public int getWhichBoard() {
    return whichBoard;
  }
  public void setWhichBoard(int whichBoard) {
    this.whichBoard = whichBoard;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public Member getCommenter() {
    return commenter;
  }
  public void setCommenter(Member commenter) {
    this.commenter = commenter;
  }
  public Board getBoardNo() {
    return boardNo;
  }
  public void setBoardNo(Board boardNo) {
    this.boardNo = boardNo;
  }
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }

}
