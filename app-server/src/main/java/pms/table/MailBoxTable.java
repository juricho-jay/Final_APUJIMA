package pms.table;

import pms.domain.MailBox;
import server.DataProcessor;
import server.Request;
import server.Response;

public class MailBoxTable extends JsonDataTable<MailBox> implements DataProcessor{

  public MailBoxTable() {
    super("mailBox.json", MailBox.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    switch (request.getCommand()) {
      case "mailBox.insert": insert(request, response); break;
      case "mailBox.selectList": selectList(request, response); break;
      case "mailBox.selectOne": selectOne(request, response); break;
      //  case "mailBox.selectOne2": selectOne2(request, response); break;
      case "mailBox.selectOneById": selectOneById( request, response); break;
      case "mailBox.delete": delete(request, response); break;
      case "mailBox.update": update(request, response); break;
      default:
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insert(Request request, Response response) throws Exception {
    MailBox mailBox = request.getObject(MailBox.class);
    list.add(mailBox);
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

  private void delete(Request request, Response response) throws Exception {
    MailBox mailBox = request.getObject(MailBox.class);
    int index = indexOf(mailBox.getMailNo());

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("쪽지함에 입력하신 번호가 없습니다.");
      return;
    }

    list.remove(index);
    response.setStatus(Response.SUCCESS);
  }

  private void selectOne(Request request, Response response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));

    MailBox b = findByNo(no);


    if (b != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(b);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 쪽지함을 찾을 수 없습니다.");
    }
  }

  //  private void selectOne2(Request request, Response response) throws Exception {
  //    int no = Integer.parseInt(request.getParameter("no"));
  //
  //    MailBox b = getfindByNo(no);
  //
  //    if (b != null) {
  //      response.setStatus(Response.SUCCESS);
  //      response.setValue(b);
  //    } else {
  //      response.setStatus(Response.FAIL);
  //      response.setValue("해당 번호의 쪽지함을 찾을 수 없습니다.");
  //    }
  //  }

  private int indexOf(int no) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getMailNo() == no) {
        return i;
      }
    }
    return -1;
  }

  private MailBox findByNo(int no) {
    for (MailBox b : list) {
      if (b.getMailNo() == no) {
        return b;
      }
    }
    return null;
  }

  private MailBox getfindByNo(int no) {
    for (MailBox b : list) {
      if (b.getNo() == no) {
        return b;
      }
    }
    return null;
  }

  private void update(Request request, Response response) throws Exception {
    MailBox mailBox = request.getObject(MailBox.class);

    int index = indexOf(mailBox.getMailNo());
    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 회원을 찾을 수 없습니다.");
      return;
    }

    list.set(index, mailBox);
    response.setStatus(Response.SUCCESS);
  }

  private void selectOneById(Request request, Response response) throws Exception {
    String id = request.getParameter("id");
    MailBox mailBox = null;
    for (MailBox m : list) {
      if (m.getReceiver().equals(id)) {
        mailBox = m;
        break;
      }
    }

    if (mailBox != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(mailBox);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 회원을 찾을 수 없습니다.");
    }
  }




}
