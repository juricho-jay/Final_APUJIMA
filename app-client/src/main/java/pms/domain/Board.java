package pms.domain;

import java.io.Serializable;
import java.sql.Date;

public class Board implements Serializable{
  private int no;
  private Member writer;
  private int whichBoard; //보드 type
  private String title;
  private String content;
  private Date registeredDate;
  private int viewCount;
  private String reason;
  private String requester;
  public static int lastIndex;


  public static int getLastIndex() {
    return lastIndex;
  }
  public static void setLastIndex(int lastIndex) {
    Board.lastIndex = lastIndex;
  }
  public String getRequester() {
    return requester;
  }
  public void setRequester(String requester) {
    this.requester = requester;
  }
  public String getReason() {
    return reason;
  }
  public void setReason(String reason) {
    this.reason = reason;
  }
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }
  public int getViewCount() {
    return viewCount;
  }
  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
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
  public int getWhichBoard() {
    return whichBoard;
  }
  public void setWhichBoard(int whichBoard) {
    this.whichBoard = whichBoard;
  }


}

