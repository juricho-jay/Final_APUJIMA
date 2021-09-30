package pms.domain;

import java.io.Serializable;
import java.sql.Date;

public class NoticeBoard extends XBoard implements Serializable{
  private String title;
  private String content;
  private Date registeredDate;
  private int viewCount;
  public NoticeBoard index;
  public static int lastIndex;



  public static int getLastIndex() {
    return lastIndex;
  }
  public static void setLastIndex(int lastIndex) {
    NoticeBoard.lastIndex = lastIndex;
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
  public NoticeBoard getIndex() {
    return index;
  }
  public void setIndex(NoticeBoard index) {
    this.index = index;
  }

}

