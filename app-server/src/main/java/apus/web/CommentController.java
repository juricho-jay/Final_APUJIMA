package apus.web;

import java.util.ArrayList;
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
import apus.dao.MailBoxDao;
import apus.dao.MemberDao;
import apus.domain.Board;
import apus.domain.Comment;
import apus.domain.MailBox;
import apus.domain.Member;

@Controller
public class CommentController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired CommentDao commentDao;
  @Autowired MemberDao memberDao;
  @Autowired BoardDao boardDao;
  @Autowired MailBoxDao mailBoxDao;
  @Autowired ServletContext sc;

  @GetMapping("/comment/add")
  public ModelAndView add(String content, String board_no, HttpSession session) {

    Member Commenter = (Member)session.getAttribute("loginUser");

    try {
      Board board = boardDao.findByNo(Integer.parseInt(board_no));

      /* Collection<Comment> commentList = commentDao.findBoardComment(board.getNo()); */

      Comment comment = new Comment();
      comment.setContent(content);
      comment.setCommenter(Commenter);
      comment.setCommentBoard(board);

      commentDao.insert(comment);
      sqlSessionFactory.openSession().commit();
      // 데이터는 잘 넘어감

      ModelAndView mv = new ModelAndView();
      mv.setViewName("redirect:../board/detail?no="+board.getNo());
      // mv.setViewName("board/detail?no="+board.getNo());
      return mv;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }


  @GetMapping("/comment/delete")
  public ModelAndView delete(String no, String board_no) throws Exception {
    Comment comment = commentDao.findByNo(Integer.parseInt(no));

    commentDao.delete(comment.getNo());
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:../board/detail?no="+board_no);
    return mv;
  }



  @GetMapping("/comment/update")
  public ModelAndView update(String no, String board_no, HttpSession session, String content) throws Exception {

    //   Member Commenter = (Member)session.getAttribute("loginUser");


    Comment comment = commentDao.findByNo(Integer.parseInt(no));
    Board board = boardDao.findByNo(Integer.parseInt(board_no));
    comment.setContent(content);
    commentDao.update(comment);
    boardDao.update(board);
    sqlSessionFactory.openSession().commit();


    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:../board/detail?no="+board_no);
    return mv;
  }

  @GetMapping("/comment/searchMyComments")
  public ModelAndView searchMyPosts(HttpSession session) throws Exception {
    ModelAndView mv = new ModelAndView();

    Member member = ((Member) session.getAttribute("loginUser"));
    List<Comment> commentList = commentDao.findById(member.getId());
    List<Comment> aliveCommentList = new ArrayList<>();

    for (int i = 0; i < commentList.size(); i++) {
      Board board = boardDao.findByActiveNo(commentList.get(i).getCommentBoard().getNo());
      if (board != null) {
        aliveCommentList.add(commentList.get(i));
      }
    }

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

    mv.addObject("commentList", aliveCommentList);
    mv.addObject("pageTitle", "나의 댓글");
    mv.addObject("contentUrl", "board/BoardSearchMyComment.jsp");
    mv.setViewName("darkTemplate");
    return mv;
  }


}
