package pms.handler;
import static pms.handler.AbstractDoctor.loginUser;

public class DisplayLoginUser {


  public void displayLoginUser() {
    //MemberHandler memberHandler = new MemberHandler(memberList);


    System.out.println("[내 정보] 페이지입니다.\n ");
    System.out.println(loginUser.getName() + "님 환영합니다!");

    System.out.println("아이디 : "+ loginUser.getId());
    System.out.println("이메일 : "+ loginUser.getEmail());
    System.out.println("생년월일 : "+ loginUser.getBirthDay());
    System.out.println("사진 : "+ loginUser.getPhoto());
    System.out.println("전화번호 : " + loginUser.getPhoneNum());
    System.out.println("성별 : " + loginUser.getSex());

    // counselingMemberHandler.counselingDetail();

  }

}
