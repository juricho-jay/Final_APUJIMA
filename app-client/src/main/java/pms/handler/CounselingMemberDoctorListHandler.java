package pms.handler;

import java.util.List;
import pms.domain.CounselingMember;
import request.RequestAgent;

public class CounselingMemberDoctorListHandler implements Command {

  RequestAgent requestAgent;

  public CounselingMemberDoctorListHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }
  @Override
  public void execute(CommandRequest request) throws Exception {
    String loginUserSex = AuthLoginHandler.getLoginUser().getSex();
    System.out.println();
    System.out.println("[상담신청 이력] 페이지입니다.");

    requestAgent.request("counselingMember.selectList", null);

    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("상담신청 내역이 없습니다.");
      return;
    } else {

      List<CounselingMember> counselingMemberList = (List<CounselingMember>) 
          requestAgent.getObjects(CounselingMember.class);

      for (int i = 0; i < counselingMemberList.size() ; i++) {
        if (counselingMemberList.get(i).getStateLabel2().equals("상관없음") ||
            loginUserSex.equals(counselingMemberList.get(i).getStateLabel2())) {

          System.out.printf("이름 : %s\n"
              + "연락처 : %s\n"
              + "질병여부 : %s\n"
              + "상담내용 : %s\n"
              + "환자가 상담할 분야 : %s\n"
              + "의사성별 : %s\n",
              counselingMemberList.get(i).getName(),
              counselingMemberList.get(i).getPhoneNum(),
              counselingMemberList.get(i).getDisease(),
              counselingMemberList.get(i).getContent(),
              counselingMemberList.get(i).getStateLabel(),
              counselingMemberList.get(i).getStateLabel2()
              );      
          System.out.println();
        }
      }
    }
  }
}
