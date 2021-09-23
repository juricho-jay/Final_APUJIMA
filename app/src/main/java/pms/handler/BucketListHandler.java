package pms.handler;

import java.util.List;
import pms.domain.Bucket;

public class BucketListHandler extends AbstractBucketListHandler {

  public BucketListHandler(List<Bucket> bucketList) {
    super(bucketList);
  }


  @Override
  public void execute(CommandRequest request){

    System.out.println();
    System.out.println("[나만의 버킷리스트 목록] 페이지입니다.");
    System.out.println();
    int count = 0;

    if(bucketList.size() == 0) {
      System.out.println("현재 작성된 버킷리스트가 없습니다.");
      return;
    } else {
      for(int i = 0; i < bucketList.size(); i++) {
        if(bucketList.get(i).getWriter().equals(AuthLoginHandler.getLoginUser().getId())) {
          System.out.printf("%d, %s, %s, %s\n", bucketList.get(i).getNo(),
              bucketList.get(i).getTitle(),
              bucketList.get(i).getContent(), 
              bucketList.get(i).getCheck());

          count++;
        }

      }
      if (count == 0 ){
        System.out.println("현재 작성된 버킷리스트가 없습니다.");
      }
    }
  }
}
