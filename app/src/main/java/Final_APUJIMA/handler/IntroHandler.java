package Final_APUJIMA.handler;

import Final_APUJIMA.util.Prompt;

public class IntroHandler {

  void doIntroMenu() {
    while(true) {
      System.out.println("[소개] 페이지입니다.");
      System.out.println("We always with you, 모든 사람들이 아프지 않길 바라는 커뮤니티, "
          + "APUJIMA입니다.");
      System.out.println();

      System.out.println("0) 뒤로가기");
      int num = Prompt.inputInt("번호> ");
      if(num == 0)
        return;
    }
  }

}
