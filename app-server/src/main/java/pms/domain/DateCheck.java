package pms.domain;

import java.io.Serializable;
import java.util.Date;

public class DateCheck implements Serializable {

  private Date date;
  private String user;


  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }



}
