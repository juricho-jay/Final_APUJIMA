package apus.web;

import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import apus.dao.BoardDao;
import apus.dao.CommentDao;
import apus.dao.LikeDao;
import apus.dao.MailBoxDao;
import apus.dao.MemberDao;
import apus.domain.Board;
import apus.domain.Like;
import apus.domain.MailBox;
import apus.domain.Member;

@Controller
public class LikeController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired CommentDao commentDao;
  @Autowired MemberDao memberDao;
  @Autowired BoardDao boardDao;
  @Autowired MailBoxDao mailBoxDao;
  @Autowired LikeDao likeDao;
  @Autowired ServletContext sc;

  @GetMapping("/like/update")
  public ModelAndView add(int no, HttpSession session) throws Exception {
    ModelAndView mv = new ModelAndView();
    Member member = (Member)session.getAttribute("loginUser");
    Board board = boardDao.findByNo(no);

    // 라이크 누른적 있나 체크
    Like likeCheck = likeDao.findBoardLike(no, member.getNo());

    //안읽은 메일 체크
    if (member != null) {
      List<MailBox> mailBoxList = mailBoxDao.findAll();

      int count = 0;
      for (int i = 0; i < mailBoxList.size(); i++) {
        if (member.getNickname().equals(mailBoxList.get(i).getReceiver().getNickname())) {
          if (mailBoxList.get(i).getReceivedTime() == null) {
            count++;
          }
        }
      }
      mv.addObject("uncheckedMail", count);
    } 

    // 누른 적 없음 - 리스트에 새로 추가
    if (likeCheck == null) {

      Like like = new Like();
      like.setLikeBoard(board);
      like.setLiker(member);
      //likeOrNot은 insert시 자동으로 1로 부여


      likeDao.insert(like);
      sqlSessionFactory.openSession().commit();
      mv.setViewName("redirect:../board/detail?no=" + no);
      return mv;

      // 누른 적 있음 - 리스트에서 삭제
    } else if (likeCheck != null) {

      likeDao.delete(no, member.getNo());
      sqlSessionFactory.openSession().commit();
      mv.setViewName("redirect:../board/detail?no=" + no);
    }
    return mv;

  }
}
