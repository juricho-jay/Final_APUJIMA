package Final_APUJIMA.domain;

public class Member {

  

  private String name;
  private String password;
  private String birthDay;
  private String email;
  private String phoneNum;
 
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
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
}
