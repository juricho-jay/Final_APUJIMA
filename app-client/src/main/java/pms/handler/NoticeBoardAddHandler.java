package pms.handler;

import java.sql.Date;
import java.util.List;
import pms.dao.NoticeBoardDao;
import pms.domain.NoticeBoard;
import util.Prompt;

public class NoticeBoardAddHandler implements Command {

  NoticeBoardDao noticeBoardDao;


  public NoticeBoardAddHandler(NoticeBoardDao noticeBoardDao) {
    this.noticeBoardDao = noticeBoardDao;

  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[글쓰기] 페이지입니다.");
    System.out.println();
    NoticeBoard noticeBoard = new NoticeBoard();

    List<NoticeBoard> noticeBoardList = noticeBoardDao.findAll();


    if (noticeBoardList.size() == 0) {
      NoticeBoard.lastIndex = 1;
      noticeBoard.setNo(NoticeBoard.lastIndex);

    }

    else {

      if(NoticeBoard.lastIndex != noticeBoardList.size()) {

        NoticeBoard.lastIndex = noticeBoardList.get(noticeBoardList.size()-1).getNo();
        noticeBoard.setNo(++NoticeBoard.lastIndex);

      } else {
        noticeBoard.setNo(++NoticeBoard.lastIndex);
      }
    }
    while(true) {

      noticeBoard.setTitle(Prompt.inputString("제목> "));
      if (noticeBoard.getTitle().trim().equals("")) {
        System.out.println("제목이 공백입니다. 다시 입력해주세요.");
      }
      else {
        break;
      }
    }
    while(true) {
      noticeBoard.setContent(Prompt.inputString("내용> "));
      if (noticeBoard.getContent().trim().equals("")) {
        System.out.println("내용이 공백입니다. 다시 입력해주세요.");
      } else {
        break;
      }
    }

    noticeBoard.setWriter(AuthLoginHandler.getLoginUser());
    noticeBoard.setRegisteredDate(new Date(System.currentTimeMillis()));
    noticeBoard.setWhichBoard("notice");

    noticeBoardDao.insert(noticeBoard);
    System.out.println("게시글이 등록되었습니다.");
  }

}
