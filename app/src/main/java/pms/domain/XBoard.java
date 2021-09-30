package pms.domain;

public abstract class XBoard {
  private int no;
  private Member writer;
  private String whichBoard;
  private int like;

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public Member getWriter() {
    return writer;
  }
  public void setWriter(Member writer) {
    this.writer = writer;
  }
  public String getWhichBoard() {
    return whichBoard;
  }
  public void setWhichBoard(String whichBoard) {
    this.whichBoard = whichBoard;
  }
  public int getLike() {
    return like;
  }
  public void setLike(int like) {
    this.like = like;
  }


}
