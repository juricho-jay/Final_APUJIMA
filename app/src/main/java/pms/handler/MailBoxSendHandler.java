package pms.handler;

import java.sql.Date;
import java.util.List;
import pms.domain.MailBox;
import pms.domain.Member;
import util.Prompt;
import pms.handler.MemberPrompt;

public class MailBoxSendHandler extends AbstractMailBoxHandler {
  List<Member> memberList;
<<<<<<< HEAD
  public MailBoxSendHandler(List<MailBox> mailBoxList ,  List<Member> memberList) {
=======
  MemberPrompt memberPrompt;

  public MailBoxSendHandler(List<MailBox> mailBoxList, List<Member> memberList) {
>>>>>>> ca788885b7b606093eb32fc4621700eec49f018b
    super(mailBoxList);
    this.memberList = memberList;
  }

  @Override
  public void execute() {
    Member loginUser = AuthLoginHandler.getLoginUser();
    System.out.println();
    System.out.println("[쪽지 전송] 페이지입니다.");
    System.out.println();
    MailBox mailBox = new MailBox();
<<<<<<< HEAD

    mailBox.setReceiver(Prompt.inputString("수신인> "));

    for (int i = 0; i < memberList.size(); i++) {
      if (mailBox.getReceiver().equalsIgnoreCase(memberList.get(i).getId())) {
=======
    //    mailBox.setMailNo(Prompt.inputInt("쪽지 번호> "));
    String id = Prompt.inputString("수신인> ");

    Member member = memberPrompt.findById(id);
>>>>>>> ca788885b7b606093eb32fc4621700eec49f018b

    if (member == null) {
      System.out.println();
      System.out.printf("-%s- (이)라는 ID는 찾을 수 없습니다.",id);
    } else {


<<<<<<< HEAD
        mailBox.setSender(AuthLoginHandler.getLoginUser().getId());
        mailBox.setSendingTime(new Date(System.currentTimeMillis()));

        String input = Prompt.inputString("쪽지를 전송하시겠습니까? (y/N)> ");
        if(input.equalsIgnoreCase("n") || input.length() == 0) {
          System.out.println("쪽지 전송을 취소하였습니다.");
          return;
        }
        System.out.println("쪽지를 전송하였습니다.");
        mailBoxList.add(mailBox);
        break;
      } else if (i ==  memberList.size() - 1 ) {
        System.out.println();
        System.out.printf("-%s- (이)라는 ID는 찾을 수 없습니다.", mailBox.getReceiver());
      }
=======
      mailBox.setReceiver(id);
      mailBox.setTitle(Prompt.inputString("제목> "));
      mailBox.setContent(Prompt.inputString("내용> "));

      mailBox.setSender(AuthLoginHandler.getLoginUser().getId());
      mailBox.setSendingTime(new Date(System.currentTimeMillis()));
>>>>>>> ca788885b7b606093eb32fc4621700eec49f018b

      String input = Prompt.inputString("쪽지를 전송하시겠습니까? (y/N)> ");
      if(input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("쪽지 전송을 취소하였습니다.");
        return;
      }
      System.out.println("쪽지를 전송하였습니다.");
      mailBoxList.add(mailBox);
    }

  }


}