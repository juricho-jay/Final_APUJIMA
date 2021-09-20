package pms.domain;

import java.io.Serializable;
import java.sql.Date;

public class NoticeBoard implements Serializable{
  public int no;
  private String title;
  private String content;
  private Member writer;
  private Date registeredDate;
  private int viewCount;
  private int like;
  public NoticeBoard index;
  //  public NoticeBoard listIndex;


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
  public NoticeBoard getIndex() {
    return index;
  }
  public void setIndex(NoticeBoard index) {
    this.index = index;
  }




}

