package pms.handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import pms.domain.DateCheck;
import pms.domain.Member;
import request.RequestAgent;
import util.Prompt;

public class AttendanceCheckHandler implements Command {

  RequestAgent requestAgent;

  public AttendanceCheckHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
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
      requestAgent.request("dateCheck.selectList", null);
      // 만약에 오늘 처음 출석이면
      // dateList에 오늘 날짜가 없으면 or dateList에 날짜가 있는데 내 아이디가 없을 때

      // dateCheckList == 0
      if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        // (member)로그인 유저 포인트 적립 > 데이터에 저장
        requestAgent.request("member.selectOneById", params);
        if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
          System.out.println("해당 회원이 없습니다.");
          return;
        }
        Member member = requestAgent.getObject(Member.class);
        member.setCount(member.getCount() + 30);
        requestAgent.request("member.update", member);

        if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
          System.out.println("포인트 적립 실패!");
          return;
        }

        System.out.println("금일 첫 출석체크로 30포인트가 적립되었습니다.");

        // 날짜/아이디 저장
        DateCheck dateCheck = new DateCheck();
        dateCheck.setDate(today);
        dateCheck.setUser(loginUser);
        requestAgent.request("dateCheck.insert", dateCheck);

        if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
          System.out.println("오늘 날짜 저장 실패!");
          return;
        }
        // 3일 지난 데이터 정리
        request.getRequestDispatcher("/auth/deleteCheck").forward(request);

        return;

      } else { // dateCheckList != 0
        requestAgent.request("dateCheck.selectList", null);
        List<DateCheck> dateCheckList = (List<DateCheck>) requestAgent.getObjects(DateCheck.class);
        // 오늘 날짜가 있는지 && 내 아이디 있는지
        for(int i = 0; i < dateCheckList.size(); i++) {
          String time1 = format.format(dateCheckList.get(i).getDate());
          String time2 = format.format(today);
          if(time1.equals(time2)) {
            for(int j = 0; j < dateCheckList.size(); j++) {
              if(dateCheckList.get(j).getUser().equals(loginUser)) {
                System.out.println("이미 출석체크 포인트를 받으셨습니다.");
                return;
              }
            }
            // 오늘 날짜는 있는데 내 아이디가 없을 때
            requestAgent.request("member.selectOneById", params);
            if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
              System.out.println("해당 회원이 없습니다.");
              return;
            }
            Member member = requestAgent.getObject(Member.class);
            member.setCount(member.getCount() + 30);
            requestAgent.request("member.update", member);

            if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
              System.out.println("포인트 적립 실패!");
              return;
            }

            System.out.println("금일 첫 출석체크로 30포인트가 적립되었습니다.");

            DateCheck dateCheck = new DateCheck();
            dateCheck.setDate(today);
            dateCheck.setUser(loginUser);

            requestAgent.request("dateCheck.insert", dateCheck);

            if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
              System.out.println("오늘 날짜 저장 실패!");
              return;
            }

            // 3일 지난 데이터 정리
            request.getRequestDispatcher("/auth/deleteCheck").forward(request);
            return;
          }
        }
        // 오늘 날짜가 없을 때

        requestAgent.request("member.selectOneById", params);
        if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
          System.out.println("해당 회원이 없습니다.");
          return;
        }
        Member member = requestAgent.getObject(Member.class);
        member.setCount(member.getCount() + 30);
        requestAgent.request("member.update", member);

        if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
          System.out.println("포인트 적립 실패!");
          return;
        }

        System.out.println("금일 첫 출석체크로 30포인트가 적립되었습니다.");

        DateCheck dateCheck = new DateCheck();
        dateCheck.setDate(today);
        dateCheck.setUser(loginUser);

        requestAgent.request("dateCheck.insert", dateCheck);

        if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
          System.out.println("오늘 날짜 저장 실패!");
          return;
        }

        // 3일 지난 데이터 정리
        request.getRequestDispatcher("/auth/deleteCheck").forward(request);
        return;
      }

    }
  }
}
