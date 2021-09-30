package pms.domain;

import java.io.Serializable;
import java.sql.Date;

public class DoctorBoard extends XBoard implements Serializable {
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
    DoctorBoard.lastIndex = lastIndex;
  }
  public String getReason() {
    return reason;
  }
  public void setReason(String reason) {
    this.reason = reason;
  }
  public String getRequester() {
    return requester;
  }
  public void setRequester(String requester) {
    this.requester = requester;
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

}

