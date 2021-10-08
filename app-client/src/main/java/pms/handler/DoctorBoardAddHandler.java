package pms.handler;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import pms.domain.DoctorBoard;
import pms.domain.Member;
import request.RequestAgent;
import util.Prompt;

public class DoctorBoardAddHandler implements Command {

  RequestAgent requestAgent;

  public DoctorBoardAddHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[Healer 지식in 글쓰기] 페이지입니다.");
    System.out.println();
    DoctorBoard doctorBoard = new DoctorBoard();
    String loginUser = AuthLoginHandler.getLoginUser().getId();

    requestAgent.request("doctorBoard.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      DoctorBoard.lastIndex = 1;
      doctorBoard.setNo(DoctorBoard.lastIndex);

    } else {

      List<DoctorBoard> doctorBoardList = (List<DoctorBoard>) requestAgent.getObjects(DoctorBoard.class);

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

    if(AuthLoginHandler.getLoginUser().getCount() < 10) {
      System.out.println("포인트가 부족하여 게시글을 작성할 수 없습니다.");
      return;
    } else {

      HashMap<String,String> params = new HashMap<>();

      params.put("id", loginUser);
      requestAgent.request("member.selectOneById", params);

      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println("해당 회원이 없습니다.");
        return;
      }

      Member member = requestAgent.getObject(Member.class);

      member.setCount(member.getCount() - 10);

      requestAgent.request("member.update", member);

      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println("포인트 차감 실패!");
        return;
      }

      //      AuthLoginHandler.getLoginUser().setCount(AuthLoginHandler.getLoginUser().getCount()-10);
      System.out.println("게시글을 작성하여 10포인트가 사용되었습니다.");
    }

    requestAgent.request("doctorBoard.insert", doctorBoard);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("게시글 저장 실패!");
      return;
    }

    System.out.println("게시글이 등록되었습니다.");
  }

}
