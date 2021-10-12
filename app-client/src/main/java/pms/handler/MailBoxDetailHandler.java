package pms.handler;

import java.util.Collection;
import pms.domain.MailBox;
import request.RequestAgent;
import util.Prompt;

public class MailBoxDetailHandler  implements Command{
  RequestAgent requestAgent;

  public MailBoxDetailHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[상세보기] 페이지입니다.");
    System.out.println();

    requestAgent.request("mailBox.selectList", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("쪽지함이 비어 상세보기를 할 수 없습니다.");
      return;
    } else {

      Collection<MailBox> mailBoxList = requestAgent.getObjects(MailBox.class);
      int no = Prompt.inputInt("쪽지 번호> ");

      for (MailBox mailBox : mailBoxList) {
        if(mailBox.getReceiver().equals(AuthLoginHandler.getLoginUser().getId()) && mailBox.getNo() == no) {

          System.out.printf("제목 : %s\n", mailBox.getTitle());
          System.out.printf("내용 : %s\n", mailBox.getContent());
          System.out.printf("보낸이 : %s\n", mailBox.getSender()); // 우리는 익명이기 때문에 Id로
          System.out.printf("수신자 : %s\n", AuthLoginHandler.getLoginUser().getId());
          System.out.printf("보낸 날짜 : %s\n",mailBox.getSendingTime());
          System.out.println();

        }
      }


    }

  }
}
