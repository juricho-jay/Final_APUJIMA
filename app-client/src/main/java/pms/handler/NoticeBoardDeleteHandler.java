package pms.handler;

import pms.dao.NoticeBoardDao;
import pms.domain.NoticeBoard;
import util.Prompt;

public class NoticeBoardDeleteHandler implements Command {

  NoticeBoardDao noticeBoardDao;


  public NoticeBoardDeleteHandler(NoticeBoardDao noticeBoardDao) {
    this.noticeBoardDao = noticeBoardDao;
  }


  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[삭제] 페이지입니다.");
    System.out.println();

    int no = (int)request.getAttribute("no");

    NoticeBoard noticeBoard = noticeBoardDao.findByNo(no);

    if (noticeBoard == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    if (!noticeBoard.getWriter().getId().equals(AuthLoginHandler.getLoginUser().getId())) {
      System.out.println("삭제 권한이 없습니다.");
      return;
    }

    String input = Prompt.inputString(" ❗ 정말 삭제하시겠습니까? (y/N)> ");
    if(input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("게시글 삭제를 취소하였습니다.");
      return;
    }

    request.getRequestDispatcher("/comment/autoDelete").forward(request);
    request.getRequestDispatcher("/like/autoDelete").forward(request);

    noticeBoardDao.delete(no);

    System.out.println("게시글을 삭제하였습니다.");
  }
}
