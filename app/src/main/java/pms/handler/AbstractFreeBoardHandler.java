package pms.handler;

import java.util.List;
import pms.domain.FreeBoard;

public abstract class AbstractFreeBoardHandler implements Command{

  List<FreeBoard> freeBoardList; 

  public AbstractFreeBoardHandler(List<FreeBoard> freeBoardList) {
    this.freeBoardList = freeBoardList; // => ?? 생성자 초기화
  }

  protected FreeBoard findByNo(int no) {
    for (FreeBoard freeBoard : freeBoardList) {
      if (freeBoard.getNo() == no) {
        return freeBoard;
      }
    }
    return null;
  }

}
