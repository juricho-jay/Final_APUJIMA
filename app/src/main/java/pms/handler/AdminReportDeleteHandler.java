package pms.handler;

import java.sql.Date;
import java.util.List;
import pms.domain.FreeBoard;
import pms.domain.MailBox;
import pms.domain.Member;
import util.Prompt;

public class AdminReportDeleteHandler implements Command{

  List<FreeBoard> freeBoardList;
  List<FreeBoard> reportList;
  List<MailBox> mailBoxList;
  List<Member> memberList;

  public AdminReportDeleteHandler(List<FreeBoard> freeBoardList, List<FreeBoard> reportList,
      List<MailBox> mailBoxList, List<Member> memberList) {
    this.freeBoardList = freeBoardList;
    this.reportList = reportList;
    this.mailBoxList = mailBoxList;
    this.memberList = memberList;
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("[신고 게시판 삭제 허가]");

    if(reportList.size() == 0) {
      System.out.println("게시판 삭제 요청건이 없습니다.");
      return;
    }

    for(int i = 0; i< reportList.size(); i++) {
      System.out.printf("게시글 번호 : %d\n"
          + "게시글 제목 : %s\n"
          + "게시글 내용 : %s\n"
          + "게시글 작성자 : %s\n"
          + " 👍 개수 : %d\n\n"
          + "신고 사유 : %s\n"
          + "신고요청 유저 : %s\n",
          reportList.get(i).getNo(), reportList.get(i).getTitle(), reportList.get(i).getContent(),
          reportList.get(i).getWriter().getId(),reportList.get(i).getLike(),
          reportList.get(i).getReason(),reportList.get(i).getRequester());
      System.out.println();
    }

    System.out.println("삭제할 게시글의 번호를 입력하세요.");
    String input = Prompt.inputString("번호 (뒤로가기 #)> ");
    if(input.equals("#"))
      return;

    int inputNum = Integer.parseInt(input);


    //reportList에서 0번부터 반복한다.
    for(int i = 0; i < reportList.size(); i++) {
      if(inputNum == reportList.get(i).getNo()) {// 삭제할 번호(100)를 0번부터 돌린다.
        String input2 = Prompt.inputString("❗ 정말 삭제하시겠습니까? (y/N)> ");
        if(input2.equalsIgnoreCase("y")) {
          for(int j = 0; j < freeBoardList.size(); j++) {
            if(freeBoardList.get(j).getNo() == inputNum) {
              freeBoardList.remove(j);
            }
          }
          System.out.println("해당 게시글이 삭제되었습니다.");

          //메일 자동 전송하기
          MailBox mailBox1 = new MailBox();
          mailBox1.setReceiver(reportList.get(i).getRequester());
          mailBox1.setSender(AuthLoginHandler.getLoginUser().getId());//현재 로그인된 admin
          mailBox1.setTitle("신고하신 게시물이 삭제되었습니다.");
          mailBox1.setContent("요청하신 게시물은 삭제되었습니다. 다른 문의사항이 필요하신가요?");
          mailBox1.setSendingTime(new Date(System.currentTimeMillis()));
          mailBoxList.add(mailBox1);

          MailBox mailBox2 = new MailBox();
          mailBox2.setReceiver(reportList.get(i).getWriter().getId());
          mailBox2.setSender(AuthLoginHandler.getLoginUser().getId());//현재 로그인된 admin
          mailBox2.setTitle("회원님의 게시물이 신고되어 삭제되었습니다.");
          mailBox2.setContent("신고되어 게시물은 삭제되었습니다. ㅃ2 다른 문의사항이 필요하신가요?");
          mailBox2.setSendingTime(new Date(System.currentTimeMillis()));
          mailBoxList.add(mailBox2);

          reportList.remove(i);
          break;
        } else {
          System.out.println("삭제가 취소되었습니다.");

          MailBox mailBox3 = new MailBox();
          mailBox3.setReceiver(reportList.get(i).getRequester());
          mailBox3.setSender(AuthLoginHandler.getLoginUser().getId());//현재 로그인된 admin
          mailBox3.setTitle("신고 요청이 거부되었습니다.");
          mailBox3.setContent("신고 사유가 정당하지 않아 요청이 거부되었습니다.");
          mailBox3.setSendingTime(new Date(System.currentTimeMillis()));
          mailBoxList.add(mailBox3);
          reportList.remove(i);
          return;
        }
      } else if((i == reportList.size() -1)) { // 번호를 잘못 입력하면 현재 i(0번)과 요청받은 메일이 1개라면 true니깐 실행.
        System.out.println("입력하신 게시글 번호가 잘못되었습니다.");
        break;
      }
    }
  }

}
