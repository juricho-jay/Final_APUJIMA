package Final_APUJIMA.handler;
import java.util.List;
import Final_APUJIMA.domain.CounselingMember;
import Final_APUJIMA.util.Prompt;

public class CounselingMemberHandler {

  List memberAppList; //멤버타입의 리스트를 선언.

  public CounselingMemberHandler(List memberAppList) {
    this.memberAppList = memberAppList; // => ?? 생성자 초기화
  }

  CounselingMember memberApp = new CounselingMember();


  public void addcounseling() {
    System.out.println("[상담 신청]");

    System.out.println();
    memberApp.setName(Prompt.inputString("성함> "));
    memberApp.setTel(Prompt.inputString("연락처> "));
    memberApp.setDisease(Prompt.inputString("지병 여부> "));
    memberApp.setContent(Prompt.inputString("상담 내용> "));

    int counselingStatus = (Prompt.inputInt("상담 주제> "));
    System.out.println();

    switch (counselingStatus) {
      case 1: 
        System.out.println("1. 우울증");
        break;
      case 2:
        System.out.println("2. 진로");
        break;
      case 3:
        System.out.println("3. 일상생활");
        break;
      case 4:
        System.out.println("4. 연애");
        break;
      case 5:
        System.out.println("5. 인간관계");
        break;
      case 6:
        System.out.println("6. 기타");
        break;

    }
    memberApp.setStatus(Prompt.inputInt("> "));

    System.out.println("상담사 성별> ");
    System.out.println("1. 여성 상담사");
    System.out.println("2. 남성 상담사");
    System.out.println("3. 상관없음");
    memberApp.setStatus(Prompt.inputInt("> "));

    memberApp.getRegisteredDate();

    memberAppList.add(memberApp);
    System.out.println("신청 완료!");
    System.out.println();
  }




}
