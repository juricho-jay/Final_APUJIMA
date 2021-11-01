package apus.handler;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import apus.dao.BoardDao;
import apus.dao.MailBoxDao;
import apus.dao.ReportDao;
import apus.domain.Board;
import apus.domain.Report;
import util.Prompt;

public class AdminReportDeleteHandler implements Command{

  ReportDao reportDao;
  BoardDao boardDao;
  MailBoxDao mailBoxDao;
  SqlSession sqlSession;

  public AdminReportDeleteHandler(ReportDao reportDao
      , BoardDao boardDao
      , MailBoxDao mailBoxDao
      , SqlSession sqlSession) {
    this.reportDao = reportDao;
    this.boardDao = boardDao;
    this.mailBoxDao = mailBoxDao;
    this.sqlSession = sqlSession;
  }

  // 수정 사항: 1. board 통합 > doctor / free > 각자 조회

  @Override
  public void execute(CommandRequest request) throws Exception{
    System.out.println();
    //    int count = 0;
    System.out.println("[신고 게시판 삭제 허가]");
    System.out.println();

    List<Report> reportList = reportDao.findAll();
    List<Board> boardList = boardDao.findAll();

    if (reportList == null) {
      System.out.println("-[자유게시판 신고 접수 내역]");
      System.out.println("신고 접수건이 없습니다.");
      System.out.println();
      System.out.println("-[지식in게시판 신고 접수 내역]");
      System.out.println("신고 접수건이 없습니다.");
      System.out.println();
    } 

    String boardType = "";

    for (Report report : reportList) {
      if(report.getRequestBoard().getWhichBoard() == 1) 
        boardType = "자유 게시판";
      else if(report.getRequestBoard().getWhichBoard() == 2) 
        boardType = "지식인 게시판";

      Board b = boardDao.findByNo(report.getRequestBoard().getNo());

      System.out.printf("게시판 번호 : %d\n"
          + "게시판 종류 : %s\n"
          + "게시글 제목 : %s\n"
          + "게시글 작성자 : %s\n"
          + "신고 사유 : %s\n"
          + "신고 요청자 : %s\n",
          report.getRequestBoard().getNo(),
          boardType,
          report.getRequestBoard().getTitle(),
          b.getWriter().getId(),
          report.getReason(),
          report.getRequester().getId());
      System.out.println();
    }

    while(true) {
      String inputNum = Prompt.inputString("삭제할 게시글의 번호 (뒤로가기 #)> ");
      if (inputNum.equals("#")) {
        return;
      } 

      Board b = boardDao.findByNo(Integer.parseInt(inputNum));
      if(b == null) {
        System.out.println("해당 번호의 게시글이 없습니다. 다시 입력해 주세요.");
        continue;
      }


      int no = Integer.parseInt(inputNum);

      String input2 = Prompt.inputString("❗ 정말 삭제하시겠습니까? (y/N)> ");
      if(input2.equalsIgnoreCase("y")) {


        request.setAttribute("no", no);
        request.getRequestDispatcher("/comment/autoDelete").forward(request);
        request.getRequestDispatcher("/like/autoDelete").forward(request);

        reportDao.delete(no);
        boardDao.delete(no);
        sqlSession.commit();
        return;

      }
    }

  }
}
//      MailBox mailBox1 = new MailBox();
//      mailBox1.setReceiver(board.getRequester());
//      mailBox1.setSender(AuthLoginHandler.getLoginUser().getId());//현재 로그인된 admin
//      mailBox1.setTitle("신고하신 게시글이 삭제되었습니다.");
//      mailBox1.setContent("요청하신 게시글이 삭제되었습니다. 다른 문의사항이 필요하신가요?");
//      mailBox1.setSendingTime(new Date(System.currentTimeMillis()));
//
//      MailBox mailBox2 = new MailBox();
//      mailBox2.setReceiver(board.getWriter().getId());
//      mailBox2.setSender(AuthLoginHandler.getLoginUser().getId());//현재 로그인된 admin
//      mailBox2.setTitle("회원님의 게시글이 신고되어 삭제되었습니다.");
//      mailBox2.setContent("신고되어 게시글이 삭제되었습니다. 다른 문의사항이 필요하신가요?");
//      mailBox2.setSendingTime(new Date(System.currentTimeMillis()));
//
//      mailBoxDao.insert(mailBox1);
//      mailBoxDao.insert(mailBox2);
//      // 같은 번호들을 찾아서 다 지운다.
//      reportDao.autoDelete(no);
//
//      System.out.println("자동쪽지가 발송되었습니다.");
//      return;
//      // n를 할 때 취소부분
//    } else if (input2.equalsIgnoreCase("n") || input2.length() == 0) {  
//      System.out.println("삭제가 취소되었습니다.");
//
//      MailBox mailBox3 = new MailBox();
//      mailBox3.setReceiver(board.getRequester());
//      mailBox3.setSender(AuthLoginHandler.getLoginUser().getId());//현재 로그인된 admin
//      mailBox3.setTitle("신고 요청이 거부되었습니다.");
//      mailBox3.setContent("신고 사유가 정당하지 않아 요청이 거부되었습니다.");
//      mailBox3.setSendingTime(new Date(System.currentTimeMillis()));
//
//      mailBoxDao.insert(mailBox3);
//      reportDao.autoDelete(no);
//      return;
//    }
