package pms.handler;

import java.util.List;
import pms.domain.Bucket;
import util.Prompt;

public class BucketSearchHandler extends AbstractBucketListHandler {

  public BucketSearchHandler(List<Bucket> bucketList) {
    super(bucketList);
  }


  @Override
  public void execute(CommandRequest request){

    System.out.println();
    System.out.println("[나만의 버킷리스트 검색] 페이지입니다.");
    System.out.println();

    String input = Prompt.inputString("검색어> ");

    for (Bucket bucket : bucketList) {
      if (!bucket.getTitle().contains(input) &&
          !bucket.getContent().contains(input) &&
          !bucket.getWriter().contains(input)) {
        continue;
      }
      System.out.printf("%d, %s, %s, %s, %s\n",
          bucket.getNo(),
          bucket.getTitle(),
          bucket.getWriter(),
          bucket.getRegisteredDate(),
          bucket.getCheck());
    }
  }
}
