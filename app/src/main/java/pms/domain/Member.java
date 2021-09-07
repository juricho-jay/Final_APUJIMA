package pms.domain;

import java.io.Serializable;
import java.sql.Date;

public class Member implements Serializable{
  private String name;
  private String id;
  private String password;
  private String birthDay;
  private String email;
  private String phoneNum;
  private String photo;
  private String sex;
  private Date registeredDate;

  private int doctorOrNot;
  private String interest;
  private String doctorLicense;


  public int getDoctor() {
    return doctorOrNot;
  }
  public void setDoctor(int doctor) {
    this.doctorOrNot = doctor;
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
  public String getBirthDay() {
    return birthDay;
  }
  public void setBirthDay(String birthDay) {
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
