package apus;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import apus.table.BoardTable;
import apus.table.BucketTable;
import apus.table.CommentTable;
import apus.table.CounselingMemberTable;
import apus.table.DateCheckTable;
import apus.table.LikeTable;
import apus.table.MailBoxTable;
import apus.table.MedicineTable;
import apus.table.MemberTable;
import apus.table.PlantTable;
import apus.table.ReportTable;
import apus.table.RequestTable;
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
    dataProcessorMap.put("board.", new BoardTable());
    dataProcessorMap.put("comment.", new CommentTable());
    dataProcessorMap.put("report.", new ReportTable());
    dataProcessorMap.put("like.", new LikeTable());
    dataProcessorMap.put("mailBox.", new MailBoxTable());
    dataProcessorMap.put("bucket.", new BucketTable());
    dataProcessorMap.put("plant.", new PlantTable());
    dataProcessorMap.put("medicine.", new MedicineTable());
    dataProcessorMap.put("request.", new RequestTable());
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























