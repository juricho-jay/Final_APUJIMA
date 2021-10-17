package pms.handler;

import java.util.List;
import pms.dao.MailBoxDao;
import pms.domain.MailBox;

public class MailBoxListHandler  implements Command{
  MailBoxDao mailBoxDao;

  public MailBoxListHandler(MailBoxDao mailBoxDao) {
    this.mailBoxDao = mailBoxDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[쪽지 목록]");
    System.out.println();
    int count = 0;

    List<MailBox> mailBoxList = mailBoxDao.findAll();


    if (mailBoxList == null) {
      System.out.println("쪽지함이 비어 있습니다.");
      return;
    }


    for (MailBox mailBox : mailBoxList) {
      if(mailBox.getReceiver().equals(AuthLoginHandler.getLoginUser().getId())) {
        //  System.out.printf("이 글의 실행 조건은 %d번의 메일박스의 수신자 아이디가 로그인 아이디와 같을때\n", mailBox.getMailNo());
        //    requestAgent.request("mailBox.update", mailBox);

        System.out.printf("쪽지 번호 : %d\n"
            +"보낸 사람 : %s\n"
            +"쪽지 제목 : %s\n",
            mailBox.getNo(),
            mailBox.getSender(),
            mailBox.getTitle()); 
        count++;
        System.out.println();
      }
    }
    if (count == 0 ){
      System.out.println("받은 쪽지가 없습니다.");
    }
  }
}
