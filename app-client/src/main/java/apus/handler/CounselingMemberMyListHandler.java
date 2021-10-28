package apus.handler;

import java.util.List;
import apus.dao.CounselingDao;
import apus.dao.MemberDao;
import apus.domain.Counseling;
import apus.domain.Member;

public class CounselingMemberMyListHandler implements Command {

  CounselingDao counselingDao;
  MemberDao memberDao;

  public CounselingMemberMyListHandler(CounselingDao counselingDao, MemberDao memberDao) {
    this.counselingDao = counselingDao;
    this.memberDao = memberDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    Member loginUser = AuthLoginHandler.getLoginUser();
    System.out.println();
    System.out.println("[상담신청 이력] 페이지입니다.");
    int cnt = 0;


    List<Counseling> counselingList = counselingDao.findAll();

    if(counselingList.size() == 0) {
      System.out.println("아무도 신청 안했어!");
      return;
    }
    Counseling counsel = new Counseling();

    for (int i = 0; i < counselingList.size() ; i++) {
      if(counselingList.get(i).getClient().getId().equals(loginUser.getId())) {
        cnt++;
        counsel = counselingList.get(i);
        System.out.printf("이름: %s\n", counsel.getClient().getName());
        System.out.printf("연락처: %s\n", counsel.getClient().getPhoneNum());
        System.out.printf("지병여부 : %s\n", counsel.getDisease());
        System.out.printf("상담내용 : %s\n", counsel.getContent());
        System.out.printf("상담사 이름 : %s\n", counsel.getCounselor().getName());
        Member m = memberDao.findDoctorMajor(counsel.getCounselor());
        System.out.printf("상담사 전문분야 : %s\n", m.getDoctor().getMajor());
        System.out.println();
      } 
    }


    if (cnt == 0) {
      System.out.println("상담신청 내역이 없습니다.");
      return;
    }

  }
}
