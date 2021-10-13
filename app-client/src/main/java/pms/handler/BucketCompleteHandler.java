package pms.handler;

import pms.dao.BucketDao;
import pms.domain.Bucket;
import util.Prompt;

public class BucketCompleteHandler implements Command {

  BucketDao bucketDao;

  public BucketCompleteHandler(BucketDao bucketDao) {
    this.bucketDao = bucketDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception{

    System.out.println();
    System.out.println("[나만의 버킷리스트 달성체크] 페이지입니다.");
    System.out.println();
    int no = (int)request.getAttribute("no");

    Bucket bucket = bucketDao.findByNo(no);

    if (bucket == null) {
      System.out.println("해당 번호의 버켓리스트가 없습니다.");
      return;
    }

    //    HashMap<String,String> params = new HashMap<>();
    //    params.put("no", String.valueOf(no));
    //
    //    requestAgent.request("bucket.selectOne", params);
    //
    //
    //    Bucket bucket = requestAgent.getObject(Bucket.class);
    //
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println("해당 번호의 버킷리스트가 없습니다.");
    //      return;
    //    }

    if(bucket.getNo() == no) {
      if(bucket.isComplete() == false) {
        bucket.setCheck("🗹");
        bucket.setComplete(true);
        bucketDao.update(bucket);
        //        requestAgent.request("bucket.update", bucket);
        //        if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        //          System.out.println("버킷 리스트 완성 실패!");
        //          return;
        //        }
        System.out.println("해당 버킷리스트를 완성하였습니다. 축하드립니다!");
        return;
      } else {
        String input = Prompt.inputString("완료한 체크목록 입니다. 완료를 취소하시겠습니까? (y/N)");
        if(input.equalsIgnoreCase("y")) {
          bucket.setCheck("☐");
          bucket.setComplete(false);
          bucketDao.update(bucket);
          //          requestAgent.request("bucket.update", bucket);
          //          if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
          //            System.out.println("완료한 버킷리스트 취소 실패!");
          //            return;
          //          }
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
