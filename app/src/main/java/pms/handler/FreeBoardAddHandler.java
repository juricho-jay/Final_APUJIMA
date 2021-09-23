package pms.handler;

import java.sql.Date;
import java.util.List;
import pms.domain.FreeBoard;
import util.Prompt;

public class FreeBoardAddHandler extends AbstractFreeBoardHandler{

  static int lastNo;// 게시물 마지막 번호

  public FreeBoardAddHandler(List<FreeBoard> freeBoardList) {
    super(freeBoardList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println();
    System.out.println("[자유게시판 글쓰기] 페이지입니다.");
    System.out.println();
    FreeBoard freeBoard = new FreeBoard();

    if (freeBoardList.size() == 0) {// 게시물이 0개일 경우
      //      lastNo = FreeBoard.lastIndex;
      freeBoard.setNo(FreeBoard.lastIndex);
      FreeBoard.lastIndex++;
      //      lastNo = FreeBoard.lastIndex;
      return;
    } else { // 게시물 1개 이상일 경우
      if (freeBoard.getNo() == freeBoardList.size()) { // 현재 게시물번호와 총게시물개수와 같다면
        FreeBoard.lastIndex = freeBoard.getNo();
        lastNo = FreeBoard.lastIndex;
        lastNo++;
        FreeBoard.lastIndex = lastNo;
        freeBoard.setNo(FreeBoard.lastIndex);
        FreeBoard.lastIndex++;
      }

    }
    freeBoard.setTitle(Prompt.inputString("제목> "));
    freeBoard.setContent(Prompt.inputString("내용> "));


    freeBoard.setWriter(AuthLoginHandler.getLoginUser());
    freeBoard.setRegisteredDate(new Date(System.currentTimeMillis()));

    freeBoardList.add(freeBoard);
    System.out.println("게시판 등록이 완료되었습니다.");
  }

}