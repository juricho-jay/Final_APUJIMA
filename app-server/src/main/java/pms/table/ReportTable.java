package pms.table;

import pms.domain.FreeBoard;
import server.DataProcessor;
import server.Request;
import server.Response;

public class ReportTable extends JsonDataTable<FreeBoard> implements DataProcessor{

  public ReportTable() {
    super("report.json", FreeBoard.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    switch (request.getCommand()) {
      case "report.insert": insert(request, response); break;
      case "report.selectList": selectList(request, response); break;
      case "report.selectOne": selectOne(request, response); break;
      case "report.delete": delete(request, response); break;
      case "report.autoDelete": autoDelete(request, response); break;
      default:
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }



  private void insert(Request request, Response response) throws Exception {
    FreeBoard board = request.getObject(FreeBoard.class);
    list.add(board);
    System.out.println("서버에 신고 데이터 저장 성공!");
    response.setStatus(Response.SUCCESS);
  }

  private void selectList(Request request, Response response) throws Exception {
    if(list.size() == 0) {
      response.setStatus(Response.FAIL);
      return;
    }
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

  private void selectOne(Request request, Response response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    FreeBoard b = findByNo(no);

    if (b != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(b);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 게시글을 찾을 수 없습니다.");
    }
  }

  private void delete(Request request, Response response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    int index = indexOf(no);

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("삭제 목록 게시판에 입력하신 게시판 번호가 없습니다.");
      return;
    }

    list.remove(index);
    response.setStatus(Response.SUCCESS);
  }

  private void autoDelete(Request request, Response response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));

    if (list.size() == 0) {
      System.out.println("목록이 없습니다.");
      response.setStatus(Response.FAIL);
      return;
    }

    int count = 0;
    for (int i = list.size() - 1; i >= 0; i--) {
      if (list.get(i).getNo() == no) {
        list.remove(i);
        count++;
      }
    }

    if (count == 0) {
      response.setStatus(Response.FAIL);
      return;
    }

    response.setStatus(Response.SUCCESS);
  }



  private int indexOf(int no) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }



  private FreeBoard findByNo(int no) {
    for (FreeBoard b : list) {
      if (b.getNo() == no) {
        return b;
      }
    }
    return null;
  }


}
