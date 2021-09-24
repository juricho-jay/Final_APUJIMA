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
<<<<<<< HEAD
    int temp = 0;
    int count = 0;

    if(freeBoardList.size() == 0) {
      FreeBoard.lastIndex = 1;
      freeBoard.setNo(FreeBoard.lastIndex);
    } else {
      if(FreeBoard.lastIndex != freeBoardList.size()) {

        FreeBoard.lastIndex = freeBoardList.get(freeBoardList.size()-1).getNo();

        freeBoard.setNo(++FreeBoard.lastIndex);
      } else if(FreeBoard.lastIndex == 1){

        freeBoard.setNo(++FreeBoard.lastIndex);
        //      if(count >= 1) {
        //        temp = FreeBoard.lastIndex;
        //        count++;
        //        freeBoard.setNo(temp++);
        //      } else {
        //        FreeBoard.lastIndex = freeBoardList.get(freeBoardList.size()-1).getNo()+1;
        //        freeBoard.setNo(FreeBoard.lastIndex++);
        //      freeBoard.setNo(FreeBoard.getLastIndex()+1);
        //
        //  FreeBoard.lastIndex = freeBoard.getNo();
        //freeBoard.setNo(FreeBoard.lastIndex);
        //temp = FreeBoard.lastIndex + 1;
        //   count++;
      }

    }
    freeBoard.setTitle(Prompt.inputString("제목> "));
    freeBoard.setContent(Prompt.inputString("내용> "));
=======


    if (FreeBoard.lastIndex == 1) {
      freeBoard.setNo(FreeBoard.lastIndex);
      FreeBoard.lastIndex++;
    }else {
      freeBoard.setNo(FreeBoard.lastIndex++);
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
>>>>>>> 0be11f80701837cbc91025c30f1a4c4adf120501

    freeBoard.setWriter(AuthLoginHandler.getLoginUser());
    freeBoard.setRegisteredDate(new Date(System.currentTimeMillis()));

    freeBoardList.add(freeBoard);
    System.out.println("게시판 등록이 완료되었습니다.");
  }

}