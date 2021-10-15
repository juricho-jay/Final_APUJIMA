package pms.handler;

import java.sql.Date;
import java.util.List;
import pms.dao.DoctorBoardDao;
import pms.dao.MemberDao;
import pms.domain.DoctorBoard;
import pms.domain.Member;
import util.Prompt;

public class DoctorBoardAddHandler implements Command {

  DoctorBoardDao doctorBoardDao;
  MemberDao memberDao;

  public DoctorBoardAddHandler(DoctorBoardDao doctorBoardDao, MemberDao memberDao) {
    this.doctorBoardDao = doctorBoardDao;
    this.memberDao = memberDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[Healer 지식in 글쓰기] 페이지입니다.");
    System.out.println();
    DoctorBoard doctorBoard = new DoctorBoard();
    String loginUser = AuthLoginHandler.getLoginUser().getId();

    List<DoctorBoard> doctorBoardList = doctorBoardDao.findAll();

    if (doctorBoardList.size() == 0) {
      DoctorBoard.lastIndex = 1;
      doctorBoard.setNo(DoctorBoard.lastIndex);

    } else {

      if(DoctorBoard.lastIndex != doctorBoardList.size()) {

        DoctorBoard.lastIndex = doctorBoardList.get(doctorBoardList.size()-1).getNo();
        doctorBoard.setNo(++DoctorBoard.lastIndex);

      } else {
        doctorBoard.setNo(++DoctorBoard.lastIndex);
      }
    }

    while(true) {

      doctorBoard.setTitle(Prompt.inputString("제목> "));
      if (doctorBoard.getTitle().trim().equals("")) {
        System.out.println("제목이 공백입니다. 다시 입력해주세요.");
      }
      else {
        break;
      }
    }

    while(true) {
      doctorBoard.setContent(Prompt.inputString("내용> "));
      if (doctorBoard.getContent().trim().equals("")) {
        System.out.println("내용이 공백입니다. 다시 입력해주세요.");
      } else {
        break;
      }
    }

    doctorBoard.setWriter(AuthLoginHandler.getLoginUser());
    doctorBoard.setRegisteredDate(new Date(System.currentTimeMillis()));
    doctorBoard.setWhichBoard("doctor");

    if(AuthLoginHandler.getLoginUser().getPoint() < 10) {
      System.out.println("포인트가 부족하여 게시글을 작성할 수 없습니다.");
      return;
    } else {

      //      HashMap<String,String> params = new HashMap<>();
      //      params.put("id", loginUser);
      //      requestAgent.request("member.selectOneById", params);

      Member member = memberDao.findById(loginUser);

      if (member.equals(null)) {
        System.out.println("해당 회원이 없습니다.");
        return;
      }

      member.setPoint(member.getPoint() - 10);

      memberDao.update(member);

      System.out.println("게시글을 작성하여 10포인트가 사용되었습니다.");
    }

    doctorBoardDao.insert(doctorBoard);
    System.out.println("게시글이 등록되었습니다.");
  }

}
