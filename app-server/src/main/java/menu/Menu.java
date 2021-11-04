package menu;

public abstract class Menu {

  public static final int ACCESS_LOGOUT = 0x01;     
  public static final int ACCESS_GENERAL = 0x02;     
  public static final int ACCESS_DOCTOR = 0x04;       
  public static final int ACCESS_ADMIN = 0x08;     


  String title;

  int accessScope;

  public Menu(String title) {
    this(title, ACCESS_LOGOUT | ACCESS_GENERAL | ACCESS_ADMIN | ACCESS_DOCTOR); // 모든 사람 접근 가능
  }

  public Menu(String title, int accessScope) {
    this.title = title;
    this.accessScope = accessScope;
  }

  public abstract void execute(); // menuGroup도 무조건 execute 쓰게 하도록
}