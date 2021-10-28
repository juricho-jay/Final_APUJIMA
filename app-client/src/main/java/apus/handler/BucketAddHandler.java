package apus.handler;

import java.sql.Date;
import org.apache.ibatis.session.SqlSession;
import apus.dao.BucketDao;
import apus.domain.Bucket;
import util.Prompt;

public class BucketAddHandler implements Command {

  BucketDao bucketDao;
  SqlSession sqlSession;

  public BucketAddHandler(BucketDao bucketDao, SqlSession sqlSession) {
    this.bucketDao = bucketDao;
    this.sqlSession = sqlSession;
  }



  @Override
  public void execute(CommandRequest request) throws Exception{
    System.out.println();
    System.out.println("[나만의 버킷리스트 작성] 페이지입니다.");
    System.out.println();

    Bucket bucket = new Bucket();


    while (true) {
      bucket.setTitle(Prompt.inputString("제목> "));
      if (bucket.getTitle().trim().equals("")) {
        System.out.println("제목이 공백입니다. 다시 입력해주세요.");
        continue;
      } 
      break;
    }
    while (true) {
      bucket.setContent(Prompt.inputString("내용> "));
      if (bucket.getContent().trim().equals("")) {
        System.out.println("내용이 공백입니다. 다시 입력해주세요.");
        continue;
      } 
      break;
    }
    bucket.setWriter(AuthLoginHandler.getLoginUser());
    bucket.setRegisteredDate(new Date(System.currentTimeMillis()));

    bucketDao.insert(bucket);
    sqlSession.commit();

    System.out.println();
    System.out.println("[나만의 버킷리스트] 가 정상적으로 등록되었습니다.");
    System.out.println();
  }


}


