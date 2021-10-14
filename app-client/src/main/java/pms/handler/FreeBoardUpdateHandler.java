package pms.handler;

import pms.dao.FreeBoardDao;
import pms.domain.FreeBoard;
import util.Prompt;

public class FreeBoardUpdateHandler implements Command {

  FreeBoardDao freeBoardDao;

  public FreeBoardUpdateHandler(FreeBoardDao freeBoardDao) {
    this.freeBoardDao = freeBoardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[게시글 변경] 페이지입니다.");
    System.out.println();
    int no = (int)request.getAttribute("no");

    FreeBoard freeBoard = freeBoardDao.findByNo(no);

    //    HashMap<String,String> params = new HashMap<>();
    //    params.put("no", String.valueOf(no));
    //
    //    requestAgent.request("freeBoard.selectOne", params);
    //
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println("해당 번호의 게시글이 없습니다.");
    //      return;
    //    }
    //
    //    FreeBoard freeBoard = requestAgent.getObject(FreeBoard.class);

    String title = Prompt.inputString(String.format("제목(%s)> ", freeBoard.getTitle()));
    String content = Prompt.inputString(String.format("내용(%s)> ", freeBoard.getContent()));

    String input = Prompt.inputString("❗ 정말 변경하시겠습니까? (y/N)> ");
    if(input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("게시글 변경을 취소하였습니다.");
      return;
    }

    freeBoard.setTitle(title);
    freeBoard.setContent(content);

    //    requestAgent.request("freeBoard.update", freeBoard);
    //
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println("게시글 변경 실패!");
    //      return;
    //    }

    System.out.println("게시글을 변경하였습니다.");

  }
}
