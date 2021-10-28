package apus.handler;

import java.sql.Date;
import org.apache.ibatis.session.SqlSession;
import apus.dao.MailBoxDao;
import apus.dao.MemberDao;
import apus.domain.MailBox;
import apus.domain.Member;
import util.Prompt;

public class MailBoxSendHandler implements Command{

  MailBoxDao mailBoxDao;
  MemberDao memberDao;
  SqlSession sqlSession;

  public MailBoxSendHandler(MailBoxDao mailBoxDao , MemberDao memberDao, SqlSession sqlSession) {
    this.mailBoxDao = mailBoxDao;
    this.memberDao = memberDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[쪽지 전송] 페이지입니다.");
    System.out.println();

    //보내는 사람 = 로그인 유저 (객체 미리 담아두기)
    Member sender = memberDao.findById(AuthLoginHandler.getLoginUser().getId());

    String id = null;
    Member receiver = null;
    while (true) {
      id = Prompt.inputString("수신인> ");
      receiver = memberDao.findById(id);

      if (receiver == null) {
        System.out.printf("-%s- (이)라는 ID는 찾을 수 없습니다.",id);
        continue;
      } else if (id.equalsIgnoreCase("admin") || id.equals("관리자")) {
        System.out.println("관리자에게는 쪽지를 보낼수 없습니다!");
        continue;
      }
      break;
    }

    MailBox mailBox = new MailBox();

    mailBox.setReceiver(receiver);
    mailBox.setTitle(Prompt.inputString("제목> "));
    mailBox.setContent(Prompt.inputString("내용> "));

    mailBox.setSender(sender);
    mailBox.setSentTime(new Date(System.currentTimeMillis()));

    String input = Prompt.inputString("쪽지를 전송하시겠습니까? (y/N)> ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("쪽지 전송을 취소하였습니다.");
      return;
    } else if (input.equalsIgnoreCase("y")) {
      mailBoxDao.insert(mailBox);
      sqlSession.commit();
      System.out.println("쪽지를 전송하였습니다.");
    } else {
      System.out.println("잘못된 입력입니다.");
    }
  }

}

//    if (mailBoxList == null) {
//      mailBox.setReceiver(receiver);
//      mailBox.setTitle(Prompt.inputString("제목> "));
//      mailBox.setContent(Prompt.inputString("내용> "));
//
//      mailBox.setSender(sender);
//      mailBox.setSendingTime(new Date(System.currentTimeMillis()));
//
//      String input = Prompt.inputString("쪽지를 전송하시겠습니까? (y/N)> ");
//      if(input.equalsIgnoreCase("n") || input.length() == 0) {
//        System.out.println("쪽지 전송을 취소하였습니다.");
//        return;
//      } else if(input.equalsIgnoreCase("y")) {
//        mailBoxDao.insert(mailBox);
//        System.out.println("쪽지를 전송하였습니다.");
//      } else {
//        System.out.println("잘못된 입력입니다.");
//      }
//
//      return;
//    }