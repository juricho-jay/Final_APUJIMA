package pms.handler;

import java.sql.Date;
import java.util.List;
import pms.domain.DoctorBoard;
import pms.domain.MailBox;
import util.Prompt;

public class AdminDoctorReportDeleteHandler implements Command{

  List<DoctorBoard> doctorBoardList;
  List<DoctorBoard> doctorReportList;
  List<MailBox> mailBoxList;

  public AdminDoctorReportDeleteHandler(List<DoctorBoard> doctorBoardList, List<DoctorBoard> doctorReportList,
      List<MailBox> mailBoxList) {
    this.doctorBoardList = doctorBoardList;
    this.doctorReportList = doctorReportList;
    this.mailBoxList = mailBoxList;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println();
    System.out.println("[Healer 게시판 신고 게시글 삭제 허가]");

    if(doctorReportList.size() == 0) {
      System.out.println("게시판 삭제 요청건이 없습니다.");
      return;
    }

    for(int i = 0; i< doctorReportList.size(); i++) {
      System.out.printf("게시글 번호 : %d\n"
          + "게시글 제목 : %s\n"
          + "게시글 내용 : %s\n"
          + "게시글 작성자 : %s\n"
          + " 👍 개수 : %d\n\n"
          + "신고 사유 : %s\n"
          + "신고요청 유저 : %s\n",
          doctorReportList.get(i).getNo(), doctorReportList.get(i).getTitle(),
          doctorReportList.get(i).getContent(),
          doctorReportList.get(i).getWriter().getId(),doctorReportList.get(i).getLike(),
          doctorReportList.get(i).getReason(),doctorReportList.get(i).getRequester());
      System.out.println();
    }

    System.out.println("삭제할 게시글의 번호를 입력하세요.");
    String input = Prompt.inputString("번호 (뒤로가기 #)> ");
    if(input.equals("#"))
      return;

    int inputNum = Integer.parseInt(input);


    //reportList에서 0번부터 반복한다.
    for(int i = 0; i < doctorReportList.size(); i++) {
      if(inputNum == doctorReportList.get(i).getNo()) {// 삭제할 번호(100)를 0번부터 돌린다.
        String input2 = Prompt.inputString("❗ 정말 삭제하시겠습니까? (y/N)> ");
        if(input2.equalsIgnoreCase("y")) {
          for(int j = 0; j < doctorBoardList.size(); j++) {
            if(doctorBoardList.get(j).getNo() == inputNum) {
              doctorBoardList.remove(j);
            }
          }
          System.out.println("해당 게시글이 삭제되었습니다.");
          System.out.println("자동쪽지가 발송되었습니다.");


          //메일 자동 전송하기
          MailBox mailBox1 = new MailBox();
          mailBox1.setReceiver(doctorReportList.get(i).getRequester());
          mailBox1.setSender(AuthLoginHandler.getLoginUser().getId());//현재 로그인된 admin
          mailBox1.setTitle("신고하신 게시물이 삭제되었습니다.");
          mailBox1.setContent("요청하신 게시물은 삭제되었습니다. 다른 문의사항이 필요하신가요?");
          mailBox1.setSendingTime(new Date(System.currentTimeMillis()));
          mailBoxList.add(mailBox1);

          MailBox mailBox2 = new MailBox();
          mailBox2.setReceiver(doctorReportList.get(i).getWriter().getId());
          mailBox2.setSender(AuthLoginHandler.getLoginUser().getId());//현재 로그인된 admin
          mailBox2.setTitle("회원님의 게시물이 신고되어 삭제되었습니다.");
          mailBox2.setContent("신고되어 게시물은 삭제되었습니다. 다른 문의사항이 필요하신가요?");
          mailBox2.setSendingTime(new Date(System.currentTimeMillis()));
          mailBoxList.add(mailBox2);


          doctorReportList.remove(i);
          break;
        } else {
          System.out.println("삭제가 취소되었습니다.");

          MailBox mailBox3 = new MailBox();
          mailBox3.setReceiver(doctorReportList.get(i).getRequester());
          mailBox3.setSender(AuthLoginHandler.getLoginUser().getId());//현재 로그인된 admin
          mailBox3.setTitle("신고 요청이 거부되었습니다.");
          mailBox3.setContent("신고 사유가 정당하지 않아 요청이 거부되었습니다.");
          mailBox3.setSendingTime(new Date(System.currentTimeMillis()));
          mailBoxList.add(mailBox3);
          doctorReportList.remove(i);
          return;
        }
      } else if((i == doctorReportList.size() -1)) { // 번호를 잘못 입력하면 현재 i(0번)과 요청받은 메일이 1개라면 true니깐 실행.
        System.out.println("입력하신 게시글 번호가 잘못되었습니다.");
        break;
      }
    }
  }

}
