package pms.domain;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
  static int commentTotal; // 댓글 총 갯수
  private int commentBoardNo; // 댓글이 달린 게시판 번호
  private String commenter;
  private String commentWriter;
  private String commentContent;
  private Date commentDate;
  private int no; // 게시판마다 1부터 시작하는 댓글 번호



  public static int getCommentTotal() {
    return commentTotal;
  }
  public static void setCommentTotal(int commentTotal) {
    Comment.commentTotal = commentTotal;
  }
  public int getCommentBoardNo() {
    return commentBoardNo;
  }
  public void setCommentBoardNo(int commentBoardNo) {
    this.commentBoardNo = commentBoardNo;
  }
  public String getCommentContent() {
    return commentContent;
  }
  public void setCommentContent(String commentContent) {
    this.commentContent = commentContent;
  }
  public String getCommenter() {
    return commenter;
  }
  public void setCommenter(String commenter) {
    this.commenter = commenter;
  }

  public String getCommentWriter() {
    return commentWriter;
  }
  public void setCommentWriter(String commentWriter) {
    this.commentWriter = commentWriter;
  }
  public Date getCommentDate() {
    return commentDate;
  }
  public void setCommentDate(Date commentDate) {
    this.commentDate = commentDate;
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }


}
