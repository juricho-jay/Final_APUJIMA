package apus.domain;

import java.io.Serializable;

public class Like implements Serializable {

  private int no; // 좋아요 고유 번호
  private Board likeBoard; // 좋아요 달린 게시판 번호
  private Member liker; // 좋아요 누른 사람
  private int likeOrNot; // 좋아요 여부


  public Board getLikeBoard() {
    return likeBoard;
  }
  public void setLikeBoard(Board likeBoard) {
    this.likeBoard = likeBoard;
  }
  public Member getLiker() {
    return liker;
  }
  public void setLiker(Member liker) {
    this.liker = liker;
  }
  public int getLikeOrNot() {
    return likeOrNot;
  }
  public void setLikeOrNot(int likeOrNot) {
    this.likeOrNot = likeOrNot;
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }




}
