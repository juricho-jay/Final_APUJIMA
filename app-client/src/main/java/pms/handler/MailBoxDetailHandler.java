package pms.handler;

import java.util.List;
import pms.dao.MailBoxDao;
import pms.domain.MailBox;
import util.Prompt;

public class MailBoxDetailHandler  implements Command{
  MailBoxDao mailBoxDao;

  public MailBoxDetailHandler(MailBoxDao mailBoxDao) {
    this.mailBoxDao = mailBoxDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[상세보기] 페이지입니다.");
    System.out.println();

    List<MailBox> mailBoxList=  mailBoxDao.findAll();
    int count =0;
    if (mailBoxList == null) {
      System.out.println("쪽지함이 비어 상세보기를 할 수 없습니다.");
      return;
    } else {

      int no = Prompt.inputInt("쪽지 번호> ");

      for (MailBox mailBox : mailBoxList) {
        if(mailBox.getReceiver().equals(AuthLoginHandler.getLoginUser().getId()) && mailBox.getNo() == no) {

          System.out.printf("제목 : %s\n", mailBox.getTitle());
          System.out.printf("내용 : %s\n", mailBox.getContent());
          System.out.printf("보낸이 : %s\n", mailBox.getSender()); // 우리는 익명이기 때문에 Id로
          System.out.printf("수신자 : %s\n", AuthLoginHandler.getLoginUser().getId());
          System.out.printf("보낸 날짜 : %s\n",mailBox.getSendingTime());
          System.out.println();
          count ++ ;
        }
      }
      if (count == 0) {
        System.out.println("받은 쪽지가 없어 상세보기를 할 수 없습니다.");
      }

    }

  }
}
