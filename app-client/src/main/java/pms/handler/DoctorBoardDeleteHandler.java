package pms.handler;

import pms.dao.DoctorBoardDao;
import pms.domain.DoctorBoard;
import util.Prompt;

public class DoctorBoardDeleteHandler implements Command {

  DoctorBoardDao doctorBoardDao;

  public DoctorBoardDeleteHandler(DoctorBoardDao doctorBoardDao) {
    this.doctorBoardDao = doctorBoardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[삭제] 페이지입니다.");
    System.out.println();

    int no = (int)request.getAttribute("no"); // 게시판 번호

    DoctorBoard doctorBoard = doctorBoardDao.findByNo(no);

    if (doctorBoard == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }


    if (!doctorBoard.getWriter().getId().equals(AuthLoginHandler.getLoginUser().getId()) ) {
      System.out.println("삭제 권한이 없습니다.");
      return;
    }
    // 사실 이것도 지워도 될 듯 강사님께 여쭤보고 지우기( == 이거는 인스턴스가 달라서
    // 다르게 나오는건지

    String input = Prompt.inputString(" ❗ 정말 삭제하시겠습니까? (y/N)> ");
    if(input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("게시글 삭제를 취소하였습니다.");
      return;
    }

    request.getRequestDispatcher("/comment/autoDelete").forward(request);
    request.getRequestDispatcher("/like/autoDelete").forward(request);

    doctorBoardDao.delete(no);
    System.out.println("게시글을 삭제하였습니다.");

  }

}

