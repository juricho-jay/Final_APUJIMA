package pms.handler;

import java.sql.Date;
import java.util.List;
import pms.domain.Bucket;
import util.Prompt;

public class BucketAddHandler extends AbstractBucketListHandler {

  public BucketAddHandler(List<Bucket> bucketList) {
    super(bucketList);
  }


  @Override
  public void execute(CommandRequest request){

    System.out.println();
    System.out.println("[나만의 버킷리스트 작성] 페이지입니다.");
    System.out.println();

    Bucket bucket = new Bucket();


    // bucket.setNo(bucketList.size() + 1);
    bucket.setTitle(Prompt.inputString("제목> "));
    bucket.setContent(Prompt.inputString("내용> "));
    bucket.setWriter(AuthLoginHandler.getLoginUser().getId());
    bucket.setRegisteredDate(new Date(System.currentTimeMillis()));
    bucket.setCheck("☐");

    bucketList.add(bucket);
    System.out.println();
    System.out.println("[나만의 버킷리스트] 가 정상적으로 등록되었습니다.");
    System.out.println();

  }

}
