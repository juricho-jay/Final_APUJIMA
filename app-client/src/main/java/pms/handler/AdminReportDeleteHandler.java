package pms.handler;

import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;
import pms.domain.DoctorBoard;
import pms.domain.FreeBoard;
import pms.domain.MailBox;
import request.RequestAgent;
import util.Prompt;

public class AdminReportDeleteHandler implements Command{

  RequestAgent requestAgent;

  public AdminReportDeleteHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception{
    System.out.println();
    int count = 0;
    System.out.println("[신고 게시판 삭제 허가]");

    requestAgent.request("report.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("자유 게시판 삭제 요청건이 없습니다.");
      count++;
    } else {
      Collection<FreeBoard> reportList = requestAgent.getObjects(FreeBoard.class);
      System.out.println("[자유게시판 신고 내역]");
      for (FreeBoard freeBoard : reportList) {
        System.out.printf("게시글 번호 : %d\n"
            + "게시글 제목 : %s\n"
            + "게시글 내용 : %s\n"
            + "게시글 작성자 : %s\n"
            + "신고 사유 : %s\n"
            + "신고요청 유저 : %s\n",
            freeBoard.getNo(), freeBoard.getTitle(), freeBoard.getContent(),
            freeBoard.getWriter().getId(),
            freeBoard.getReason(),freeBoard.getRequester());
      }
    }

    requestAgent.request("doctorReport.selectList", null);


    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("지식인 게시판 삭제 요청건이 없습니다.");
      count++;
    } else {
      Collection<DoctorBoard> doctorReportList = requestAgent.getObjects(DoctorBoard.class);
      System.out.println("지식인 게시판 신고 내역]");
      for (DoctorBoard doctorBoard : doctorReportList) {
        System.out.printf("게시글 번호 : %d\n"
            + "게시글 제목 : %s\n"
            + "게시글 내용 : %s\n"
            + "게시글 작성자 : %s\n"
            + "신고 사유 : %s\n"
            + "신고요청 유저 : %s\n",
            doctorBoard.getNo(), doctorBoard.getTitle(), doctorBoard.getContent(),
            doctorBoard.getWriter().getId(),
            doctorBoard.getReason(),doctorBoard.getRequester());
      }
    }

