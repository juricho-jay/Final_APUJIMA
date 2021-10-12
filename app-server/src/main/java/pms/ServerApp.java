package pms;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import pms.table.BucketTable;
import pms.table.CommentTable;
import pms.table.CounselingMemberTable;
import pms.table.DateCheckTable;
import pms.table.DoctorBoardTable;
import pms.table.DoctorReportTable;
import pms.table.FreeBoardTable;
import pms.table.LikeTable;
import pms.table.MailBoxTable;
import pms.table.MedicineTable;
import pms.table.MemberTable;
import pms.table.NoticeBoardTable;
import pms.table.PlantTable;
import pms.table.ReportTable;
import pms.table.RequestTable;
import server.DataProcessor;
import server.RequestProcessor;

public class ServerApp {

  public static void main(String[] args) throws Exception {
    System.out.println("[PMS 서버]");

    System.out.println("서버 실행중");
    ServerSocket serverSocket = new ServerSocket(8888);


    // RequestProcessor 가 사용할 DataProcessor 맵 준비
    HashMap<String,DataProcessor> dataProcessorMap = new HashMap<String,DataProcessor>();

    // => 데이터 처리 담당자를 등록한다.
    dataProcessorMap.put("member.", new MemberTable());
    dataProcessorMap.put("freeBoard.", new FreeBoardTable());
    dataProcessorMap.put("comment.", new CommentTable());
    dataProcessorMap.put("report.", new ReportTable());
    dataProcessorMap.put("like.", new LikeTable());
    dataProcessorMap.put("doctorBoard.", new DoctorBoardTable());
    dataProcessorMap.put("mailBox.", new MailBoxTable());
    dataProcessorMap.put("bucket.", new BucketTable());
    dataProcessorMap.put("plant.", new PlantTable());
    dataProcessorMap.put("medicine.", new MedicineTable());
    dataProcessorMap.put("request.", new RequestTable());
    dataProcessorMap.put("noticeBoard.", new NoticeBoardTable());
    dataProcessorMap.put("doctorReport.", new DoctorReportTable());
    dataProcessorMap.put("dateCheck.", new DateCheckTable());
    dataProcessorMap.put("counselingMember.", new CounselingMemberTable());


    while (true) {
      Socket socket = serverSocket.accept();
      System.out.println("클라이언트가 접속했음");

      RequestProcessor requestProcessor = new RequestProcessor(socket, dataProcessorMap);
      System.out.println("클라이언트 접속 종료!");

      requestProcessor.start();
    }

    //    System.out.println("서버 종료");
    //    serverSocket.close();
  }
}























