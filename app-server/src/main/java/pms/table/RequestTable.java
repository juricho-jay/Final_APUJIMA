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
      //      case "medicine.selectOne": selectOne(request, response); break;
      case "request.selectList": selectList(request, response); break;
      case "request.delete": delete(request, response); break;
      default:
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insert(Request request, Response response) throws Exception {
    Medicine medicine = request.getObject(Medicine.class);
    list.add(medicine);
    System.out.println("서버에 의약품 데이터 저장 성공!");
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

    String name = request.getParameter("input");
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

  //  private Medicine selectOneByName() throws Exception {
  //    String name = request.getParameter("name");
  //    Medicine medicine = null;
  //    for (Medicine m : list) {
  //      if (m.getName().equals(name)) {
  //        medicine = m;
  //        return medicine;
  //      }
  //    }
  //    return null;
  //  }


}
