package apus.handler;

import java.util.HashMap;
import apus.domain.Member;
import request.RequestAgent;
import util.Prompt;

public class MemberFindIdHandler implements Command {

  RequestAgent requestAgent;

  public MemberFindIdHandler (RequestAgent requestAgent) {
    this.requestAgent = requestAgent;

  }
  @Override
  public void execute(CommandRequest request) throws Exception {
    while (true) {
      //      Member member = new Member();
      System.out.println("[ID 찾기] 페이지입니다.");
      System.out.println("(취소: #)");
      String name = Prompt.inputString("이름> ");
      if (name.contains("#")) {
        return;
      }
      String phoneNum = Prompt.inputString("휴대폰> ");

      HashMap<String,String> params = new HashMap<>();
      params.put("name", name);
      params.put("phoneNum", phoneNum);

      requestAgent.request("member.selectOneByNamePhoneNum", params);


      if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println("아이디를 찾을 수 없습니다.");
        System.out.println();
        continue;
      } else {
        Member member = requestAgent.getObject(Member.class);
        System.out.println();
        System.out.printf ("회원님의 아이디 입니다. [ %s ]", member.getId());
        System.out.println();
        return;
      }
    }
  }

}
