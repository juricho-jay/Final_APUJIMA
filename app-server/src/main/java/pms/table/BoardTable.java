package pms.table;

import java.util.ArrayList;
import pms.domain.Board;
import server.DataProcessor;
import server.Request;
import server.Response;

public class BoardTable extends JsonDataTable<Board> implements DataProcessor {

  public BoardTable() {
    super("board.json", Board.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    switch (request.getCommand()) {
      case "board.insert": insert(request, response); break;
      case "board.selectList": selectList(request, response); break;
      case "board.selectOne": selectOne(request, response); break;
      case "board.selectListByKeyword":selectListByKeyword(request , response); break;
      case "board.update": update(request, response); break;
      case "board.delete": delete(request, response); break;
      default:
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insert(Request request, Response response) throws Exception {
    Board board = request.getObject(Board.class);
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
    Board b = findByNo(no);

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
  //    Board board = findByNo(no);
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

    ArrayList<Board> searchResult = new ArrayList<>();
    for (Board board : list) {
      if (!board.getTitle().contains(keyword) &&
          !board.getContent().contains(keyword) &&
          !board.getWriter().getId().contains(keyword)) {
        continue;
      }
      searchResult.add(board);
    }

    if(searchResult.size() == 0) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 키워드로 검색 가능한 게시글이 없습니다.");
      return;
    }

    response.setStatus(Response.SUCCESS);
    response.setValue(searchResult);
  }

  private void update(Request request, Response response) throws Exception {
    Board board = request.getObject(Board.class);

    int index = indexOf(board.getNo());
    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 게시글을 찾을 수 없습니다.");
      return;
    }

    list.set(index, board);
    response.setStatus(Response.SUCCESS);
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

  private Board findByNo(int no) {
    for (Board b : list) {
      if (b.getNo() == no) {
        return b;
      }
    }
    return null;
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













