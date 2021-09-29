package pms.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class NoticeBoard extends XBoard implements Serializable{
  public int no;
  private String title;
  private String content;
  private Member writer;
  private Date registeredDate;
  private int viewCount;
  private int like;
  public NoticeBoard index;
  private List<Member> likeMemberList;
  public static int lastIndex;



  public static int getLastIndex() {
    return lastIndex;
  }
  public static void setLastIndex(int lastIndex) {
    NoticeBoard.lastIndex = lastIndex;
  }
  @Override
  public Member getWriter() {
    return writer;
  }
  @Override
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
  @Override
  public int getNo() {
    return no;
  }
  @Override
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
  public List<Member> getLikeMemberList() {
    return likeMemberList;
  }
  public void setLikeMemberList(List<Member> likeMemberList) {
    this.likeMemberList = likeMemberList;
  }




}

