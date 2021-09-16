package pms.handler;

import java.util.List;
import pms.domain.FreeBoard;
import pms.domain.MailBox;

public class MailBoxAutoSendHandler extends AbstractMailBoxHandler {

  List<FreeBoard> freeBoardList;

  public MailBoxAutoSendHandler(List<MailBox> mailBoxList, List<FreeBoard> freeBoardList) {
    super(mailBoxList);
    this.freeBoardList = freeBoardList;
  }

  @Override
  public void execute() {
    System.out.println();
    MailBox mailBox = new MailBox();

    mailBox.setTitle("신고하신 게시물이 삭제되었습니다.");
    mailBox.setContent("요청하신 게시물은 삭제되었습니다. 이용해 주셔서 감사합니다.");
    
    mailBoxList.add(mailBox);
  }

}
