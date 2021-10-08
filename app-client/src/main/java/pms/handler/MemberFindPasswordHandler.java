package pms.handler;

import java.util.HashMap;
import pms.domain.Member;
import request.RequestAgent;
import util.Prompt;

public class MemberFindPasswordHandler implements Command {

  RequestAgent requestAgent;

  public MemberFindPasswordHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;

  }
  @Override
  public void execute(CommandRequest request) throws Exception {
    while (true) {
      System.out.println("[PW 찾기] 페이지입니다.");
      System.out.println("(취소: #)");
      String id = Prompt.inputString("아이디> ");
      if (id.contains("#")) {
        return;
      }
      String phoneNum = Prompt.inputString("휴대폰> ");

      HashMap<String,String> params = new HashMap<>();
      params.put("id", id);
      params.put("phoneNum", phoneNum);

      requestAgent.request("member.selectOneByIdPhoneNum", params);


      if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println("입력이 잘못되었습니다. 정보를 찾을 수 없습니다.");
        System.out.println();
        continue;
      } else {
        int num = (int)(Math.random() * 1000000);
        Member member = requestAgent.getObject(Member.class);

        System.out.println("임시 비밀번호를 발급해 드리겠습니다.");
        System.out.printf("임시 비밀번호는 %d 입니다. 임시 비밀번호로 로그인 해주세요.", num);
        member.setPassword(Integer.toString(num));
        System.out.println();
        return;
      }
    }
  }

}
