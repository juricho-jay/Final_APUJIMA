package apus.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import apus.dao.MailBoxDao;
import apus.domain.MailBox;
@WebServlet("/mailbox/delete")
public class MailBoxDeleteController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  MailBoxDao mailboxDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    mailboxDao = (MailBoxDao) 웹애플리케이션공용저장소.getAttribute("mailboxDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      MailBox mailBox = mailboxDao.findByNo(no);
      if (mailBox == null) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      }

      mailboxDao.delete(no);
      sqlSession.commit();

      response.sendRedirect("list");

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
//public class MailBoxDeleteHandler implements Command{
//  MailBoxDao mailBoxDao;
//  SqlSession sqlSession;
//
//  public MailBoxDeleteHandler(MailBoxDao mailBoxDao, SqlSession sqlSession) {
//    this.mailBoxDao = mailBoxDao;
//    this.sqlSession = sqlSession;
//  }
//  @Override
//  public void execute(CommandRequest request) throws Exception {
//    System.out.println();
//    System.out.println("[쪽지 삭제] 페이지입니다.");
//    System.out.println();
//    while(true) {
//      int count = 0;
//
//      List<MailBox> mailBoxList = mailBoxDao.findAll();
//
//      if (mailBoxList == null) {
//        System.out.println("쪽지함이 비어 있습니다.");
//        return;
//      }
//
//      for (MailBox mailBox : mailBoxList) {
//        if(mailBox.getReceiver().getId().equals(AuthLoginHandler.getLoginUser().getId())) {
//          System.out.printf("쪽지 번호 : %d\n"
//              +"보낸 사람 : %s\n"
//              +"쪽지 제목 : %s\n",
//              mailBox.getNo(),
//              mailBox.getSender().getId(),
//              mailBox.getTitle()); 
//          System.out.println();
//          count++;
//        }
//      }
//
//      if (count == 0){
//        System.out.println("받은 쪽지가 없습니다.");
//        return;
//      }
//
//      int no = Prompt.inputInt("쪽지 번호 > ");
//
//      String input = Prompt.inputString("❗ 정말 삭제하시겠습니까? (y/N)> ");
//      if(input.equalsIgnoreCase("n") || input.length() == 0) {
//        System.out.println("쪽지 삭제를 취소하였습니다.");
//        return;
//      }
//
//      mailBoxDao.delete(no);
//      sqlSession.commit();
//
//      //      for (MailBox mailBox : mailBoxList) {
//      //        if(mailBox.getReceiver().getId().equals(AuthLoginHandler.getLoginUser().getId()) &&
//      //            mailBox.getNo() == no) {
//      //          mailBoxDao.delete(no);
//      //        }
//      //      }
//
//      System.out.println("선택한 쪽지를 삭제하였습니다.");
//      return;
//    } 
//  }
//}




