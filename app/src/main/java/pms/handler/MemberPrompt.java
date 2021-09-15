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

<<<<<<< HEAD
  protected Member findById(String id) {
=======
  public Member findById(String id) {
>>>>>>> ff4e645331d9326a45df86ab7f0722fcbdbdfb85
    for (Member member : memberList) {
      if (member.getId().equalsIgnoreCase(id)) {
        return member;
      }
    }
    return null;
  }

<<<<<<< HEAD
  protected static Member findById(String id, List<Member> memberList) {
=======
  public static Member findById(String id, List<Member> memberList) {
>>>>>>> ff4e645331d9326a45df86ab7f0722fcbdbdfb85
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
<<<<<<< HEAD
      String memberId = Prompt.inputString(label);
      if (memberId.length() == 0) {
        return null;
      }

      Member member = findById(memberId, memberList);
=======
      String memberName = Prompt.inputString(label);
      if (memberName.length() == 0) {
        return null;
      }

      Member member = findById(memberName, memberList);
>>>>>>> ff4e645331d9326a45df86ab7f0722fcbdbdfb85
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
<<<<<<< HEAD

}
=======
}
>>>>>>> ff4e645331d9326a45df86ab7f0722fcbdbdfb85
