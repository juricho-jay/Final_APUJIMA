package pms.handler;

import java.util.List;
import pms.domain.MailBox;
import util.Prompt;

public class MailBoxDeleteHandler extends AbstractMailBoxHandler{
  public MailBoxDeleteHandler(List<MailBox> mailBoxList) {
    super(mailBoxList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[삭제] 페이지입니다.");
    System.out.println();
    int no = Prompt.inputInt("쪽지 번호> ");

    MailBox mailBox = findByNo(no);

    if (mailBox == null) {
      System.out.println("해당 번호의 쪽지가 없습니다.");
      return;
    }


    String input = Prompt.inputString(" ❗ 정말 삭제하시겠습니까? (y/N)> ");
    if(input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("쪽지 삭제를 취소하였습니다.");
      return;
    }

    mailBoxList.remove(mailBox);
    System.out.println("쪽지를 삭제하였습니다.");
  }

}
