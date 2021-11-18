package apus.web;

import java.util.Collection;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import apus.dao.BoardDao;
import apus.dao.MemberDao;
import apus.domain.Board;
import apus.domain.Member;

@Controller
public class BoardController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired BoardDao boardDao;
  @Autowired MemberDao memberDao;

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
  public ModelAndView add(Board board, HttpSession session) throws Exception {

    board.setWriter((Member) session.getAttribute("loginUser"));

    System.out.println("-------------hello----------------");
    System.out.println("작성자 => " + board.getWriter().getNickname());
    System.out.println("----------------------------");
    System.out.println("board의 타입 =>" + board.getWhichBoard());
    System.out.println("----------------------------");

    // String writer => 로그인 유저의 닉네임

    board.setActive(1);



    boardDao.insert(board);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }

  @GetMapping("/board/list")
  public ModelAndView list() throws Exception {
    Collection<Board> boardList = boardDao.findAll();

    ModelAndView mv = new ModelAndView();
    mv.addObject("boardList", boardList);
    mv.addObject("pageTitle", "게시글목록");
    mv.addObject("contentUrl", "board/BoardList.jsp");
    //mv.setViewName("board/BoardList");
    mv.setViewName("template3");
    // mv.setViewName("template1");
    return mv;
  }

  @GetMapping("/board/detail")
  public ModelAndView detail(int no) throws Exception {
    Board board = boardDao.findByNo(no);

    if (board == null) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    }

    boardDao.updateCount(no);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.addObject("board", board);
    mv.addObject("pageTitle", "게시글");
    mv.addObject("contentUrl", "board/BoardDetail.jsp");
    mv.setViewName("template3");
    return mv;
  }

  @PostMapping("/board/updateForm")
  public ModelAndView updateForm(String no) throws Exception {
    System.out.println("----------helo updateForm-----------");

    Board board = boardDao.findByNo(Integer.parseInt(no));
    if (board == null) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    } 

    ModelAndView mv = new ModelAndView();
    mv.addObject("board", board);
    mv.addObject("contentUrl", "board/UpdateForm.jsp");
    mv.setViewName("template3");
    return mv;
  }

  @PostMapping("/board/update")
  public ModelAndView update(Board board, String title, String content) throws Exception {
    System.out.println("----------helo update-----------");

    if (board == null) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    } 
    board.setTitle(title);
    board.setContent(content);

    boardDao.update(board);
    sqlSessionFactory.openSession().commit();


    ModelAndView mv = new ModelAndView();
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

  @GetMapping("/board/freeBoardList")
  public ModelAndView freeBoardList() throws Exception {
    Collection<Board> boardList = boardDao.findFreeBoard();

    ModelAndView mv = new ModelAndView();
    mv.addObject("boardList", boardList);
    mv.addObject("pageTitle", "자유게시판목록");
    mv.addObject("contentUrl", "board/FreeBoardList.jsp");
    //mv.setViewName("board/BoardList");
    mv.setViewName("template3");
    // mv.setViewName("template1");
    return mv;
  }

  @GetMapping("/board/doctorBoardList")
  public ModelAndView doctorBoardList() throws Exception {
    Collection<Board> boardList = boardDao.findDoctorBoard();

    ModelAndView mv = new ModelAndView();
    mv.addObject("boardList", boardList);
    mv.addObject("pageTitle", "자유게시판목록");
    mv.addObject("contentUrl", "board/DoctorBoardList.jsp");
    //mv.setViewName("board/BoardList");
    mv.setViewName("template3");
    // mv.setViewName("template1");
    return mv;
  }

  @GetMapping("/board/noticeBoardList")
  public ModelAndView noticeBoardList() throws Exception {
    Collection<Board> boardList = boardDao.findNoticeBoard();

    ModelAndView mv = new ModelAndView();
    mv.addObject("boardList", boardList);
    mv.addObject("pageTitle", "자유게시판목록");
    mv.addObject("contentUrl", "board/NoticeBoardList.jsp");
    //mv.setViewName("board/BoardList");
    mv.setViewName("template3");
    // mv.setViewName("template1");
    return mv;
  }


}