    if(count == 2)
      return;
    // 여러 번 신고된 게시글일 경우에는? 삭제 어케행~
    System.out.println("삭제할 게시판을 선택해주세요. (1. 자유게시판 / 2. 지식인 게시판)");
    String inputNum = Prompt.inputString("번호 (뒤로가기 #)> ");
    if (inputNum.equals("#")) {
      return;
    } else if (inputNum.equals("1")) {
      while(true) {
        String input = Prompt.inputString("삭제할 게시글의 번호를 입력해 주세요 (취소 #)> ");
        if(input.equals("#"))
          return;
        String input2 = Prompt.inputString("❗ 정말 삭제하시겠습니까? (y/N)> ");
        if(input2.equalsIgnoreCase("y")) {
          HashMap<String,String> params = new HashMap<>();
          params.put("no", input);
          requestAgent.request("freeBoard.delete", params);
          if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
            System.out.println("삭제 목록 게시판에 입력하신 게시글 번호가 없습니다.");
          } else {
            System.out.println("해당 게시글이 삭제되었습니다.");
            requestAgent.request("report.selectOne", params);

            if(requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
              FreeBoard freeBoard = requestAgent.getObject(FreeBoard.class);

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

              requestAgent.request("mailBox.insert", mailBox1);
              requestAgent.request("mailBox.insert", mailBox2);
              requestAgent.request("report.delete", params);
              System.out.println("자동쪽지가 발송되었습니다.");
              break;
            }
          }
        } else if (input2.equalsIgnoreCase("n") || input2.length() == 0) {  
          System.out.println("삭제가 취소되었습니다.");

          HashMap<String,String> params = new HashMap<>();
          params.put("no", input);
          requestAgent.request("report.selectOne", params);

          if(requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
            FreeBoard freeBoard = requestAgent.getObject(FreeBoard.class);

            MailBox mailBox3 = new MailBox();
            mailBox3.setReceiver(freeBoard.getRequester());
            mailBox3.setSender(AuthLoginHandler.getLoginUser().getId());//현재 로그인된 admin
            mailBox3.setTitle("신고 요청이 거부되었습니다.");
            mailBox3.setContent("신고 사유가 정당하지 않아 요청이 거부되었습니다.");
            mailBox3.setSendingTime(new Date(System.currentTimeMillis()));
            requestAgent.request("mailBox.insert", mailBox3);
            requestAgent.request("report.delete", params);
          }
        }
      }
    } else if (inputNum.equals("2")) {
      while(true) {
        String input = Prompt.inputString("삭제할 게시글의 번호를 입력해 주세요 (취소 #)> ");
        if(input.equals("#"))
          return;
        String input2 = Prompt.inputString("❗ 정말 삭제하시겠습니까? (y/N)> ");
        if(input2.equalsIgnoreCase("y")) {
          HashMap<String,String> params = new HashMap<>();
          params.put("no", input);
          requestAgent.request("doctorBoard.delete", params);
          if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
            System.out.println("삭제 목록 게시판에 입력하신 게시글 번호가 없습니다.");
          } else {
            System.out.println("해당 게시글이 삭제되었습니다.");
            requestAgent.request("doctorReport.selectOne", params);

            if(requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
              DoctorBoard doctorBoard = requestAgent.getObject(DoctorBoard.class);

              MailBox mailBox1 = new MailBox();
              mailBox1.setReceiver(doctorBoard.getRequester());
              mailBox1.setSender(AuthLoginHandler.getLoginUser().getId());//현재 로그인된 admin
              mailBox1.setTitle("신고하신 게시글이 삭제되었습니다.");
              mailBox1.setContent("요청하신 게시글이 삭제되었습니다. 다른 문의사항이 필요하신가요?");
              mailBox1.setSendingTime(new Date(System.currentTimeMillis()));


              MailBox mailBox2 = new MailBox();
              mailBox2.setReceiver(doctorBoard.getWriter().getId());
              mailBox2.setSender(AuthLoginHandler.getLoginUser().getId());//현재 로그인된 admin
              mailBox2.setTitle("회원님의 게시물이 신고되어 삭제되었습니다.");
              mailBox2.setContent("신고되어 게시물이 삭제되었습니다. 다른 문의사항이 필요하신가요?");
              mailBox2.setSendingTime(new Date(System.currentTimeMillis()));

              requestAgent.request("mailBox.insert", mailBox1);
              requestAgent.request("mailBox.insert", mailBox2);
              requestAgent.request("report.delete", params);
              System.out.println("자동쪽지가 발송되었습니다.");
              break;
            }
          }
        } else if (input2.equalsIgnoreCase("n") || input2.length() == 0) {  
          System.out.println("삭제가 취소되었습니다.");

          HashMap<String,String> params = new HashMap<>();
          params.put("no", input);
          requestAgent.request("doctorReport.selectOne", params);

          if(requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
            DoctorBoard doctorBoard = requestAgent.getObject(DoctorBoard.class);

            MailBox mailBox3 = new MailBox();
            mailBox3.setReceiver(doctorBoard.getRequester());
            mailBox3.setSender(AuthLoginHandler.getLoginUser().getId());//현재 로그인된 admin
            mailBox3.setTitle("신고 요청이 거부되었습니다.");
            mailBox3.setContent("신고 사유가 정당하지 않아 요청이 거부되었습니다.");
            mailBox3.setSendingTime(new Date(System.currentTimeMillis()));
            requestAgent.request("mailBox.insert", mailBox3);
            requestAgent.request("report.delete", params);
          }

        }
      }
    }
  }
}