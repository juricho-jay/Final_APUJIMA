package apus.handler;

import java.sql.Date;
import java.util.List;
import apus.dao.BoardDao;
import apus.dao.MailBoxDao;
import apus.dao.ReportDao;
import apus.domain.Board;
import apus.domain.MailBox;
import apus.domain.Report;
import util.Prompt;

public class AdminReportDeleteHandler implements Command{

  ReportDao reportDao;
  BoardDao boardDao;
  MailBoxDao mailBoxDao;

  public AdminReportDeleteHandler(ReportDao reportDao
      , BoardDao boardDao
      , MailBoxDao mailBoxDao) {
    this.reportDao = reportDao;
    this.boardDao = boardDao;
    this.mailBoxDao = mailBoxDao;
  }

  // 수정 사항: 1. board 통합 > doctor / free > 각자 조회

  @Override
  public void execute(CommandRequest request) throws Exception{
    System.out.println();
    //    int count = 0;
    System.out.println("[신고 게시판 삭제 허가]");
    System.out.println();

    List<Report> reportList = reportDao.findAll();

    if (reportList == null) {
      System.out.println("-[자유게시판 신고 접수 내역]");
      System.out.println("신고 접수건이 없습니다.");
      System.out.println();
      System.out.println("-[지식in게시판 신고 접수 내역]");
      System.out.println("신고 접수건이 없습니다.");
      System.out.println();
    } 


    if (reportList != null) {
      System.out.println("-[자유게시판 신고 접수 내역]");
      for (Board freeBoard : reportList) {
        System.out.printf("게시글 번호 : %d\n"
            + "게시글 제목 : %s\n"
            + "게시글 내용 : %s\n"
            + "게시글 작성자 : %s\n"
            + "신고 사유 : %s\n"
            + "신고요청 유저 : %s\n",
            freeBoard.getNo(), freeBoard.getTitle(), freeBoard.getContent(),
            freeBoard.getWriter().getId(),
            freeBoard.getReason(),freeBoard.getRequester());
        System.out.println();
      }
    } 

    /*
     * if (doctorReportList != null) { System.out.println("-[지식in게시판 신고 접수 내역]"); for (DoctorBoard
     * doctorBoard : doctorReportList) { System.out.printf("게시글 번호 : %d\n" + "게시글 제목 : %s\n" +
     * "게시글 내용 : %s\n" + "게시글 작성자 : %s\n" + "신고 사유 : %s\n" + "신고요청 유저 : %s\n", doctorBoard.getNo(),
     * doctorBoard.getTitle(), doctorBoard.getContent(), doctorBoard.getWriter().getId(),
     * doctorBoard.getReason(),doctorBoard.getRequester()); } }
     */

    System.out.println("게시물을 삭제할 게시판을 선택해주세요. (1. 자유게시판 / 2. 지식인 게시판)");
    String inputNum = Prompt.inputString("번호 (뒤로가기 #)> ");
    if (inputNum.equals("#")) {
      return;
    } else if (inputNum.equals("1")) {
      while(true) {
        int no = Prompt.inputInt("삭제할 게시글의 번호>");
        Board freeBoard = reportDao.findByNo(no);
        String input2 = Prompt.inputString("❗ 정말 삭제하시겠습니까? (y/N)> ");
        if(input2.equalsIgnoreCase("y")) {
          boardDao.delete(no);
          request.setAttribute("no", no);
          request.getRequestDispatcher("/comment/autoDelete").forward(request);
          request.getRequestDispatcher("/like/autoDelete").forward(request);

          MailBox mailBox1 = new MailBox();
          mailBox1.setReceiver(freeBoard.getRequester());
          mailBox1.setSender(AuthLoginHandler.getLoginUser().getId());//현재 로그인된 admin
          mailBox1.setTitle("신고하신 게시글이 삭제되었습니다.");
          mailBox1.setContent("요청하신 게시글이 삭제되었습니다. 다른 문의사항이 필요하신가요?");
          mailBox1.setSendingTime(new Date(System.currentTimeMillis()));

          MailBox mailBox2 = new MailBox();
          mailBox2.setReceiver(freeBoard.getWriter().getId());
          mailBox2.setSender(AuthLoginHandler.getLoginUser().getId());//현재 로그인된 admin
          mailBox2.setTitle("회원님의 게시글이 신고되어 삭제되었습니다.");
          mailBox2.setContent("신고되어 게시글이 삭제되었습니다. 다른 문의사항이 필요하신가요?");
          mailBox2.setSendingTime(new Date(System.currentTimeMillis()));

          mailBoxDao.insert(mailBox1);
          mailBoxDao.insert(mailBox2);
          // 같은 번호들을 찾아서 다 지운다.
          reportDao.autoDelete(no);

          System.out.println("자동쪽지가 발송되었습니다.");
          return;
          // n를 할 때 취소부분
        } else if (input2.equalsIgnoreCase("n") || input2.length() == 0) {  
          System.out.println("삭제가 취소되었습니다.");

          MailBox mailBox3 = new MailBox();
          mailBox3.setReceiver(freeBoard.getRequester());
          mailBox3.setSender(AuthLoginHandler.getLoginUser().getId());//현재 로그인된 admin
          mailBox3.setTitle("신고 요청이 거부되었습니다.");
          mailBox3.setContent("신고 사유가 정당하지 않아 요청이 거부되었습니다.");
          mailBox3.setSendingTime(new Date(System.currentTimeMillis()));

          mailBoxDao.insert(mailBox3);
          reportDao.autoDelete(no);
          return;
        }
      }
      // 지식인 게시판 선택부분
    } /*
     * else if (inputNum.equals("2")) { while(true) { int no =
     * Prompt.inputInt("삭제할 게시글의 번호를 입력해 주세요."); DoctorBoard doctorBoard =
     * doctorReportDao.findByNo(no); String input2 = Prompt.inputString("❗ 정말 삭제하시겠습니까? (y/N)> ");
     * if(input2.equalsIgnoreCase("y")) { doctorBoardDao.delete(no); request.setAttribute("no",
     * no); request.setAttribute("boardType", "doctorBoard");
     * request.getRequestDispatcher("/comment/autoDelete").forward(request);
     * request.getRequestDispatcher("/like/autoDelete").forward(request);
     * 
     * MailBox mailBox1 = new MailBox(); mailBox1.setReceiver(doctorBoard.getRequester());
     * mailBox1.setSender(AuthLoginHandler.getLoginUser().getId());//현재 로그인된 admin
     * mailBox1.setTitle("신고하신 게시글이 삭제되었습니다.");
     * mailBox1.setContent("요청하신 게시글이 삭제되었습니다. 다른 문의사항이 필요하신가요?"); mailBox1.setSendingTime(new
     * Date(System.currentTimeMillis()));
     * 
     * 
     * MailBox mailBox2 = new MailBox(); mailBox2.setReceiver(doctorBoard.getWriter().getId());
     * mailBox2.setSender(AuthLoginHandler.getLoginUser().getId());//현재 로그인된 admin
     * mailBox2.setTitle("회원님의 게시물이 신고되어 삭제되었습니다.");
     * mailBox2.setContent("신고되어 게시물이 삭제되었습니다. 다른 문의사항이 필요하신가요?"); mailBox2.setSendingTime(new
     * Date(System.currentTimeMillis()));
     * 
     * mailBoxDao.insert(mailBox1); mailBoxDao.insert(mailBox2); doctorReportDao.autoDelete(no);
     * 
     * System.out.println("자동쪽지가 발송되었습니다."); return; } else if (input2.equalsIgnoreCase("n") ||
     * input2.length() == 0) { System.out.println("삭제가 취소되었습니다.");
     * 
     * MailBox mailBox3 = new MailBox(); mailBox3.setReceiver(doctorBoard.getRequester());
     * mailBox3.setSender(AuthLoginHandler.getLoginUser().getId());//현재 로그인된 admin
     * mailBox3.setTitle("신고 요청이 거부되었습니다."); mailBox3.setContent("신고 사유가 정당하지 않아 요청이 거부되었습니다.");
     * mailBox3.setSendingTime(new Date(System.currentTimeMillis()));
     * 
     * mailBoxDao.insert(mailBox3); doctorReportDao.autoDelete(no); return; } } }
     */
  }
}