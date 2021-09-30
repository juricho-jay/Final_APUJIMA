package pms.domain;

import java.io.Serializable;

public class Like implements Serializable {

  private int likeNo; // 좋아요 고유번호
  private int likeBoardNo; // 좋아요 달린 게시판 번호
  private Member liker; // 좋아요 누른 사람
  private String likeWriter; // 좋아요 누른 게시판의 글쓴이
  private String whichBoard;



  public int getLikeNo() {
    return likeNo;
  }
  public void setLikeNo(int likeNo) {
    this.likeNo = likeNo;
  }
  public int getLikeBoardNo() {
    return likeBoardNo;
  }
  public void setLikeBoardNo(int likeBoardNo) {
    this.likeBoardNo = likeBoardNo;
  }

  public Member getLiker() {
    return liker;
  }
  public void setLiker(Member liker) {
    this.liker = liker;
  }
  public String getLikeWriter() {
    return likeWriter;
  }
  public void setLikeWriter(String likeWriter) {
    this.likeWriter = likeWriter;
  }
  public String getWhichBoard() {
    return whichBoard;
  }
  public void setWhichBoard(String whichBoard) {
    this.whichBoard = whichBoard;
  }

}
