package apus.handler;

import org.apache.ibatis.session.SqlSession;
import Menu.Menu;
import apus.dao.MemberDao;
import util.Prompt;

public class MemberDeleteHandler implements Command {

  MemberDao memberDao;
  SqlSession sqlSession;

  public MemberDeleteHandler(MemberDao memberDao, SqlSession sqlSession) {
    this.memberDao = memberDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("[회원 탈퇴]");

    String loginUserId = AuthLoginHandler.getLoginUser().getId();

    String status = Prompt.inputString("정말 탈퇴하시겠습니까?(y/N) ");
    if (status.equalsIgnoreCase("n") || status.length() == 0) {
      System.out.println("회원 삭제를 취소하였습니다.");
      return;
    }

    memberDao.delete(loginUserId);
    sqlSession.commit();

    AuthLoginHandler.loginUser = null;
    AuthLoginHandler.userAccessLevel = Menu.ACCESS_LOGOUT;
    System.out.println("회원 탈퇴가 완료되었습니다. 그동안 이용해주셔서 감사합니다.");
  }
}