package pms.table;

import java.util.Calendar;
import java.util.Date;
import pms.domain.DateCheck;
import server.DataProcessor;
import server.Request;
import server.Response;



public class DateCheckTable extends JsonDataTable<DateCheck> implements DataProcessor {

  public DateCheckTable() {
    super("dateCheck.json", DateCheck.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    switch (request.getCommand()) {
      case "dateCheck.insert": insert(request, response); break;
      case "dateCheck.selectList": selectList(request, response); break;
      case "dateCheck.delete": delete(request, response); break;
      default:
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insert(Request request, Response response) throws Exception {
    DateCheck dateCheck = request.getObject(DateCheck.class);
    list.add(dateCheck);
    response.setStatus(Response.SUCCESS);
  }


  private void selectList(Request request, Response response) throws Exception {
    if (list.size() == 0) {
      response.setStatus(Response.FAIL);
      response.setValue(null);
      return;
    }
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

  // 3일 지난 데이터 삭제하기
  private void delete(Request request, Response response) throws Exception {
    Calendar cal = Calendar.getInstance(); // Calendar는 추상 클래스라 new로 객체 생성이 불가, getInstance사용
    long todayMil = cal.getTimeInMillis(); // 현재 시간 (밀리 세컨드)
    long oneDayMil = 24*60*60*1000; // 일 단위 (밀리 세컨드)

    Calendar dataCal = Calendar.getInstance();
    Date dataDate = null;

    if (list.size() == 0) {
      return;
    }

    for (int i = 0; i < list.size(); i++) {

      dataDate = list.get(i).getDate(); //i번째의 시간

      //현재 시간과 기존 시간의 시간차 계산 (밀리세컨드)
      dataCal.setTime(dataDate);
      long diffMil = todayMil - dataCal.getTimeInMillis();

      //날짜로 계산
      int diffDay = (int)(diffMil/oneDayMil);

      //3일 지난 데이터 삭제
      if (diffDay > 3) {
        list.remove(i);
        response.setStatus(Response.SUCCESS);
        return;
      }

      response.setStatus(Response.FAIL);
    }
  }


}













