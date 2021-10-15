package pms.handler;

import pms.dao.NoticeBoardDao;
import pms.domain.NoticeBoard;
import util.Prompt;

public class NoticeBoardUpdateHandler implements Command {

  NoticeBoardDao noticeBoardDao;

  public NoticeBoardUpdateHandler(NoticeBoardDao noticeBoardDao) {
    this.noticeBoardDao = noticeBoardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[공지사항 게시글 변경] 페이지입니다.");
    System.out.println();
    int no = (int)request.getAttribute("no");

    NoticeBoard noticeBoard = noticeBoardDao.findByNo(no);

    if (noticeBoard == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    } 


    String title = Prompt.inputString(String.format("제목(%s)> ", noticeBoard.getTitle()));
    String content = Prompt.inputString(String.format("내용(%s)> ", noticeBoard.getContent()));

    String input = Prompt.inputString("❗ 정말 변경하시겠습니까? (y/N)> ");
    if(input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("게시글 변경을 취소하였습니다.");
      return;
    }

    noticeBoard.setTitle(title);
    noticeBoard.setContent(content);
    noticeBoardDao.update(noticeBoard);

    System.out.println("게시글을 변경하였습니다.");
  }
}
