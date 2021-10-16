package pms.handler;

import java.util.Collection;
import pms.dao.BucketDao;
import pms.domain.Bucket;
import util.Prompt;

public class BucketListHandler  implements Command {

  BucketDao bucketDao;

  public BucketListHandler(BucketDao bucketDao) {
    this.bucketDao = bucketDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception{

    System.out.println();
    System.out.println("[나만의 버킷리스트 목록] 페이지입니다.");
    System.out.println();
    int count = 0;
    int bucketNo = 0;

    Collection<Bucket> bucketList = bucketDao.findAll();

    if (bucketList == null) {
      System.out.println("현재 작성된 버킷 리스트가 없습니다.");
      return;
    } else {

      for(Bucket bucket : bucketList) {
        if(bucket.getWriter().equals(AuthLoginHandler.getLoginUser().getId())) {
          bucketNo++;
          bucket.setNo(bucketNo);
          System.out.printf("%s, %d, %s ,%s \n",bucket.getCheck(),
              bucket.getNo(),
              bucket.getTitle(),
              bucket.getContent());

          count++;
        }

      }

      if (count == 0 ){
        System.out.println("버킷리스트 목록 중 현재 본인이 작성한 버킷리스트가 없습니다.");
        return;
      }
      //    }

      String answer = Prompt.inputString("달성한 버켓리스트가 있으신가요?(y/N) 검색하기(#)> ");

      if (answer.equalsIgnoreCase("y")) {
        while(true) {
          int no = Prompt.inputInt("달성한 버킷리스트 번호를 입력해주세요> ");
          String input = Prompt.inputString("달성체크(C), 이전(0)> ");
          request.setAttribute("no", no);
          switch(input) {
            case "C":
            case "c": 
              request.getRequestDispatcher("/bucket/complete").forward(request);
              return;
            case "0":
              return;
            default:
              System.out.println("명령어가 올바르지 않습니다!");
          }
        }

      } else if(answer.equalsIgnoreCase("n") || (answer.length() == 0)) {
        System.out.println("당신의 버킷리스트를 응원합니다!");
        return;
      } else if(answer.equals("#")) {
        request.getRequestDispatcher("/bucket/search").forward(request);
        return;
      }
      else {
        System.out.println("명령어가 잘못 입력되었습니다.");
        return;
      }


    }
  }
}
