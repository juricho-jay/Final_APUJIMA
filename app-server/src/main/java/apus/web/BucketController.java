package apus.web;

import java.util.Collection;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import apus.dao.BucketDao;
import apus.dao.MemberDao;
import apus.domain.Bucket;
import apus.domain.Member;

@Controller
public class BucketController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired BucketDao bucketDao;
  @Autowired MemberDao memberDao;


  @GetMapping("/bucket/add")
  public ModelAndView add(Bucket bucket, String id, String title, String content) throws Exception {

    Member member = memberDao.findById(id);

    bucket.setTitle(title);
    bucket.setContent(content);
    bucket.setWriter(member);



    bucketDao.insert(bucket);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }

  @GetMapping("/bucket/list")
  public ModelAndView list() throws Exception {
    Collection<Bucket> bucketList = bucketDao.findAll();

    ModelAndView mv = new ModelAndView();
    mv.addObject("bucketList", bucketList);
    mv.addObject("pageTitle", "버킷리스트");
    mv.setViewName("bucket/BucketList");
    return mv;
  }


  @GetMapping("/bucket/update")
  public ModelAndView update(Bucket bucket, int complete, int no) throws Exception {
    ModelAndView mv = new ModelAndView();

    bucket = bucketDao.findByNo(no);

    if (bucket == null) {
      System.out.println("번호 없음");
      mv.setViewName("redirect:list");
      return mv;
    }

    if (complete == 0) {
      bucketDao.completeUpdate(bucket);
      sqlSessionFactory.openSession().commit();
      mv.setViewName("redirect:list");

    } else if (complete == 1) {
      bucket.setComplete(0);
      bucketDao.cancelCompleteUpdate(bucket);
      sqlSessionFactory.openSession().commit();
      mv.setViewName("redirect:list");
    }

    return mv;
  }

  @GetMapping("/bucket/delete")
  public ModelAndView delete(Bucket bucket, int[] no) throws Exception {
    ModelAndView mv = new ModelAndView();

    if (no == null) {
      mv.setViewName("redirect:list");
      return mv;
    }

    for (int i = 0; i < no.length; i++) {
      bucket = bucketDao.findByNo(no[i]);

      if (bucket == null) {
        throw new Exception("해당 아이디의 회원이 없습니다.");
      }

      bucketDao.delete(no[i]);
      sqlSessionFactory.openSession().commit();
    }

    mv.setViewName("redirect:list");
    return mv;
  }

  @GetMapping("/bucket/contentUpdate")
  public ModelAndView update(Bucket bucket, int no, String title, String content) throws Exception {
    ModelAndView mv = new ModelAndView();

    bucket = bucketDao.findByNo(no);

    if (bucket == null) {
      System.out.println("번호 없음");
      mv.setViewName("redirect:list");
      return mv;
    }

    bucket.setTitle(title);
    bucket.setContent(content);

    bucketDao.update(bucket);
    sqlSessionFactory.openSession().commit();

    mv.setViewName("redirect:list");
    return mv;
  }

}