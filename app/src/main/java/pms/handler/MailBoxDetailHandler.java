package pms.handler;

import java.util.List;
import pms.domain.MailBox;
import util.Prompt;

public class MailBoxDetailHandler extends AbstractMailBoxHandler {

  public MailBoxDetailHandler(List<MailBox> mailBoxList) {
    super(mailBoxList);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void execute() {

    System.out.println("[상세보기] 페이지입니다.");
    System.out.println();
    int no = Prompt.inputInt("쪽지 번호> ");

    MailBox mailBox = findByNo(no);

    if (mailBox == null) {
      System.out.println("해당 번호의 쪽지가 없습니다.");
      return;
    }

    System.out.printf("제목 : %s\n", mailBox.getTitle());
    System.out.printf("내용 : %s\n", mailBox.getContent());
    System.out.printf("보낸이 : %s\n", mailBox.getSender()); // 우리는 익명이기 때문에 Id로
    System.out.printf("수신자 : %s\n", AuthLoginHandler.getLoginUser().getId());
    System.out.printf("보낸 시간 : %s\n",mailBox.getSendingTime());

  }
}
