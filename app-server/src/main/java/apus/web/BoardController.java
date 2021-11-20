package apus.web;

import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import apus.dao.BoardDao;
import apus.dao.CommentDao;
import apus.dao.LikeDao;
import apus.dao.MailBoxDao;
import apus.dao.MemberDao;
import apus.dao.ReportDao;
import apus.domain.Board;
import apus.domain.Comment;
import apus.domain.Like;
import apus.domain.MailBox;
import apus.domain.Member;
import apus.domain.Report;

@Controller
public class BoardController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired BoardDao boardDao;
  @Autowired MemberDao memberDao;
  @Autowired CommentDao commentDao;
  @Autowired LikeDao likeDao;
  @Autowired MailBoxDao mailBoxDao;
  @Autowired ReportDao reportDao;

  @GetMapping("/board/form")
  public ModelAndView form() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "새 글");
    mv.addObject("contentUrl", "board/BoardForm.jsp");
    // mv.setViewName("template1");
    mv.setViewName("board/BoardForm");
    return mv;
  }

  @PostMapping("/board/add")
  public ModelAndView add(Board board, HttpSession session ,String whichBoard) throws Exception {

    board.setWriter((Member) session.getAttribute("loginUser"));



    // String writer => 로그인 유저의 닉네임

    //    board.setActive(1);
    if(board.getWhichBoard() == 1) {
      boardDao.insert(board);
    }else if ((board.getWhichBoard() == 2)){
      boardDao.insert2(board);
    }else {
      boardDao.insert3(board);
    }
    sqlSessionFactory.openSession().commit();
    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }

  @GetMapping("/board/list")
  public ModelAndView list(HttpSession session) throws Exception {
    Collection<Board> boardList = boardDao.findAll();

    ModelAndView mv = new ModelAndView();

    Member member = ((Member) session.getAttribute("loginUser"));

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

    mv.addObject("boardList", boardList);
    mv.addObject("pageTitle", "게시글목록");
    mv.addObject("contentUrl", "board/BoardList.jsp");
    //mv.setViewName("board/BoardList");
    mv.setViewName("template3");
    // mv.setViewName("template1");
    return mv;
  }

  @GetMapping("/board/detail")
  public ModelAndView detail(int no, HttpSession session) throws Exception {
    ModelAndView mv = new ModelAndView();
    Board board = boardDao.findByNo(no);

    if (board == null) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    }

    Member member = ((Member) session.getAttribute("loginUser"));

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

    Collection<Comment> commentList = commentDao.findBoardComment(board.getNo());
    Collection<Like> likeList = likeDao.findBoardCount(board.getNo());

    //해당 게시물의 좋아요/댓글 갯수
    int likeNo = likeList.size();
    int commentNo = commentList.size();

    boardDao.updateCount(no);
    sqlSessionFactory.openSession().commit();

    mv.addObject("pageTitle", "게시글");
    mv.addObject("board", board);
    mv.addObject("commentList", commentList);
    mv.addObject("likeList", likeList);
    mv.addObject("likeNo", likeNo);
    mv.addObject("commentNo", commentNo);
    mv.addObject("contentUrl", "board/BoardDetail.jsp");
    mv.setViewName("template3");
    return mv;
  }

  @GetMapping("/board/updateForm")
  public ModelAndView updateForm(String no, HttpSession session) throws Exception {
    System.out.println("----------helo updateForm-----------");

    Board board = boardDao.findByNo(Integer.parseInt(no));
    if (board == null) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    } 

    ModelAndView mv = new ModelAndView();

    Member member = ((Member) session.getAttribute("loginUser"));

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

    mv.addObject("board", board);
    mv.addObject("contentUrl", "board/UpdateForm.jsp");
    mv.setViewName("template3");
    return mv;
  }

  @PostMapping("/board/update")
  public ModelAndView update(Board board, String title, String content, HttpSession session) throws Exception {
    System.out.println("----------helo update-----------");

    if (board == null) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    } 
    board.setTitle(title);
    board.setContent(content);

    boardDao.update(board);
    sqlSessionFactory.openSession().commit();


    ModelAndView mv = new ModelAndView();

    Member member = ((Member) session.getAttribute("loginUser"));

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

    mv.addObject("board", board);
    mv.addObject("contentUrl", "board/BoardUpdate.jsp");
    mv.setViewName("template3");
    return mv;
  }

  @GetMapping("/board/delete")
  public ModelAndView delete(int no) throws Exception {

    Board board = boardDao.findByNo(no);
    if (board == null) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    }

    boardDao.delete(no);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }

  @GetMapping("/board/report")
  public ModelAndView report(String no,HttpSession session) throws Exception {
    Member requester = ((Member) session.getAttribute("loginUser"));
    ModelAndView mv = new ModelAndView();



    int no1 = Integer.parseInt(no);
    Board board = boardDao.findByNo(no1);
    if(requester == null) {
      throw new Exception("로그인 되어 있지 않습니다.");
    }
    if (board == null) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    }

    Report report = reportDao.findByReport(no1, requester.getId());

    if(report != null) {
      report = null;
      System.out.println("신고 한 적이 있기에 이게 실행됨");
      mv.addObject("report",report);
    } 
    mv.addObject("report",report);
    mv.addObject("contentUrl","/board/BoardReport.jsp");
    mv.setViewName("/board/BoardReport");
    return mv;
  }

  @GetMapping("/board/reportAdd")
  public ModelAndView reportAdd(int no,HttpSession session,String reportId,String reason,String reason2) throws Exception {

    ModelAndView mv = new ModelAndView();
    Board requestBoard = boardDao.findByNo(no);

    if (requestBoard == null) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    }

    Member requester = memberDao.findById(reportId);

    Report report = reportDao.findByReport(no, reportId);

    if (report == null) {
      Report adminReport = new Report();

      if(reason2.length() == 0) {
        adminReport.setRequester(requester);
        adminReport.setRequestBoard(requestBoard);
        adminReport.setReason(reason);
        adminReport.setCheck(0);
        reportDao.insert(adminReport);
        sqlSessionFactory.openSession().commit();
        mv.addObject("report",adminReport);
      }
      else {
        adminReport.setRequester(requester);
        adminReport.setRequestBoard(requestBoard);
        adminReport.setReason(reason2);
        adminReport.setCheck(0);
        reportDao.insert(adminReport);
        sqlSessionFactory.openSession().commit();
        mv.addObject("report",adminReport);
      }
    }

    mv.addObject("report",report);
    mv.addObject("contentUrl","/board/BoardReport.jsp");
    mv.setViewName("/board/BoardReport");
    return mv;
  }
  @GetMapping("/board/freeBoardList")
  public ModelAndView freeBoardList(HttpSession session) throws Exception {
    Collection<Board> boardList = boardDao.findFreeBoard();

    ModelAndView mv = new ModelAndView();

    Member member = ((Member) session.getAttribute("loginUser"));

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

    mv.addObject("boardList", boardList);
    mv.addObject("pageTitle", "자유게시판목록");
    mv.addObject("contentUrl", "board/FreeBoardList.jsp");
    //mv.setViewName("board/BoardList");
    mv.setViewName("template3");
    // mv.setViewName("template1");
    return mv;
  }

  @GetMapping("/board/doctorBoardList")
  public ModelAndView doctorBoardList(HttpSession session) throws Exception {
    Collection<Board> boardList = boardDao.findDoctorBoard();

    ModelAndView mv = new ModelAndView();

    Member member = ((Member) session.getAttribute("loginUser"));

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

    mv.addObject("boardList", boardList);
    mv.addObject("pageTitle", "자유게시판목록");
    mv.addObject("contentUrl", "board/DoctorBoardList.jsp");
    //mv.setViewName("board/BoardList");
    mv.setViewName("template3");
    // mv.setViewName("template1");
    return mv;
  }

  @GetMapping("/board/noticeBoardList")
  public ModelAndView noticeBoardList(HttpSession session) throws Exception {
    Collection<Board> boardList = boardDao.findNoticeBoard();

    ModelAndView mv = new ModelAndView();

    Member member = ((Member) session.getAttribute("loginUser"));

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

    mv.addObject("boardList", boardList);
    mv.addObject("pageTitle", "자유게시판목록");
    mv.addObject("contentUrl", "board/NoticeBoardList.jsp");
    //mv.setViewName("board/BoardList");
    mv.setViewName("template3");
    // mv.setViewName("template1");
    return mv;
  }


}