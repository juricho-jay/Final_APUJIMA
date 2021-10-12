package pms.table;

import pms.domain.CounselingMember;
import server.DataProcessor;
import server.Request;
import server.Response;

// 역할
// - 회원 데이터를 처리하는 일을 한다.
// 
public class CounselingMemberTable extends JsonDataTable<CounselingMember> implements DataProcessor {

  public CounselingMemberTable() {
    super("counselingMember.json", CounselingMember.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    switch (request.getCommand()) {
      case "counselingMember.insert": insert(request, response); break;
      case "counselingMember.selectList": selectList(request, response); break;
      // id 찾기 (이름+번호)
      // case "counselingMember.selectOneByNamePhoneNum": selectOneByNamePhoneNum(request, response); break;
      // pw 찾기 (아이디+번호)
      //  case "counselingMember.selectOneByIdPhoneNum": selectOneByIdPhoneNum(request, response); break;
      //      case "counselingMember.selectOneById": selectOneById(request, response); break;
      case "counselingMember.selectOneByName": selectOneByName(request, response); break;
      case "counselingMember.update": update(request, response); break; //id로 인덱스 찾아서 인덱스로 리스트에서 변경
      case "counselingMember.delete": delete(request, response); break;//id로 인덱스 찾아서 인덱스로 리스트에서 삭제
      case "counselingMember.check": check(request, response); break; //id 중복 검사
      // id 찾기 (이름+번호)
      //      case "counselingMember.selectOneByNamePhoneNum": selectOneByNamePhoneNum(request, response); break;
      // pw 찾기 (아이디+번호)
      //      case "counselingMember.selectOneByIdPhoneNum": selectOneByIdPhoneNum(request, response); break;
      default:
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }



  private void insert(Request request, Response response) throws Exception {
    CounselingMember counselingMember = request.getObject(CounselingMember.class);
    list.add(counselingMember);
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

  //  private void selectOne(Request request, Response response) throws Exception {
  //    String id = request.getParameter("id");
  //    Member m = findById(id);
  //
  //    if (m != null) {
  //      response.setStatus(Response.SUCCESS);
  //      response.setValue(m);
  //    } else {
  //      response.setStatus(Response.FAIL);
  //      response.setValue("해당 회원을 찾을 수 없습니다.");
  //    }
  //  }

  //  private void selectOneByIdPassword(Request request, Response response) throws Exception {
  //    String id = request.getParameter("id");
  //    String password = request.getParameter("password");
  //
  //    CounselingMember counselingMember = null;
  //    for (CounselingMember m : list) {
  //      if (m.getName().equals(id) && m.getPassword().equals(password)) {
  //        counselingMember = m;
  //        break;
  //      }
  //    }
  //
  //    if (counselingMember != null) {
  //      response.setStatus(Response.SUCCESS);
  //      response.setValue(member);
  //    } else {
  //      response.setStatus(Response.FAIL);
  //      response.setValue("ID와 암호가 일치하지 않습니다.");
  //    }
  //  }

  //  private void selectOneById(Request request, Response response) throws Exception {
  //    String id = request.getParameter("id");
  //    CounselingMember counselingMember = null;
  //
  //    for (CounselingMember m : list) {
  //      if (m.getName().equals(id)) {
  //        counselingMember = m;
  //        break;
  //      }
  //    }
  //
  //    if (member != null) {
  //      response.setStatus(Response.SUCCESS);
  //      response.setValue(member);
  //    } else {
  //      response.setStatus(Response.FAIL);
  //      response.setValue("해당 회원을 찾을 수 없습니다.");
  //    }
  //  }


  private void selectOneByName(Request request, Response response) throws Exception {
    String name = request.getParameter("name");
    CounselingMember counselingMember = null;

    for (CounselingMember m : list) {
      if (m.getName().equals(name)) {
        counselingMember = m;
        break;
      }
    }

    if (counselingMember != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(counselingMember);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 회원을 찾을 수 없습니다.");
    }
  }
  //  private void update(Request request, Response response) throws Exception {
  //    CounselingMember counselingMember = request.getObject(CounselingMember.class);
  //
  //    int index = indexOf(counselingMember.getId());
  //    if (index == -1) {
  //      response.setStatus(Response.FAIL);
  //      response.setValue("해당 회원을 찾을 수 없습니다.");
  //      return;
  //    }
  //
  //    list.set(index, counselingMember);
  //    response.setStatus(Response.SUCCESS);
  //  }
  private void update(Request request, Response response) throws Exception {
    CounselingMember counselingMember = request.getObject(CounselingMember.class);

    int index = indexOf(counselingMember.getName());
    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 회원을 찾을 수 없습니다.");
      return;
    }

    list.set(index, counselingMember);
    response.setStatus(Response.SUCCESS);
  }

  private void delete(Request request, Response response) throws Exception {
    String id = request.getParameter("id");
    int index = indexOf(id);

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 회원을 찾을 수 없습니다.");
      return;
    }

    list.remove(index);
    response.setStatus(Response.SUCCESS);
  }

  private void check(Request request, Response response) throws Exception {
    String name = request.getParameter("name");

    CounselingMember counselingMember = null;
    for (CounselingMember m : list) {
      if (m.getName().equals(name)) {
        counselingMember = m;
        break;
      }
    }

    if (counselingMember != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(counselingMember);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("이름과 번호가 일치하지 않습니다.");
    }

  }

  //  private Member findById(String id) {
  //    for (Member m : list) {
  //      if (m.getId().equals(id)) {
  //        return m;
  //      }
  //    }
  //    return null;
  //  }
  //
  //  private Member findByName(String name) {
  //    for (Member m : list) {
  //      if (m.getName().equals(name)) {
  //        return m;
  //      }
  //    }
  //    return null;
  //  }

  //  private void selectOneByNamePhoneNum(Request request, Response response) {
  //    String name = request.getParameter("name");
  //    String phoneNum = request.getParameter("phoneNum");
  //
  //    CounselingMember counselingMember = null;
  //    for (CounselingMember m : list) {
  //      if (m.getName().equals(name) && m.getPassword().equals(phoneNum)) {
  //        counselingMember = m;
  //        break;
  //      }
  //    }
  //
  //    if (counselingMember != null) {
  //      response.setStatus(Response.SUCCESS);
  //      response.setValue(counselingMember);
  //    } else {
  //      response.setStatus(Response.FAIL);
  //      response.setValue("이름과 번호가 일치하지 않습니다.");
  //    }
  //
  //
  //  }

  //  private void selectOneByIdPhoneNum(Request request, Response response) {
  //    String id = request.getParameter("id");
  //    String phoneNum = request.getParameter("phoneNum");
  //
  //    Member member = null;
  //    for (Member m : list) {
  //      if (m.getId().equals(id) && m.getPassword().equals(phoneNum)) {
  //        member = m;
  //        break;
  //      }
  //    }
  //
  //    if (member != null) {
  //      response.setStatus(Response.SUCCESS);
  //      response.setValue(member);
  //    } else {
  //      response.setStatus(Response.FAIL);
  //      response.setValue("이름과 번호가 일치하지 않습니다.");
  //    }
  //
  //  }



  //list.set, list.remove에서 인덱스 번호 필요 > 이름으로 인덱스 번호 찾는 메서드
  private int indexOf(String name) { 
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getName().equals(name)) {
        return i;   // 아이디로 찾은 경우 인덱스 번호 리턴
      }
    }
    return -1; // 없으면 -1로 리턴
  }

}













