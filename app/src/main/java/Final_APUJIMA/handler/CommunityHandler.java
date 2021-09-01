package Final_APUJIMA.handler;

import Final_APUJIMA.Main2;
import Final_APUJIMA.util.Prompt;

public class CommunityHandler {

  FreeBoardHandler freeBoardHandler;
  Main2 main2;


  //ID찾기메뉴 메서드
  public void doCommunityMenu() {
    System.out.println("[커뮤니티] 페이지입니다. 선택해주세요.");
    System.out.println();
    System.out.println("1) 공지사항");
    System.out.println("2) APUs 자유게시판");
    System.out.println("3) Healer 지식in");
    System.out.println("0) 뒤로가기");
    int select = Prompt.inputInt("메뉴를 선택해주세요>  ");

    if (select == 1) {
      NoticeMenu();
    } else if(select == 2){
      FreeboardMenu();
    } else if(select == 3) {
      knowledgeMenu();
    } else if(select == 0) {
      main2.doMainMenu2();
    }

  }

  void NoticeMenu() {
    System.out.println("[공지사항] 페이지입니다.");
  }
  //자유게시판 관련 method.
  private void FreeboardMenu() {
    System.out.println("[APUs 자유게시판] 페이지입니다.");
    System.out.println();
    System.out.println("1) 글쓰기");
    System.out.println("2) 글목록");
    System.out.println("3) 글상세");
    System.out.println("4) 글수정");
    System.out.println("5) 글삭제");
    System.out.println("0) 뒤로가기");
    int select = Prompt.inputInt("선택> ");
    try {
      if (select == 1) {
        freeBoardHandler.add();
      } else if (select == 2){
        freeBoardHandler.list();
      } else if (select == 3) {
        freeBoardHandler.detail();
      } else if (select == 4) {
        freeBoardHandler.update();
      } else if (select == 5) {
        freeBoardHandler.delete();
      } else if (select == 0) {
        doCommunityMenu();
      }
    }catch(Exception e) {

    }

  }
  //Healer 지식in method.
  void knowledgeMenu() {
    System.out.println("[Healer 지식in] 페이지입니다.");
    System.out.println();
    System.out.println("1) 의사");
    System.out.println("2) 일반");
  }

}
