package pms.handler;

import java.util.List;
import pms.domain.CounselingMember;
import pms.domain.Member;
import request.RequestAgent;

public class CounselingMemberMyListHandler implements Command {

  RequestAgent requestAgent;

  public CounselingMemberMyListHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    Member loginUser = AuthLoginHandler.getLoginUser();
    System.out.println();
    System.out.println("[상담신청 이력] 페이지입니다.");
    int cnt = 0;
    requestAgent.request("counselingMember.selectList", null);

    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("상담신청 내역이 없습니다.");
      return;
    } else {

      List<CounselingMember> counselingMemberList = (List<CounselingMember>) 
          requestAgent.getObjects(CounselingMember.class);

      for (int i = 0; i < counselingMemberList.size() ; i++) {
        if(counselingMemberList.get(i).getName().equals(loginUser.getName())) {
          cnt++;
          System.out.printf("이름: %s\n", loginUser.getName());
          System.out.printf("연락처: %s\n", loginUser.getPhoneNum());
          System.out.printf("지병여부 : %s\n"
              + "현재증상 : %s\n"
              + "상담내용 : %s\n"
              + "의사성별 : %s\n",
              counselingMemberList.get(i).getDisease(),
              counselingMemberList.get(i).getContent(),
              counselingMemberList.get(i).getStateLabel(),
              counselingMemberList.get(i).getStateLabel2()

              );      
          System.out.println();
        } 
      }

      if (cnt == 0) {
        System.out.println("상담신청 내역이 없습니다.");
        return;
      }

    }
  }
}