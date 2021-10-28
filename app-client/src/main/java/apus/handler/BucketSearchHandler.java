package apus.handler;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import apus.dao.BucketDao;
import apus.domain.Bucket;
import util.Prompt;

public class BucketSearchHandler implements Command {

  BucketDao bucketDao;
  SqlSession sqlSession;

  public BucketSearchHandler(BucketDao bucketDao, SqlSession sqlSession) {
    this.bucketDao = bucketDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception{

    int count = 0;
    System.out.println();
    System.out.println("[나만의 버킷리스트 검색] 페이지입니다.");
    System.out.println();
    String str = "";

    String input = Prompt.inputString("검색어> ");

    List<Bucket> bucketList = bucketDao.findByKeyword(input);

    for(Bucket bucket : bucketList) {
      if(bucket.getWriter().getId().equals(AuthLoginHandler.getLoginUser().getId())) {
        if(bucket.isComplete()) {
          bucket.setCheck("🗹");
        } else {
          bucket.setCheck("☐");
        }

        if(bucket.getCompletedDate() == null) {
          str = "도전중";
          System.out.printf("번호 : %d\n"
              + "제목 : %s\n"
              + "버킷 도전자 : %s\n"
              + "등록일 : %s\n"
              + "완료일 : %s\n"
              + "완료상태 : %s\n",
              bucket.getNo(),
              bucket.getTitle(),
              bucket.getWriter().getId(),
              bucket.getRegisteredDate(),
              str,
              bucket.getCheck());
          count++;
        } else {
          System.out.printf("번호 : %d\n"
              + "제목 : %s\n"
              + "버킷 도전자 : %s\n"
              + "등록일 : %s\n"
              + "완료일 : %s\n"
              + "완료상태 : %s\n",
              bucket.getNo(),
              bucket.getTitle(),
              bucket.getWriter().getId(),
              bucket.getRegisteredDate(),
              bucket.getCompletedDate(),
              bucket.getCheck());
          count++;
        }
      }
    }


    if (count == 0 ) {
      System.out.println("버킷리스트가 없습니다.");
    }
  }
}
