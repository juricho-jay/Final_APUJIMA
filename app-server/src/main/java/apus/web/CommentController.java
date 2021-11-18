package apus.web;

import java.util.Collection;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import apus.dao.BoardDao;
import apus.dao.CommentDao;
import apus.dao.MemberDao;
import apus.domain.Board;
import apus.domain.Comment;
import apus.domain.Member;

@Controller
public class CommentController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired CommentDao commentDao;
  @Autowired MemberDao memberDao;
  @Autowired BoardDao boardDao;
  @Autowired ServletContext sc;

  @GetMapping("/comment/add")
  public ModelAndView add(String content, String commenter, String no, HttpSession session) {
    System.out.println("-------------comment Add----------------");

    Member Commenter = (Member)session.getAttribute("loginUser");

    try {
      Board board = boardDao.findByNo(Integer.parseInt(no));
      System.out.println("------------------------------------");

      System.out.println("넘어간 보드의 숫자 => " + board.getNo());

      Collection<Comment> commentList = commentDao.findBoardComment(board.getNo());

      System.out.println("------------------------------------");
      System.out.println("commentList size => " + commentList.size());
      System.out.println("------------------------------------");
      Comment comment = new Comment();
      comment.setContent(content);
      comment.setCommenter(Commenter);
      comment.setCommentBoard(board);

      commentDao.insert(comment);
      sqlSessionFactory.openSession().commit();
      // 데이터는 잘 넘어감

      ModelAndView mv = new ModelAndView();
      mv.addObject("pageTitle", "댓글추가");
      mv.addObject("commentList", commentList);
      mv.addObject("comment", comment);
      mv.addObject("contentUrl", "board/detail?no=" + board.getNo());
      mv.setViewName("board/detail?no="+board.getNo());
      // mv.setViewName("board/detail?no="+board.getNo());
      return mv;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }


  @GetMapping("/comment/delete")
  public ModelAndView delete(String no) throws Exception {
    Comment comment = commentDao.findByNo(Integer.parseInt(no));

    if (comment == null) {
      throw new Exception("해당 번호의 댓글이 없습니다.");
    }

    commentDao.delete(comment.getNo());
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }



}
