package pms.handler;

import java.sql.Date;
import java.util.List;
import pms.dao.FreeBoardDao;
import pms.domain.DoctorBoard;
import pms.domain.FreeBoard;
import util.Prompt;

public class FreeBoardAddHandler implements Command{

  FreeBoardDao freeBoardDao;

  public FreeBoardAddHandler(FreeBoardDao freeBoardDao) {
    this.freeBoardDao = freeBoardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[자유게시판 글쓰기] 페이지입니다.");
    System.out.println();
    FreeBoard freeBoard = new FreeBoard();

    List<FreeBoard> freeBoardList = freeBoardDao.findAll();

    if (freeBoardList == null) {
      FreeBoard.lastIndex = 1;
      freeBoard.setNo(FreeBoard.lastIndex);

    } else {

      if(FreeBoard.lastIndex != freeBoardList.size()) {

        FreeBoard.lastIndex = freeBoardList.get(freeBoardList.size()-1).getNo();
        freeBoard.setNo(++DoctorBoard.lastIndex);

      } else {
        freeBoard.setNo(++DoctorBoard.lastIndex);
      }
    }

    while(true) {

      freeBoard.setTitle(Prompt.inputString("제목> "));
      if (freeBoard.getTitle().trim().equals("")) {
        System.out.println("제목이 공백입니다. 다시 입력해주세요.");
      }
      else {
        break;
      }
    }
    while(true) {
      freeBoard.setContent(Prompt.inputString("내용> "));
      if (freeBoard.getContent().trim().equals("")) {
        System.out.println("내용이 공백입니다. 다시 입력해주세요.");
      } else {
        break;
      }
    }

    freeBoard.setWriter(AuthLoginHandler.getLoginUser());
    freeBoard.setRegisteredDate(new Date(System.currentTimeMillis()));
    freeBoard.setWhichBoard("free");

    freeBoardDao.insert(freeBoard);
    System.out.println("게시글이 등록되었습니다.");
  }

}