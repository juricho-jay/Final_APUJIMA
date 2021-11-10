package apus.servlet;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import apus.dao.DateCheckDao;
import apus.dao.MemberDao;
import apus.domain.DateCheck;
import apus.domain.Member;
import util.Prompt;

public class AttendanceCheckHandler implements Command {

  DateCheckDao dateCheckDao;
  MemberDao memberDao;
  SqlSession sqlSession;

  public AttendanceCheckHandler(DateCheckDao dateCheckDao, MemberDao memberDao,SqlSession sqlSession) {
    this.dateCheckDao = dateCheckDao;
    this.memberDao = memberDao;
    this.sqlSession = sqlSession;
  }


  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println();
    System.out.println("[출석체크] 페이지입니다.");
    System.out.println();
    Member member = new Member();
    DateCheck dateCheck = new DateCheck();
    //출석 체크 하는 사람.(LoginUser)
    Member loginUser = memberDao.findById(AuthLoginHandler.getLoginUser().getId());
    String loginUser1 = AuthLoginHandler.getLoginUser().getId();

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Date today = new Date();

    String input = Prompt.inputString("출석체크(C) / 뒤로가기(0) > ");
    if(input.equals("0")) {
      return;
    } else if(input.equalsIgnoreCase("c")) {

      List<DateCheck> dateCheckList = dateCheckDao.findAll();



      // 만약에 오늘 처음 출석이면
      // dateList에 오늘 날짜가 없으면 or dateList에 날짜가 있는데 내 아이디가 없을 때


      if (dateCheckList.size() == 0) {
        // (member)로그인 유저 포인트 적립 > 데이터에 저장


        loginUser.setPoint(loginUser.getPoint() + 30);
        memberDao.update2(loginUser);
        sqlSession.commit();

        System.out.println("금일 첫 출석체크로 30포인트가 적립되었습니다.");



        dateCheck = new DateCheck();
        dateCheck.setDate(today);
        dateCheck.setAttendee(loginUser);

        dateCheckDao.insert(dateCheck);
        sqlSession.commit();


        //        // 3일 지난 데이터 정리
        //        dateCheckDao.delete();
        //        sqlSession.commit();
        return;

      } else { // dateCheckList != 0
        String time2 = format.format(today);
        for(int i = 0; i < dateCheckList.size(); i++) {
          String time1 = format.format(dateCheckList.get(i).getDate());
          if(time1.equals(time2) && dateCheckList.get(i).getAttendee().getId().equals(loginUser1)) {
            System.out.println("이미 출석체크 포인트를 받으셨습니다.");
            //dateCheckDao.delete();
            return;

            //끝까지 돌렸는데 없을 때
          } else if (i == dateCheckList.size() -1)  { 


            loginUser.setPoint(loginUser.getPoint() + 30);
            memberDao.update2(loginUser);
            sqlSession.commit();

            System.out.println("금일 첫 출석체크로 30포인트가 적립되었습니다.");

            dateCheck = new DateCheck();
            dateCheck.setDate(today);
            dateCheck.setAttendee(loginUser);

            dateCheckDao.insert(dateCheck);
            sqlSession.commit();

            //            // 3일 지난 데이터 정리
            //            dateCheckDao.delete();
            //            sqlSession.commit();

            return;

          }
        }
      }
    }
  }
}


