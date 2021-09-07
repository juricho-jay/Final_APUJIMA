package pms.handler;

import java.util.List;
import pms.domain.CounselingMember;
import pms.domain.Member;

public class CounselingMemberMyListHandler extends AbstractCounselingMemberHandler {

  public CounselingMemberMyListHandler(List<CounselingMember> counselingMemberList) {
    super(counselingMemberList);
  }


  @Override
  public void execute() {
    Member loginUser = AuthLoginHandler.getLoginUser();

    System.out.println();
    System.out.println("[상담신청 이력] 페이지입니다.");
    if(counselingMemberList.size() == 0) {
      System.out.println("상담신청 내역이 없습니다.");
      return;
    }
    System.out.printf("이름: %s\n", loginUser.getName());
    System.out.printf("연락처: %s\n", loginUser.getPhoneNum());
    for (int i = 0; i < counselingMemberList.size() ; i++) {
      System.out.printf("질병여부 : %s\n"
          + "상담내용 : %s\n"
          + "환자가 상담할 분야 : %s\n"
          + "의사성별 : %s\n",
          counselingMemberList.get(i).getDisease(),
          counselingMemberList.get(i).getContent(),
          counselingMemberList.get(i).getStateLabel(),
          counselingMemberList.get(i).getStateLabel2()
          );      
    }
  }
}