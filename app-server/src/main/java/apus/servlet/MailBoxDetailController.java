package apus.servlet;

import java.io.IOException;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import apus.dao.MailBoxDao;
import apus.domain.MailBox;

@WebServlet("/mailbox/detail")
public class MailBoxDetailController extends GenericServlet {

  private static final long serialVersionUID = 1L;
  MailBoxDao mailboxDao;
  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    mailboxDao = (MailBoxDao) 웹애플리케이션공용저장소.getAttribute("mailboxDao");
  }


  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      MailBox mailbox = mailboxDao.findByNo(no);

      if (mailbox == null) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      }

      request.setAttribute("mailbox", mailbox);
      request.getRequestDispatcher("/mailbox/MailBoxDetail.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
//public class MailBoxDetailHandler  implements Command{
//  MailBoxDao mailBoxDao;
//  SqlSession sqlSession;
//
//  public MailBoxDetailHandler(MailBoxDao mailBoxDao, SqlSession sqlSession) {
//    this.mailBoxDao = mailBoxDao;
//    this.sqlSession = sqlSession;
//  }
//
//  @Override
//  public void execute(CommandRequest request) throws Exception {
//    System.out.println();
//    System.out.println("[상세보기] 페이지입니다.");
//    System.out.println();
//
//    List<MailBox> mailBoxList=  mailBoxDao.findAll();
//
//    int count =0;
//    if (mailBoxList == null) {
//      System.out.println("쪽지함이 비어 상세보기를 할 수 없습니다.");
//      return;
//    } else {
//
//
//      for (MailBox mailBox : mailBoxList) {
//        if(mailBox.getReceiver().getId().equals(AuthLoginHandler.getLoginUser().getId())) {
//
//          System.out.printf("%d."
//              + "보낸 사람 : %s\n"
//              +"쪽지 제목 : %s\n",
//              mailBox.getNo(),
//              mailBox.getSender().getId(),
//              mailBox.getTitle()); 
//          count++;
//          System.out.println();
//        }
//      }
//      if (count == 0 ){
//        System.out.println("받은 쪽지가 없습니다.");
//        return;
//      }
//
//      int no = Prompt.inputInt("쪽지 번호> ");
//      int count2 = 0;
//
//      for (MailBox mailBox : mailBoxList) {
//        if(mailBox.getReceiver().getId().equals(AuthLoginHandler.getLoginUser().getId()) && mailBox.getNo() == no) {
//
//          System.out.printf("제목 : %s\n", mailBox.getTitle());
//          System.out.printf("내용 : %s\n", mailBox.getContent());
//          System.out.printf("보낸이 : %s\n", mailBox.getSender().getId()); // 우리는 익명이기 때문에 Id로
//          System.out.printf("수신자 : %s\n", AuthLoginHandler.getLoginUser().getId());
//          System.out.printf("보낸 날짜 : %s\n",mailBox.getSentTime());
//          mailBoxDao.update(mailBox);
//          sqlSession.commit();
//          count2++;
//        }
//      }
//      List<MailBox> mailBoxList2=  mailBoxDao.findAll();
//      for (MailBox mailBox : mailBoxList2) {
//        if(mailBox.getReceiver().getId().equals(AuthLoginHandler.getLoginUser().getId()) && mailBox.getNo() == no) {
//          System.out.printf("확인 날짜 : %s\n",mailBox.getReceivedTime());
//          System.out.println();
//        }
//      }
//      if (count2 == 0) {
//        System.out.println("해당 번호의 쪽지가 존재하지 않습니다.");
//        return;
//      }
//    } 
//
//  }
//}
