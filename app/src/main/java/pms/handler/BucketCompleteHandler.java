package pms.handler;

import java.util.List;
import pms.domain.Bucket;
import util.Prompt;

public class BucketCompleteHandler extends AbstractBucketListHandler {

  public BucketCompleteHandler(List<Bucket> bucketList) {
    super(bucketList);
  }


  @Override
  public void execute(CommandRequest request){

    System.out.println();
    System.out.println("[나만의 버킷리스트 달성체크] 페이지입니다.");
    System.out.println();


    int no = (int)request.getAttribute("no");

    Bucket bucket = findByNo(no);

    if (bucketList == null) {
      System.out.println("해당 번호의 버킷리스트가 없습니다.");
      return;
    }

    if(bucket.getNo() == no) {
      if(bucket.isComplete() == false) {
        bucket.setCheck("🗹");
        bucket.setComplete(true);
        System.out.println("해당 버킷리스트를 완성하였습니다. 축하드립니다!");
        return;
      } else {
        String input = Prompt.inputString("완료한 체크목록 입니다. 완료를 취소하시겠습니까? (y/N)");
        if(input.equalsIgnoreCase("y")) {
          bucket.setCheck("☐");
          bucket.setComplete(false);
          System.out.println("해당 버킷리스트를 취소하였습니다. 성공하신 후 성공등록을 해주세요.");
          return;
        } else if(input.equalsIgnoreCase("n")) {
          System.out.println("해당 버킷리스트는 완료상태로 남겨두었습니다.");
          return;
        }
      }
    }

  }

}
