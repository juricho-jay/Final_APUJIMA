package pms.handler;

import java.util.List;
import pms.domain.MailBox;

public class MailBoxListHandler extends AbstractMailBoxHandler {

  public MailBoxListHandler(List<MailBox> mailBoxList) {
    super(mailBoxList);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("[쪽지 목록]");
    System.out.println();
    for(int i = 0; i< mailBoxList.size(); i++) {
      System.out.println("보낸 사람 :"  + mailBoxList.get(i).getSender());
      System.out.println("쪽지 제목 :"  + mailBoxList.get(i).getTitle());
    }
  }


}
