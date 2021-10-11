package pms.table;

import pms.domain.Medicine;
import server.DataProcessor;
import server.Request;
import server.Response;

public class RequestTable extends JsonDataTable<Medicine> implements DataProcessor{

  public RequestTable() {
    super("request.json", Medicine.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    switch (request.getCommand()) {
      case "request.insert": insert(request, response); break;
      case "request.selectOneByName": selectOneByName(request, response); break;
      case "request.selectList": selectList(request, response); break;
      case "request.delete": delete(request, response); break;
      default:
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  // 의사가 약품 승인 요청 넣을 때 > request.insert
  private void insert(Request request, Response response) throws Exception {
    Medicine medicine = request.getObject(Medicine.class);
    list.add(medicine);
    System.out.println("의약품 등록 요청 목록에 저장 성공!");
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

  private void delete(Request request, Response response) throws Exception {

    String name = request.getParameter("name");
    int index = indexOf(name);

    if (index == -1) {
      response.setStatus(Response.FAIL);
      return;
    }

    list.remove(index);
    response.setStatus(Response.SUCCESS);
  }

  private int indexOf(String name) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getName() == name) {
        return i;
      }
    }
    return -1;
  }

  private void selectOneByName(Request request, Response response) throws Exception {
    String name = request.getParameter("name");

    Medicine medicine = null;
    for (Medicine m : list) {
      if (m.getName().equals(name)) {
        medicine = m;
        break;
      }
    }

    if (medicine != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(medicine);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 이름의 회원을 찾을 수 없습니다.");
    }
  }


}
