package pms.page;

import util.Prompt;

public class DoComunityMenu {


  void doCommunityMenu() {
    System.out.println();
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
      doMainMenu2();
    }

  }
}
