package pms.handler;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import pms.dao.FreeBoardDao;
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

    //프리보드리스트가 0일 때 lastIndext = 1로 지정하고 사이즈가 1이 아닐 때
    Collection<FreeBoard> freeBoardList = freeBoardDao.findAll();

    if (freeBoardList.size() == 0) {
      FreeBoard.lastIndex = 1;
      freeBoard.setNo(FreeBoard.lastIndex);
    } else if(FreeBoard.lastIndex != freeBoardList.size()) {

      FreeBoard.lastIndex = ((List<FreeBoard>) freeBoardList).get(freeBoardList.size()-1).getNo();
      freeBoard.setNo(++FreeBoard.lastIndex);

    } else {
      freeBoard.setNo(++FreeBoard.lastIndex);
    }

    //    requestAgent.request("freeBoard.selectList", null);
    //
    //    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      FreeBoard.lastIndex = 1;
    //      freeBoard.setNo(FreeBoard.lastIndex);
    //    }
    //
    //    else {
    //      List<FreeBoard> freeBoardList = (List<FreeBoard>) requestAgent.getObjects(FreeBoard.class);
    //
    //      if(FreeBoard.lastIndex != freeBoardList.size()) {
    //
    //        FreeBoard.lastIndex = freeBoardList.get(freeBoardList.size()-1).getNo();
    //        freeBoard.setNo(++FreeBoard.lastIndex);
    //
    //      } else {
    //        freeBoard.setNo(++FreeBoard.lastIndex);
    //      }
    //    }

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

    System.out.println("게시글을 저장했습니다.");
  }

}