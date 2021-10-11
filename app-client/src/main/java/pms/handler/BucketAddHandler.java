package pms.handler;

import java.sql.Date;
import java.util.List;
import pms.domain.Bucket;
import request.RequestAgent;
import util.Prompt;

public class BucketAddHandler implements Command {

  RequestAgent requestAgent;

  public BucketAddHandler(RequestAgent requestAgent ) {
    this.requestAgent = requestAgent;
  }



  @Override
  public void execute(CommandRequest request) throws Exception{
    System.out.println();
    System.out.println("[나만의 버킷리스트 작성] 페이지입니다.");
    System.out.println();

    Bucket bucket = new Bucket();

    requestAgent.request("bucket.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {


      Bucket.lastIndex = 1;
      bucket.setNo(Bucket.lastIndex);

    }

    else {
      List<Bucket> bucketList = (List<Bucket>) requestAgent.getObjects(Bucket.class);
      if (Bucket.lastIndex != bucketList.size()) {
        Bucket.lastIndex = bucketList.get(bucketList.size()-1).getNo();
        bucket.setNo(++Bucket.lastIndex);

      } else {
        bucket.setNo(++Bucket.lastIndex);
      }
    }
    while (true) {
      bucket.setTitle(Prompt.inputString("제목> "));
      if (bucket.getTitle().trim().equals("")) {
        System.out.println("제목이 공백입니다. 다시 입력해주세요.");
      } 
      else {
        break;
      }
    }
    while (true) {
      bucket.setContent(Prompt.inputString("내용> "));
      if (bucket.getContent().trim().equals("")) {
        System.out.println("내용이 공백입니다. 다시 입력해주세요");
      } else {
        break;
      }
    }
    bucket.setWriter(AuthLoginHandler.getLoginUser().getId());
    bucket.setRegisteredDate(new Date(System.currentTimeMillis()));
    bucket.setCheck("☐");

    requestAgent.request("bucket.insert", bucket);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("버킷리스트 등록 실패!");
      return;
    } 

    System.out.println();
    System.out.println("[나만의 버킷리스트] 가 정상적으로 등록되었습니다.");
    System.out.println();
  }


}


