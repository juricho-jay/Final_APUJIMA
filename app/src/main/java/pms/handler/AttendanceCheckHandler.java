package pms.handler;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import pms.domain.Member;
import util.Prompt;

public class AttendanceCheckHandler implements Command{

  List<Date> dateList;
  List<Member> memberCheckList;

  public AttendanceCheckHandler(List<Date> dateList, List<Member> memberCheckList) {
    this.dateList = dateList;
    this.memberCheckList = memberCheckList; 
  }


  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println();
    System.out.println("[출석체크] 페이지입니다.");
    System.out.println();

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");



    String input = Prompt.inputString("출석체크(C) / 뒤로가기(0)");
    if(input.equals("0")) {
      return;
    } else if(input.equalsIgnoreCase("c")) {
      // 만약에 오늘 처음 출석이면
      // dateList에 오늘 날짜가 없으면 or dateList에 날짜가 있는데 내 아이디가 없을 때
      if(dateList.size() == 0) {
        AuthLoginHandler.loginUser.setCount(AuthLoginHandler.loginUser.getCount() + 30);
        System.out.println("금일 첫 출석체크로 30포인트가 적립되었습니다.");
        dateList.add(new Date(System.currentTimeMillis()));
        memberCheckList.add(AuthLoginHandler.loginUser);
        return;
      }

      for(int i = 0; i < dateList.size(); i++) {
        String time1 = format.format(dateList.get(i));
        String time2 = format.format(new Date(System.currentTimeMillis()));
        if(time1.equals(time2)) {
          for(int j = 0; j < memberCheckList.size(); j++) {
            if(memberCheckList.get(j).getId().equals(AuthLoginHandler.loginUser.getId())) {
              System.out.println("이미 출석체크 포인트를 받으셨습니다.");
              return;
            }
          }
          System.out.println("금일 첫 출석체크로 30포인트가 적립되었습니다.");
          memberCheckList.add(AuthLoginHandler.loginUser);
          return;
        }
      }

      System.out.println("금일 첫 출석체크로 30포인트가 적립되었습니다.");
      dateList.add(new Date(System.currentTimeMillis()));
      memberCheckList.add(AuthLoginHandler.loginUser);
    }

  }
}
