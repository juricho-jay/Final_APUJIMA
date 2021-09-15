package pms.handler;

import java.util.ArrayList;
import java.util.List;
import pms.domain.Member;
import util.Prompt;

public class MemberPrompt {
  List<Member> memberList;

  public MemberPrompt(List<Member> memberList) {
    this.memberList = memberList;
  }

  protected Member findById(String id) {
    for (Member member : memberList) {
      if (member.getId().equalsIgnoreCase(id)) {
        return member;
      }
    }
    return null;
  }

  protected static Member findById(String id, List<Member> memberList) {
    for (Member member : memberList) {
      if (member.getId().equalsIgnoreCase(id)) {
        return member;
      }
    }
    return null;
  }

  public Member promptMember(String label) {
    while (true) {
      String memberId = Prompt.inputString(label);
      if (memberId.length() == 0) {
        return null;
      }

      Member member = findById(memberId);
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

  public List<Member> promptMembers(String label) {
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
