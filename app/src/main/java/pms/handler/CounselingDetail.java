package pms.handler;

import java.util.List;
import pms.domain.CounselingMember;

public class CounselingDetail extends AbstractCounselingMember{

  public CounselingDetail(List<CounselingMember> counselingmemberList) {
    super(counselingmemberList);
  }

  public void counselingDetail() {

    System.out.println();
    System.out.println("[상담신청 이력]");
    if(counselingmemberList.size() == 0) {
      System.out.println("상담신청 내역이 없습니다.");
      return;
    }

    for (int i = 0; i < counselingmemberList.size() ; i++) {
      System.out.printf("성함 : %s\n"
          + "연락처 : %s\n"
          + "질병여부 : %s\n"
          + "상담내용 : %s\n"
          + "환자가 상담할 분야 : %s\n"
          + "의사성별 : %s\n",
          counselingmemberList.get(i).getName(),
          counselingmemberList.get(i).getTel(),
          counselingmemberList.get(i).getDisease(),
          counselingmemberList.get(i).getContent(),
          counselingmemberList.get(i).getStateLabel(),
          counselingmemberList.get(i).getStateLabel2()

          );      
    }
  }


}
