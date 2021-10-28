package apus.handler;

import java.util.List;
import apus.dao.MailBoxDao;
import apus.domain.MailBox;

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


    if (mailBoxList.size() == 0) {
      System.out.println("쪽지함이 비어 있습니다.");
      return;
    }


    for (MailBox mailBox : mailBoxList) {
      if(mailBox.getReceiver().getId().equals(AuthLoginHandler.getLoginUser().getId())) {

        System.out.printf("보낸 사람 : %s\n"
            +"쪽지 제목 : %s\n",
            mailBox.getSender().getId(),
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
