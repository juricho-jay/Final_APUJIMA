package apus.handler;

import java.util.List;
import apus.dao.CounselingDao;
import apus.domain.Counseling;
import apus.domain.Member;

public class CounselingMemberDoctorListHandler implements Command {

  CounselingDao counselingDao;

  public CounselingMemberDoctorListHandler(CounselingDao counselingDao) {
    this.counselingDao = counselingDao;
  }
  @Override
  public void execute(CommandRequest request) throws Exception {
    Member loginUser = AuthLoginHandler.getLoginUser();
    System.out.println();
    System.out.println("[상담신청 이력] 페이지입니다.");

    List<Counseling> counselingList= counselingDao.findAll();

    for (int i = 0; i < counselingList.size() ; i++) {
      if (counselingList.get(i).getCounselor().getId().equals(AuthLoginHandler.getLoginUser().getId())) {

        System.out.printf("상담 신청자 : %s\n"
            + "연락처 : %s\n"
            + "질병여부 : %s\n"
            + "상담내용 : %s\n",
            counselingList.get(i).getClient().getName(),
            counselingList.get(i).getClient().getPhoneNum(),
            counselingList.get(i).getDisease(),
            counselingList.get(i).getContent()
            );      
        System.out.println();
      }
    }
  }
}
