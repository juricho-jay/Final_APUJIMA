package apus.domain;

public class WhichBoard {
  private int whichBoardNo; //보드 type
  private String whichBoard;
  private int active;

  public int getWhichBoardNo() {
    return whichBoardNo;
  }
  public void setWhichBoardNo(int whichBoardNo) {
    this.whichBoardNo = whichBoardNo;
  }
  public String getWhichBoard() {
    return whichBoard;
  }
  public void setWhichBoard(String whichBoard) {
    this.whichBoard = whichBoard;
  }
  public int getActive() {
    return active;
  }
  public void setActive(int active) {
    this.active = active;
  }

}