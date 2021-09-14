package pms.handler;

import java.sql.Date;
import java.util.List;
import pms.domain.MailBox;
import pms.domain.Member;
import util.Prompt;

public class MailBoxSendHandler extends AbstractMailBoxHandler{

  Member member;
  public MailBoxSendHandler(List<MailBox> mailBoxList) {
    super(mailBoxList);
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("[쪽지 보내기] 페이지입니다.");
    System.out.println();
    MailBox mailBox = new MailBox();

    mailBox.setMailNo(Prompt.inputInt("쪽지 번호> "));
    mailBox.setReceiver(Prompt.inputString("수신인> "));

    if (mailBox.getReceiver() != (member.getId())) {
      System.out.println();
      System.out.printf("-%s- (이)라는 ID는 찾을 수 없습니다.", mailBox.getReceiver());
    } else return;

    mailBox.setTitle(Prompt.inputString("제목> "));
    mailBox.setContent(Prompt.inputString("내용> "));


    mailBox.setSender(AuthLoginHandler.getLoginUser());
    mailBox.setSendingTime(new Date(System.currentTimeMillis()));

    mailBoxList.add(mailBox);
    System.out.println("쪽지를 보냈습니다.");
  }

}