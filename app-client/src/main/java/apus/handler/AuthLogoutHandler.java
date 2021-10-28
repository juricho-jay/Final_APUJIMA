package apus.handler;

import Menu.Menu;

public class AuthLogoutHandler implements Command {
  @Override
  public void execute(CommandRequest request) {
    System.out.println("[로그아웃]");

    AuthLoginHandler.loginUser = null;
    AuthLoginHandler.userAccessLevel = Menu.ACCESS_LOGOUT;
    System.out.println("정상적으로 로그아웃되었습니다.");
  }
}

