package pms.domain;

import java.io.Serializable;
import java.sql.Date;

public class Member implements Serializable{
  private String name;
  private String id;
  private String password;
  private Date birthDay;
  private String email;
  private String phoneNum;
  private String photo;
  private String sex;
  private Date registeredDate;
  private String interest;
  private String doctorLicense;
  private int doctorOrNot;
  //  private List<MailBox> mailBox;
  //  private List<Plant> plant;
  private int point;


  //  public List<Plant> getPlant() {
  //    return plant;
  //  }
  //  public void setPlant(List<Plant> plant) {
  //    this.plant = plant;
  //  }
  public int getPoint() {
    return point;
  }
  public void setPoint(int point) {
    this.point = point;
  }
  //  public List<MailBox> getMailBox() {
  //    return mailBox;
  //  }
  //  public void setMailBox(List<MailBox> mailBox) {
  //    this.mailBox = mailBox;
  //  }
  public int getDoctorOrNot() {
    return doctorOrNot;
  }
  public void setDoctorOrNot(int doctorOrNot) {
    this.doctorOrNot = doctorOrNot;
  }
  public String getDoctorLicense() {
    return doctorLicense;
  }
  public void setDoctorLicense(String doctorLicense) {
    this.doctorLicense = doctorLicense;
  }
  public String getInterest() {
    return interest;
  }
  public void setInterest(String interest) {
    this.interest = interest;
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public Date getBirthDay() {
    return birthDay;
  }
  public void setBirthDay(Date birthDay) {
    this.birthDay = birthDay;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPhoneNum() {
    return phoneNum;
  }
  public void setPhoneNum(String phoneNum) {
    this.phoneNum = phoneNum;
  }
  public String getPhoto() {
    return photo;
  }
  public void setPhoto(String photo) {
    this.photo = photo;
  }
  public String getSex() {
    return sex;
  }
  public void setSex(String sex) {
    this.sex = sex;
  }
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }

}
