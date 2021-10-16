package pms.handler;

import java.util.List;
import pms.dao.MailBoxDao;
import pms.domain.MailBox;
import util.Prompt;

public class MailBoxDeleteHandler implements Command{
  MailBoxDao mailBoxDao;

  public MailBoxDeleteHandler(MailBoxDao mailBoxDao) {
    this.mailBoxDao = mailBoxDao;
  }
  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[쪽지 삭제] 페이지입니다.");
    System.out.println();
    while(true) {
      int mailNo = 0;
      int count = 0;

      List<MailBox> mailBoxList = mailBoxDao.findAll();


      if (mailBoxList == null) {
        System.out.println("쪽지함이 비어 있습니다.");
        return;
      }


      for (MailBox mailBox : mailBoxList) {
        if(mailBox.getReceiver().equals(AuthLoginHandler.getLoginUser().getId())) {
          mailNo++;
          mailBox.setMailNo(mailNo);
          mailBoxDao.update(mailBox);

          System.out.printf("쪽지 번호 : %d\n"
              +"보낸 사람 : %s\n"
              +"쪽지 제목 : %s\n",
              mailBox.getMailNo(),
              mailBox.getSender(),
              mailBox.getTitle()); 

          count++;
          System.out.println();

        }
      }

      if (count == 0 ){
        System.out.println("받은 쪽지가 없습니다.");
        return;
      }


      int no = Prompt.inputInt("쪽지 번호 > ");


      String input = Prompt.inputString(" ❗ 정말 삭제하시겠습니까? (y/N)> ");
      if(input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("쪽지 삭제를 취소하였습니다.");
        return;
      }

      for (MailBox mailBox : mailBoxList) {
        if(mailBox.getReceiver().equals(AuthLoginHandler.getLoginUser().getId()) && mailBox.getNo() == no) {
          mailBoxDao.delete(no);

        }
      }

      System.out.println("선택한 쪽지를 삭제하였습니다.");
      return;
    } 
  }
}




