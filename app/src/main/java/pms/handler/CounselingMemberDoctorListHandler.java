package pms.handler;

import java.util.List;
import pms.domain.CounselingMember;

public class CounselingMemberDoctorListHandler extends AbstractCounselingMemberHandler{

  public CounselingMemberDoctorListHandler(List<CounselingMember> counselingMemberList) {
    super(counselingMemberList);
  }

  public void execute() {

    System.out.println();
    System.out.println("[상담신청 이력]");
    if(counselingMemberList.size() == 0) {
      System.out.println("상담신청 내역이 없습니다.");
      return;
    }

    for (int i = 0; i < counselingMemberList.size() ; i++) {
      System.out.printf("이름 : %s\n"
          + "연락처 : %s\n"
          + "질병여부 : %s\n"
          + "상담내용 : %s\n"
          + "환자가 상담할 분야 : %s\n"
          + "의사성별 : %s\n",
          //    counselingMemberList.get(i).getName(),
          //    counselingMemberList.get(i).getTel(),
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
