package pms.handler;

import java.util.Collection;
import java.util.HashMap;
import pms.domain.Bucket;
import request.RequestAgent;
import util.Prompt;

public class BucketSearchHandler implements Command {
  RequestAgent requestAgent;

  public BucketSearchHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }


  @Override
  public void execute(CommandRequest request) throws Exception{

    int count = 0;
    System.out.println();
    System.out.println("[나만의 버킷리스트 검색] 페이지입니다.");
    System.out.println();

    String input = Prompt.inputString("검색어> ");
    HashMap<String, String> params = new HashMap<>();
    params.put("keyword", input);

    requestAgent.request("bucket.search", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("검색 실패!");
      return;
    }

    Collection<Bucket> bucketList = requestAgent.getObjects(Bucket.class);


    for (Bucket bucket : bucketList) {
      if (bucket.getWriter().equals(AuthLoginHandler.getLoginUser().getId())) {
        System.out.printf("%d, %s, %s, %s, %s\n",
            bucket.getNo(),
            bucket.getTitle(),
            bucket.getWriter(),
            bucket.getRegisteredDate(),
            bucket.getCheck());
        count++;

      }
      continue;
    }

    if (count == 0 ) {
      System.out.println("버킷리스트 검색 실패!");
    }
  }
}
