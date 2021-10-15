package pms.handler;

import pms.dao.DoctorBoardDao;
import pms.domain.DoctorBoard;
import util.Prompt;

public class DoctorBoardUpdateHandler implements Command {

  DoctorBoardDao doctorBoardDao;

  public DoctorBoardUpdateHandler(DoctorBoardDao doctorBoardDao) {
    this.doctorBoardDao = doctorBoardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[게시글 변경] 페이지입니다.");
    System.out.println();
    int no = (int)request.getAttribute("no");

    //    HashMap<String,String> params = new HashMap<>();
    //    params.put("no", String.valueOf(no));
    //    requestAgent.request("doctorBoard.selectOne", params);

    DoctorBoard doctorBoard = doctorBoardDao.findByNo(no);

    if (doctorBoard == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    String title = Prompt.inputString(String.format("제목(%s)> ", doctorBoard.getTitle()));
    String content = Prompt.inputString(String.format("내용(%s)> ", doctorBoard.getContent()));

    String input = Prompt.inputString("❗ 정말 변경하시겠습니까? (y/N)> ");
    if(input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("게시글 변경을 취소하였습니다.");
      return;
    }

    doctorBoard.setTitle(title);
    doctorBoard.setContent(content);
    doctorBoardDao.update(doctorBoard);


    System.out.println("게시글을 변경하였습니다.");

  }
}

