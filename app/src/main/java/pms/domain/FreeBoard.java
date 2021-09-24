package pms.domain;

import java.io.Serializable;
import java.sql.Date;

public class FreeBoard implements Serializable{
  private int no;
  private String title;
  private String content;
  private Member writer;
  private Date registeredDate;
  private int viewCount;
  private int like;
  private String reason;
  private String requester;
<<<<<<< HEAD
  // private int lastIndex;
  public static int lastIndex = 1;
  public static int index = lastIndex;
=======
  public static int lastIndex;

>>>>>>> bb52b42cae21c961e0c42cef34a53f61081dfe9f


  public static int getIndex() {
    return index;
  }
  public static void setIndex(int index) {
    FreeBoard.index = index;
  }
  public static int getLastIndex() {
    return lastIndex;
  }
  public static void setLastIndex(int lastIndex) {
    FreeBoard.lastIndex = lastIndex;
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
  public Member getWriter() {
    return writer;
  }
  public void setWriter(Member writer) {
    this.writer = writer;
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
  public int getLike() {
    return like;
  }
  public void setLike(int like) {
    this.like = like;
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
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

}

