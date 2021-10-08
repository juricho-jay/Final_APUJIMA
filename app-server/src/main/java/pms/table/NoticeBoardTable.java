package pms.table;

import java.util.ArrayList;
import pms.domain.NoticeBoard;
import server.DataProcessor;
import server.Request;
import server.Response;

// 역할
// - 게시글 데이터를 처리하는 일을 한다.
// 
public class NoticeBoardTable extends JsonDataTable<NoticeBoard> implements DataProcessor {

  public NoticeBoardTable() {
    super("noticeBoard.json", NoticeBoard.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    switch (request.getCommand()) {
      case "noticeBoard.insert": insert(request, response); break;
      case "noticeBoard.selectList": selectList(request, response); break;
      case "noticeBoard.selectListByKeyword": selectListByKeyword(request, response); break;
      case "noticeBoard.selectOne": selectOne(request, response); break;
      case "noticeBoard.update": update(request, response); break;
      case "noticeBoard.delete": delete(request, response); break;
      default:
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insert(Request request, Response response) throws Exception {
    NoticeBoard noticeBoard = request.getObject(NoticeBoard.class);
    list.add(noticeBoard);
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

  private void selectListByKeyword(Request request, Response response) throws Exception {
    String keyword = request.getParameter("keyword");

    ArrayList<NoticeBoard> searchResult = new ArrayList<>();
    for (NoticeBoard noticeBoard : list) {
      if (!noticeBoard.getTitle().contains(keyword) &&
          !noticeBoard.getContent().contains(keyword) &&
          !noticeBoard.getWriter().getName().contains(keyword)) {
        continue;
      }
      searchResult.add(noticeBoard);
    }

    response.setStatus(Response.SUCCESS);
    response.setValue(searchResult);
  }

  private void selectOne(Request request, Response response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    NoticeBoard noticeBoard = findByNo(no);

    if (noticeBoard != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(noticeBoard);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 게시글을 찾을 수 없습니다.");
    }
  }

  private void update(Request request, Response response) throws Exception {
    NoticeBoard noticeBoard = request.getObject(NoticeBoard.class);

    int index = indexOf(noticeBoard.getNo());
    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 게시글을 찾을 수 없습니다.");
      return;
    }

    list.set(index, noticeBoard);
    response.setStatus(Response.SUCCESS);
  }

  private void delete(Request request, Response response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    int index = indexOf(no);

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 게시글을 찾을 수 없습니다.");
      return;
    }

    list.remove(index);
    response.setStatus(Response.SUCCESS);
  }

  private NoticeBoard findByNo(int no) {
    for (NoticeBoard noticeBoard : list) {
      if (noticeBoard.getNo() == no) {
        return noticeBoard;
      }
    }
    return null;
  }

  private int indexOf(int noticeBoardNo) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == noticeBoardNo) {
        return i;
      }
    }
    return -1;
  }

}


