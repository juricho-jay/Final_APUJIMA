package pms.handler;

public interface Command {
  void execute(CommandRequest request) throws Exception;
  // 구현 클래스에서 implements Command 써야함

}