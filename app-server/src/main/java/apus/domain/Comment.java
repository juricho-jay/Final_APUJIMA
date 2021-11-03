package apus.domain;

import java.io.Serializable;
import java.sql.Date;

public class Comment implements Serializable {
  private int no;
  private Board commentBoard;
  private Member commenter;
  private String content;
  private Date registeredDate;

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
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
  public Board getCommentBoard() {
    return commentBoard;
  }
  public void setCommentBoard(Board commentBoard) {
    this.commentBoard = commentBoard;
  }
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }

}
