package Final_APUJIMA.handler;

import java.util.List;
import Final_APUJIMA.domain.DoctorMember;
import Final_APUJIMA.domain.Human;
import Final_APUJIMA.domain.Member;
import Final_APUJIMA.util.Prompt;

public class FinderHandler {

  List<DoctorMember> doctormemberList; 
  List<Member> memberList;
  DoctorMemberHandler doctorMemberHandler;
  MemberHandler memberHandler;
  Human human;


  //ID 찾기
  public void findId() {
    while (true) {
      System.out.println("[ID 찾기] 페이지입니다.");
      System.out.println("(취소: #)");
      String Name = Prompt.inputString("이름> ");
      if (Name.contains("#")) {
        return;
      }
      String PhoneNum = Prompt.inputString("휴대폰> ");
      human = validFindID(Name,PhoneNum);
      if(human == null) {
        System.out.println();
        System.out.println("아이디를 찾을 수 없습니다.");
        System.out.println();
        continue;
      } else {
        System.out.println();
        System.out.printf ("회원님의 아이디입니다. [ %s ]", human.getId());
        System.out.println();
        return;
      }
    }
  }

  // 비밀번호 찾기
  public void findPassword() {
    while (true) {
      System.out.println("[비밀번호 찾기] 페이지입니다.");
      System.out.println("(취소: #)");
      String id = Prompt.inputString("아이디> ");
      if(id.contains("#")) {
        return;
      }
      String PhoneNo = Prompt.inputString("휴대폰> ");
      human = validFindPassword(id,PhoneNo);
      if(human == null) {
        System.out.println("입력이 잘못되었습니다. 정보를 찾을 수 없습니다.");
        System.out.println();
        continue;
      }else {
        System.out.printf ("회원님의 비밀번호입니다. [ %s ]",human.getPassword());
        System.out.println();
        return;
      }
    }
  }


  // 아이디 찾기 유효성 검사
  public Human validFindID (String name, String phoneNum)   {
    if (human instanceof DoctorMember) {
      for (Human member : doctormemberList) {
        if (member.getName().equals(name) && (member.getPhoneNum().equals(phoneNum))) {
          return member;
        }
      }
    } else if (human instanceof Member) {
      for (Human member : memberList) {
        if (member.getName().equals(name) && (member.getPhoneNum().equals(phoneNum))) {
          return member;
        }
      }
    }
    return null;
  }

  // 비번찾기 유효성 검사
  public Human validFindPassword (String id, String phoneNum)   {
    if (human instanceof DoctorMember) {
      for (Human member : doctormemberList) {
        if (member.getId().equals(id) && (member.getPhoneNum().equals(phoneNum))) {
          return member;
        }
      }
    } else if (human instanceof Member) {
      for (Human member : memberList) {
        if (member.getId().equals(id) && (member.getPhoneNum().equals(phoneNum))) {
          return member;
        }
      }
    }
    return null;
  }

}


//
////PW찾기메뉴 메서드
//public void doFindPassWordMenu() {
//  System.out.println("[PW 찾기] 페이지입니다. 선택해주세요.");
//  System.out.println("1) 의사");
//  System.out.println("2) 일반");
//  System.out.println("0) 뒤로가기");   // 구현해야해 아직 없어
//
//  try{
//    int memNo = (Prompt.inputInt("PW 찾기> "));
//    System.out.println();
//    switch(memNo){
//      case 1:
//        FindPassword();
//        break;
//      case 2:
//        FindPassword();
//        break;
//    }
//  }
//  catch(Exception e) {
//    System.out.println();
//    System.out.println("번호를 잘못 입력하셨습니다. 번호를 다시 입력해 주세요. \n" + e.getMessage());
//    System.out.println();
//  }
//}
