package pms.handler;

import java.sql.Date;
import java.util.List;
import pms.domain.FreeBoard;
import util.Prompt;

public class FreeBoardAddHandler extends AbstractFreeBoardHandler{


  public FreeBoardAddHandler(List<FreeBoard> freeBoardList) {
    super(freeBoardList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println();
    System.out.println("[자유게시판 글쓰기] 페이지입니다.");
    System.out.println();
    FreeBoard freeBoard = new FreeBoard();

    if (freeBoardList.size() == 0) { // 게시물 0번 일 때,
      freeBoard.setNo(FreeBoard.lastIndex++);
      return;
    } else if (freeBoardList.size() > 0) { // 게시물이 1번 이상일 때
      for (FreeBoard freeBoardNo : freeBoardList) { // 리스트를 불러와서 반복한다.
        //        FreeBoard.index = FreeBoard.lastIndex;
        if (freeBoardList.size() - 1 == FreeBoard.lastIndex) { // 마지막번호와 사이즈가 다르면
          freeBoardNo.setNo(FreeBoard.index++);
        }
      }
      FreeBoard.lastIndex = freeBoard.getNo();
    } 
    freeBoard.setTitle(Prompt.inputString("제목> "));
    freeBoard.setContent(Prompt.inputString("내용> "));


    freeBoard.setWriter(AuthLoginHandler.getLoginUser());
    freeBoard.setRegisteredDate(new Date(System.currentTimeMillis()));

    freeBoardList.add(freeBoard);
    System.out.println("게시판 등록이 완료되었습니다.");
  }

}