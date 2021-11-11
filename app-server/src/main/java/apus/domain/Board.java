package apus.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Board implements Serializable{
  private int no;
  private Member writer;
  private int whichBoard; //보드 type
  private String title;
  private String content;
  private Date registeredDate;
  private int viewCount;
  private int active; // 활성화 여부 => 1 : 활성화  0 : 비활성화
  private List<Comment> comments = new ArrayList<>();
  private List<Like> likes = new ArrayList<>();

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
  public List<Comment> getComments() {
    return comments;
  }
  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }
  public List<Like> getLikes() {
    return likes;
  }
  public void setLikes(List<Like> likes) {
    this.likes = likes;
  }
  public int getActive() {
    return active;
  }
  public void setActive(int active) {
    this.active = active;
  }


}

