package pms.table;

import java.util.ArrayList;
import pms.domain.FreeBoard;
import server.DataProcessor;
import server.Request;
import server.Response;

public class FreeBoardTable extends JsonDataTable<FreeBoard> implements DataProcessor {

  public FreeBoardTable() {
    super("freeBoard.json", FreeBoard.class);
  }

  public void execute(Request request, Response response) throws Exception {
    switch (request.getCommand()) {
      case "freeBoard.insert": insert(request, response); break;
      case "freeBoard.selectList": selectList(request, response); break;
      case "freeBoard.selectOne": selectOne(request, response); break;
      case "freeBoard.selectListByKeyword":selectListByKeyword(request , response); break;
      case "freeBoard.delete": delete(request, response); break;
      default:
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insert(Request request, Response response) throws Exception {
    FreeBoard board = request.getObject(FreeBoard.class);
    list.add(board);
    System.out.println("서버에 보드 데이터 저장 성공!");
    response.setStatus(Response.SUCCESS);
  }

  private void selectList(Request request, Response response) throws Exception {
    if (list.size() == 0) {
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

  //  @SuppressWarnings("unchecked")
  //  private void selectOne2(Request request, Response response) throws Exception {
  //    Map<String,String> params = request.getObject(Map.class);
  //    int no = Integer.parseInt(params.get("num"));
  //    FreeBoard board = findByNo(no);
  //    if (board != null) {
  //      response.setStatus(Response.SUCCESS);
  //      response.setValue(board);
  //    } else {
  //      response.setStatus(Response.FAIL);
  //    }
  //
  //  }
  private void selectListByKeyword(Request request, Response response) throws Exception {
    String keyword = request.getParameter("keyword");

    ArrayList<FreeBoard> searchResult = new ArrayList<>();
    for (FreeBoard freeBoard : list) {
      if (!freeBoard.getTitle().contains(keyword) &&
          !freeBoard.getContent().contains(keyword) &&
          !freeBoard.getWriter().getId().contains(keyword)) {
        continue;
      }
      searchResult.add(freeBoard);
    }

    response.setStatus(Response.SUCCESS);
    response.setValue(searchResult);
  }

  private FreeBoard findByNo(int no) {
    for (FreeBoard b : list) {
      if (b.getNo() == no) {
        return b;
      }
    }
    return null;
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

  private int indexOf(int no) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }

}













