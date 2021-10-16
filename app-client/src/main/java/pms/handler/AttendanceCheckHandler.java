package pms.handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import pms.dao.DateCheckDao;
import pms.dao.MemberDao;
import pms.domain.DateCheck;
import pms.domain.Member;
import util.Prompt;

public class AttendanceCheckHandler implements Command {

  DateCheckDao dateCheckDao;
  MemberDao memberDao;

  public AttendanceCheckHandler(DateCheckDao dateCheckDao, MemberDao memberDao) {
    this.dateCheckDao = dateCheckDao;
    this.memberDao = memberDao;
  }


  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println();
    System.out.println("[출석체크] 페이지입니다.");
    System.out.println();
    String loginUser = AuthLoginHandler.getLoginUser().getId();
    HashMap<String,String> params = new HashMap<>();
    params.put("id", loginUser);

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Date today = new Date();

    String input = Prompt.inputString("출석체크(C) / 뒤로가기(0)");
    if(input.equals("0")) {
      return;
    } else if(input.equalsIgnoreCase("c")) {

      List<DateCheck> dateCheckList = dateCheckDao.findAll();

      // 만약에 오늘 처음 출석이면
      // dateList에 오늘 날짜가 없으면 or dateList에 날짜가 있는데 내 아이디가 없을 때

      // dateCheckList == 0
      if(dateCheckList == null) {
        // (member)로그인 유저 포인트 적립 > 데이터에 저장

        Member member = memberDao.findById(loginUser);
        System.out.println(loginUser);
        System.out.println(member.getId());
        member.setPoint(member.getPoint() + 30);
        System.out.println(member.getPoint());
        memberDao.update(member);

        System.out.println("금일 첫 출석체크로 30포인트가 적립되었습니다.");


        // 날짜/아이디 저장
        DateCheck dateCheck = new DateCheck();
        dateCheck.setDate(today);
        dateCheck.setUser(loginUser);

        dateCheckDao.insert(dateCheck);

        // 3일 지난 데이터 정리
        dateCheckDao.delete();
        return;

      } else { // dateCheckList != 0
        String time2 = format.format(today);
        for(int i = 0; i < dateCheckList.size(); i++) {
          String time1 = format.format(dateCheckList.get(i).getDate());
          // today O && loginUser O
          if(time1.equals(time2) && dateCheckList.get(i).getUser().equals(loginUser)) {
            System.out.println("이미 출석체크 포인트를 받으셨습니다.");
            return;

            // today O && loginUser X
          } else if (i == dateCheckList.size() -1)  { 
            if (time1.equals(time2) && !dateCheckList.get(i).getUser().equals(loginUser)) {


              Member member = memberDao.findById(loginUser);
              member.setPoint(member.getPoint() + 30);
              System.out.println(member.getPoint());
              memberDao.update(member);

              System.out.println("금일 첫 출석체크로 30포인트가 적립되었습니다.");
              System.out.println("t o / l x");
              System.out.println(time1);
              System.out.println(time2);

              DateCheck dateCheck = new DateCheck();
              dateCheck.setDate(today);
              dateCheck.setUser(loginUser);

              dateCheckDao.insert(dateCheck);

              // 3일 지난 데이터 정리
              dateCheckDao.delete();

              return;

              // today X && loginUser O
            } else if (!time1.equals(time2) && dateCheckList.get(i).getUser().equals(loginUser)) {

              Member member = memberDao.findById(loginUser);
              member.setPoint(member.getPoint() + 30);
              System.out.println(member.getPoint());
              memberDao.update(member);

              System.out.println("금일 첫 출석체크로 30포인트가 적립되었습니다.");
              System.out.println("t x / l o");
              System.out.println(time1);
              System.out.println(time2);


              DateCheck dateCheck = new DateCheck();
              dateCheck.setDate(today);
              dateCheck.setUser(loginUser);

              dateCheckDao.insert(dateCheck);

              // 3일 지난 데이터 정리
              dateCheckDao.delete();

              return;

              // today X && loginUser X
            } else if (!time1.equals(time2) && !dateCheckList.get(i).getUser().equals(loginUser)) {
              Member member = memberDao.findById(loginUser);
              member.setPoint(member.getPoint() + 30);
              System.out.println(member.getPoint());
              memberDao.update(member);

              System.out.println("금일 첫 출석체크로 30포인트가 적립되었습니다.");
              System.out.println("t x / l x");
              System.out.println(time1);
              System.out.println(time2);


              DateCheck dateCheck = new DateCheck();
              dateCheck.setDate(today);
              dateCheck.setUser(loginUser);

              dateCheckDao.insert(dateCheck);

              // 3일 지난 데이터 정리
              dateCheckDao.delete();

              return;
            }
          }
        }
      }
    }
  }
}


