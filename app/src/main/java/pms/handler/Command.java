package pms.handler;

public interface Command {
  void execute(); //모든 메서드 > execute 통일
  // 구현 클래스에서 implements Command 써야함

}