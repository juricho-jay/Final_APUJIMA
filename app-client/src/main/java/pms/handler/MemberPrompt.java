package pms.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import pms.domain.Member;
import request.RequestAgent;
import util.Prompt;

public class MemberPrompt {

  RequestAgent requestAgent;

  public MemberPrompt(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  protected Member findById(String id) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("id", id);

    requestAgent.request("member.selectOneById", params);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }
    return requestAgent.getObject(Member.class);
  }

  protected static Member findById(String id, List<Member> memberList) {
    for (Member member : memberList) {
      if (member.getId().equalsIgnoreCase(id)) {
        return member;
      }
    }
    return null;
  }

  public Member promptMember(String label) throws Exception {
    while (true) {
      String memberName = Prompt.inputString(label);
      if (memberName.length() == 0) {
        return null;
      }

      Member member = findById(memberName);
      if (member != null) {
        return member;
      }

      System.out.println("등록된 회원이 아닙니다.");
    }
  }

  public static Member promptMember(String label, List<Member> memberList) {
    while (true) {
      String memberId = Prompt.inputString(label);
      if (memberId.length() == 0) {
        return null;
      }

      Member member = findById(memberId, memberList);
      if (member != null) {
        return member;
      }

      System.out.println("등록된 회원이 아닙니다.");
    }
  }

  public List<Member> promptMembers(String label) throws Exception {
    ArrayList<Member> members = new ArrayList<>();

    while (true) {
      String memberId = Prompt.inputString(label);
      Member member = findById(memberId);
      if (member != null) {
        members.add(member);
        continue;
      } else if (memberId.length() == 0) {
        break;
      } 
      System.out.println("등록된 회원이 아닙니다.");
    }
    return members;
  }
}


