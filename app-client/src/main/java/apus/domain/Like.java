package apus.domain;

import java.io.Serializable;

public class Like implements Serializable {

  private Board likeBoard; // 좋아요 달린 게시판 번호
  private Member liker; // 좋아요 누른 사람


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



}
