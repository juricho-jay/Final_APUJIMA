package pms.handler;

import pms.domain.CounselingMember;
import pms.domain.Member;
import request.RequestAgent;
import util.Prompt;

public class CounselingMemberAddHandler implements Command {

  RequestAgent requestAgent;

  public CounselingMemberAddHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[상담 신청]페이지입니다.");
    System.out.println();
    Member loginUser = AuthLoginHandler.getLoginUser();
    CounselingMember counselingMember = new CounselingMember();

    System.out.printf("이름: %s\n", loginUser.getName());
    counselingMember.setName(loginUser.getName());
    System.out.printf("연락처: %s\n", loginUser.getPhoneNum());
    counselingMember.setPhoneNum(loginUser.getPhoneNum());


    counselingMember.setDisease(Prompt.inputString("지병여부> "));
    if(counselingMember.getDisease().trim().equals("")) {
      System.out.println("입력하시지 않아 공백으로 지정되었습니다. 상담시 상담사에게 말씀해주세요.");
      counselingMember.setDisease("미기입");
    }
    counselingMember.setContent(Prompt.inputString("현재증상> "));
    if(counselingMember.getContent().trim().equals("")) {
      System.out.println("입력하시지 않아 공백으로 지정되었습니다. 상담시 상담사에게 말씀해주세요.");
      counselingMember.setContent("미기입");
    }

    while(true) {
      System.out.println();
      System.out.println("[상담 내용]");
      System.out.println("1. 우울증");
      System.out.println("2. 진로");
      System.out.println("3. 일상생활");
      System.out.println("4. 연애");
      System.out.println("5. 인간관계");
      System.out.println("6. 기타");
      try {
        int counselingStatus = (Prompt.inputInt("선택> "));
        System.out.println();


        String stateLabel = null;
        if(counselingStatus == 6) {
          stateLabel = Prompt.inputString("상담 내용를 작성해 주세요> ");
        } else {
          switch (counselingStatus) {
            case 1:
              stateLabel = "우울증";
              break;
            case 2:
              stateLabel = "진로";
              break;
            case 3:
              stateLabel = "일상생활";
              break;
            case 4:
              stateLabel = "연애";
              break;
            case 5:
              stateLabel = "인간관계";
              break;
            case 6:
              stateLabel = "기타";
              break;

            default: 
              System.out.println("잘못 입력하셨습니다. 1 ~ 6중에서 입력해 주세요.");
              continue;
          }
        }
        counselingMember.setStateLabel(stateLabel);
        break;
      }catch(Exception e ) {
        System.out.println("잘못 입력하셨습니다. 1 ~ 6중에서 입력해 주세요.");
      }
    }

    while(true) {
      System.out.println();
      System.out.println("상담사 성별> ");
      System.out.println("1. 여성 상담사");
      System.out.println("2. 남성 상담사");
      System.out.println("3. 상관없음");
      try {
        int counselorStatus = Prompt.inputInt("선택> ");

        String stateLabel2 = null;
        switch (counselorStatus) {
          case 1:
            stateLabel2 = "여";
            break;
          case 2:
            stateLabel2 = "남";
            break;
          case 3:
            stateLabel2 = "상관없음";
            break;
          default:
            System.out.println("잘못 입력하셨습니다. 1 ~ 3중에 다시 입력해주세요. ");
            continue;
        }
        counselingMember.setStateLabel2(stateLabel2);
        break;
      } catch(Exception e) {
        System.out.println("잘못 입력하셨습니다. 1 ~ 3중에 다시 입력해주세요. ");
      }
    }

    requestAgent.request("counselingMember.insert", counselingMember);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("상담 신청 실패!");
      return;
    }

    System.out.println();
    System.out.println("신청이 완료되었습니다.");
  }

}
