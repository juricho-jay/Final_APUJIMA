package pms.table;

import pms.domain.Member;
import server.DataProcessor;
import server.Request;
import server.Response;

public class MemberTable extends JsonDataTable<Member> implements DataProcessor {

  public MemberTable() {
    super("member.json", Member.class);
  }

  public void execute(Request request, Response response) throws Exception {
    switch (request.getCommand()) {
      case "member.insert": insert(request, response); break;
      case "member.selectList": selectList(request, response); break;
      case "member.check": check(request, response); break;
      case "member.selectOne": selectOne(request, response); break;
      case "member.selectOneById": selectOneById(request, response); break;
      case "member.selectOneByIdPassword": selectOneByIdPassword(request, response); break;
      case "member.selectOneByName": selectOneByName(request, response); break;
      case "member.update": update(request, response); break;
      case "member.delete": delete(request, response); break;
      default:
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insert(Request request, Response response) throws Exception {
    Member member = request.getObject(Member.class);
    list.add(member);
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

  private void check(Request request, Response response) throws Exception {
    Member member = request.getObject(Member.class);
    for(int i = 0 ; i < list.size() ; i++) {
      if(member.getId().equals(list.get(i).getId())) {
        System.out.println("중복되는 아이디 입니다. 다른 아이디를 사용해 주세요.");
        member.setId("");
        response.setStatus(Response.FAIL);
        return;
      }
    }
    response.setStatus(Response.SUCCESS);
  }

  private void selectOne(Request request, Response response) throws Exception {
    String id = request.getParameter("id");
    Member m = findById(id);

    if (m != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(m);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 회원을 찾을 수 없습니다.");
    }
  }

  private void selectOneById(Request request, Response response) throws Exception {
    String id = request.getParameter("id");
    Member member = null;

    for (Member m : list) {
      if (m.getId().equals(id)) {
        member = m;
        break;
      }
    }

    if (member != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(member);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 회원을 찾을 수 없습니다.");
    }
  }


  private void selectOneByIdPassword(Request request, Response response) throws Exception {
    String id = request.getParameter("id");
    String password = request.getParameter("password");

    Member member = null;
    for (Member m : list) {
      if (m.getId().equals(id) && m.getPassword().equals(password)) {
        member = m;
        break;
      }
    }

    if (member != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(member);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 이메일과 암호를 가진 회원을 찾을 수 없습니다.");
    }
  }

  private void selectOneByName(Request request, Response response) throws Exception {
    String name = request.getParameter("name");
    System.out.println("-----> " + name);
    Member member = null;
    for (Member m : list) {
      if (m.getName().equals(name)) {
        member = m;
        break;
      }
    }

    if (member != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(member);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 이름의 회원을 찾을 수 없습니다.");
    }
  }


  private void update(Request request, Response response) throws Exception {
    Member member = request.getObject(Member.class);

    int index = indexOf(member.getId());
    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 회원을 찾을 수 없습니다.");
      return;
    }

    list.set(index, member);
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

  private Member findById(String id) {
    for (Member m : list) {
      if (m.getId().equals(id)) {
        return m;
      }
    }
    return null;
  }

  //list.set, list.remove에서 인덱스 번호 필요 > id로 인덱스 번호 찾는 메서드
  private int indexOf(String memberId) { 
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getId().equals(memberId)) {
        return i;   // 아이디로 찾은 경우 인덱스 번호 리턴
      }
    }
    return -1; // 없으면 -1로 리턴
  }

}













