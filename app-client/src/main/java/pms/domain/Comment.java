package pms.domain;

import java.io.Serializable;

public class Comment implements Serializable {
  static int commentTotal; // 댓글 총 갯수
  private int commentBoardNo; // 댓글이 달린 게시판 번호
  private String commenter; // 댓글 달은 사람
  private String commentWriter; // 댓글이 달린 글의 작성자
  private String commentContent; //댓글 내용
  private int whichBoard; //댓글 달린 게시판 종류 (free/doctor/notice)
  private int no; // 게시판마다 1부터 시작하는 댓글 번호
  private int commentNo; // 댓글 고유번호



  public int getCommentNo() {
    return commentNo;
  }
  public void setCommentNo(int commentNo) {
    this.commentNo = commentNo;
  }
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



}
