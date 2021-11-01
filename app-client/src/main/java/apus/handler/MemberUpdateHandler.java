package apus.handler;

import apus.dao.MemberDao;
import apus.domain.Member;
import util.Prompt;

public class MemberUpdateHandler implements Command {

  MemberDao memberDao;

  public MemberUpdateHandler(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[내정보 변경]");

    String loginUserId = AuthLoginHandler.getLoginUser().getId();

    Member loginUser = memberDao.findById(loginUserId);

    String name = Prompt.inputString("이름(" + loginUser.getName()  + ")? ");
    String id = Prompt.inputString("아이디(" + loginUser.getId() + ")? ");
    String password = Prompt.inputString("암호? ");
    String email = Prompt.inputString("이메일(" + loginUser.getEmail() + ")? ");
    String phoneNum = Prompt.inputString("전화(" + loginUser.getPhoneNum() + ")? ");
    String photo = Prompt.inputString("사진(" + loginUser.getPhoto() + ")? ");

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("회원 변경을 취소하였습니다.");
      return;
    }

    loginUser.setName(name);
    loginUser.setEmail(id);
    loginUser.setEmail(password);
    loginUser.setPassword(email);
    loginUser.setPhoto(phoneNum);
    loginUser.setPhoneNum(photo);

    memberDao.update(loginUser);

    System.out.println("회원을 변경하였습니다.");
  }
}