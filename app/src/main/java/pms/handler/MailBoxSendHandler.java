package pms.handler;

import java.sql.Date;
import java.util.List;
import pms.domain.MailBox;
import pms.domain.Member;
import util.Prompt;

public class MailBoxSendHandler extends AbstractMailBoxHandler {
  MemberPrompt memberPrompt;

  public MailBoxSendHandler(List<MailBox> mailBoxList, MemberPrompt memberPrompt) {
    super(mailBoxList);
    this.memberPrompt = memberPrompt;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println();
    System.out.println("[쪽지 전송] 페이지입니다.");
    System.out.println();
    MailBox mailBox = new MailBox();
    //    mailBox.setMailNo(Prompt.inputInt("쪽지 번호> "));
    String id = Prompt.inputString("수신인> ");

    Member member = memberPrompt.findById(id);

    if (member == null) {
      System.out.printf("-%s- (이)라는 ID는 찾을 수 없습니다.",id);
      System.out.println();
    } else if (member.getId().equalsIgnoreCase(AuthLoginHandler.getLoginUser().getId())){
      System.out.println("쪽지를 보내는 아이디가 같습니다. 다시 입력해주세요.");
      System.out.println();
    } else if(member.getId().equalsIgnoreCase("admin")) {
      System.out.println("관리자에게는 쪽지를 보낼수 없습니다!");
    } else {
      mailBox.setReceiver(id);
      mailBox.setTitle(Prompt.inputString("제목> "));
      mailBox.setContent(Prompt.inputString("내용> "));

      mailBox.setSender(AuthLoginHandler.getLoginUser().getId());
      mailBox.setSendingTime(new Date(System.currentTimeMillis()));


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