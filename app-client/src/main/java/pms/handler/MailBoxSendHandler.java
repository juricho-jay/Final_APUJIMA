package pms.handler;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import pms.dao.MailBoxDao;
import pms.dao.MemberDao;
import pms.domain.MailBox;
import pms.domain.Member;
import util.Prompt;

public class MailBoxSendHandler implements Command{

  MailBoxDao mailBoxDao;
  MemberDao memberDao;

  public MailBoxSendHandler(MailBoxDao mailBoxDao , MemberDao memberDao) {
    this.mailBoxDao = mailBoxDao;
    this.memberDao = memberDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[쪽지 전송] 페이지입니다.");
    System.out.println();
    //  MailBox mailBox = new MailBox();
    //    mailBox.setMailNo(Prompt.inputInt("쪽지 번호> "));
    String id = Prompt.inputString("수신인> ");

    HashMap<String,String> params = new HashMap<>();
    params.put("id", id);
    MailBox mailBox = new MailBox();
    List<MailBox> mailBoxList = mailBoxDao.findAll();



    if (mailBoxList == null) {
      mailBox.setNo(1);
      mailBox.setMailNo(1);
      mailBox.setReceiver(id);
      mailBox.setTitle(Prompt.inputString("제목> "));
      mailBox.setContent(Prompt.inputString("내용> "));

      mailBox.setSender(AuthLoginHandler.getLoginUser().getId());
      mailBox.setSendingTime(new Date(System.currentTimeMillis()));

      String input = Prompt.inputString("쪽지를 전송하시겠습니까? (y/N)> ");
      if(input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("쪽지 전송을 취소하였습니다.");
        return;
      } else if(input.equalsIgnoreCase("y")) {
        mailBoxDao.insert(mailBox);
        System.out.println("쪽지를 전송하였습니다.");
      } else {
        System.out.println("잘못된 입력입니다.");
      }

      return;
    }

    Member member =  memberDao.findById(id);

    int count = 0 ; //기존에 존재하는 쪽지 갯수

    if (member == null) {
      System.out.printf("-%s- (이)라는 ID는 찾을 수 없습니다.",id);
      System.out.println();
    } 
    //    else if (member.getId().equalsIgnoreCase(AuthLoginHandler.getLoginUser().getId())){
    //      System.out.println("쪽지를 보내는 아이디가 같습니다. 다시 입력해주세요.");
    //      System.out.println();
    //    }
    else if(member.getId().equalsIgnoreCase("admin")) {
      System.out.println("관리자에게는 쪽지를 보낼수 없습니다!");
    } else {

      for (int i = 0; i < mailBoxList.size(); i++) {
        if (mailBoxList.get(i).getReceiver().equals(member.getId())) {
          count++;
          //conditionLast = mailBoxList.get(i).getNo();
        }
      }

      //      if(conditionLast != count) {
      //        mailBox.setNo(++conditionLast);
      //      } 
      //      else {
      mailBox.setNo(++count);
      //      }

      mailBox.setMailNo(mailBoxList.size() +1);
      mailBox.setReceiver(id);
      mailBox.setTitle(Prompt.inputString("제목> "));
      mailBox.setContent(Prompt.inputString("내용> "));

      mailBox.setSender(AuthLoginHandler.getLoginUser().getId());
      mailBox.setSendingTime(new Date(System.currentTimeMillis()));


      String input = Prompt.inputString("쪽지를 전송하시겠습니까? (y/N)> ");
      if(input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("쪽지 전송을 취소하였습니다.");
        return;
      } else if(input.equalsIgnoreCase("y")) {
        mailBoxDao.insert(mailBox);
        System.out.println("쪽지를 전송하였습니다.");
      } else {
        System.out.println("잘못된 입력입니다.");
      }
    }

  }


}