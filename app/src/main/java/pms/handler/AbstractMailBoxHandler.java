package pms.handler;

import java.util.List;
import pms.domain.MailBox;

public abstract class AbstractMailBoxHandler implements Command{
  List<MailBox> mailBoxList; 

  public AbstractMailBoxHandler(List<MailBox> mailBoxList) {
    this.mailBoxList = mailBoxList; // => ?? 생성자 초기화
  }



}
