package apus.domain;

import java.io.Serializable;
import java.util.Date;

public class DateCheck implements Serializable {
  private int attendanceNo;
  private Date date;
  private Member attendee;

  public int getAttendanceNo() {
    return attendanceNo;
  }
  public void setAttendanceNo(int attendanceNo) {
    this.attendanceNo = attendanceNo;
  }
  public Date getDate() {
    return date;
  }
  public void setDate(Date date) {
    this.date = date;
  }
  public Member getAttendee() {
    return attendee;
  }
  public void setAttendee(Member attendee) {
    this.attendee = attendee;
  }






}
