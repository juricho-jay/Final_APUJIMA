package pms.page;

import pms.handler.FreeBoardAdd;
import pms.handler.FreeBoardDetail;
import pms.handler.FreeBoardList;
import util.Prompt;

public class DoFreeBoardMenu {

  FreeBoardAdd freeBoardAdd;
  FreeBoardList freeBoradList;
  FreeBoardDetail freeBoradDetail;

  public void FreeboardMenu() {

    while(true) {
      System.out.println();
      System.out.println("[APUs 자유게시판] 페이지입니다.");
      System.out.println();
      System.out.println("1) 글쓰기");
      System.out.println("2) 글목록");
      System.out.println("3) 글상세");
      //      System.out.println("4) 글수정");
      //      System.out.println("5) 글삭제");
      System.out.println("0) 뒤로가기");
      int select = Prompt.inputInt("선택> ");
      try {
        if (select == 1) {
          freeBoardAdd.add();
        } else if (select == 2){
          freeBoradList.list();
        } else if (select == 3) {
          freeBoradDetail.detail();
        } /*else if (select == 4) {
          freeboardHandler.update();
        } else if (select == 5) {
          freeboardHandler.delete();
        }*/ else if (select == 0) {
          return;
        }
      }catch(Exception e) {

      }

    }
  }


}
