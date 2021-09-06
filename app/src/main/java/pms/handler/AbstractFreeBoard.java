package pms.handler;

import java.util.List;
import pms.domain.FreeBoard;

public class AbstractFreeBoard {

  List<FreeBoard> freeBoardList; 

  public AbstractFreeBoard(List<FreeBoard> freeBoardList) {
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
