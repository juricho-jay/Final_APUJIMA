package pms.listener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import pms.context.ApplicationContextListener;
import pms.domain.Bucket;
import pms.domain.CounselingMember;
import pms.domain.DoctorBoard;
import pms.domain.FreeBoard;
import pms.domain.MailBox;
import pms.domain.Medicine;
import pms.domain.Member;
import pms.domain.NoticeBoard;

public class FileListener implements ApplicationContextListener {

  @SuppressWarnings("unchecked")
  @Override
  public void contextInitialized(Map<String, Object> params) {

    List<Member> memberList = (List<Member>) params.get("memberList");
    List<CounselingMember> counselingMemberList = (List<CounselingMember>) params.get("counselingMemberList");
    List<NoticeBoard> noticeBoardList = (List<NoticeBoard>) params.get("noticeBoardList");
    List<FreeBoard> freeBoardList = (List<FreeBoard>) params.get("freeBoardList");
    List<DoctorBoard> doctorBoardList = (List<DoctorBoard>) params.get("doctorBoardList");
    List<Medicine> medicineList = (List<Medicine>) params.get("medicineList");
    List<MailBox> mailBoxList = (List<MailBox>) params.get("mailBoxList");
    List<Bucket> bucketList = (List<Bucket>) params.get("bucketList");
    List<Date> dateList = (List<Date>) params.get("dateList");
    List<Member> memberCheckList = (List<Member>) params.get("memberCheckList");
    List<FreeBoard> reportList = (List<FreeBoard>) params.get("reportList");
    List<DoctorBoard> doctorReportList = (List<DoctorBoard>) params.get("doctorReportList");

    loadObjects("member.json", memberList, Member.class);
    loadObjects("counselingMember.json", counselingMemberList,CounselingMember.class);
    loadObjects("noticeBoard.json", noticeBoardList, NoticeBoard.class);
    loadObjects("freeBoard.json", freeBoardList, FreeBoard.class);
    loadObjects("doctorBoard.json", doctorBoardList, DoctorBoard.class);
    loadObjects("medicine.json", medicineList, Medicine.class);
    loadObjects("mailBox.json", mailBoxList, MailBox.class);
    loadObjects("bucketlist.json",bucketList, Bucket.class);
    loadObjects("dateList.json",dateList, Date.class);
    loadObjects("memberCheckList.json",memberCheckList, Member.class);
    loadObjects("report.json", reportList, FreeBoard.class);
    loadObjects("doctorReport.json", doctorReportList, DoctorBoard.class);


  }

  @SuppressWarnings("unchecked")
  @Override
  public void contextDestroyed(Map<String, Object> params) {

    List<Member> memberList = (List<Member>) params.get("memberList");
    List<CounselingMember> counselingMemberList = (List<CounselingMember>) params.get("counselingMemberList");
    List<NoticeBoard> noticeBoardList = (List<NoticeBoard>) params.get("noticeBoardList");
    List<FreeBoard> freeBoardList = (List<FreeBoard>) params.get("freeBoardList");
    List<DoctorBoard> doctorBoardList = (List<DoctorBoard>) params.get("doctorBoardList");
    List<Medicine> medicineList = (List<Medicine>) params.get("medicineList");
    List<MailBox> mailBoxList = (List<MailBox>) params.get("mailBoxList");
    List<Bucket> bucketList = (List<Bucket>) params.get("bucketList");
    List<Date> dateList = (List<Date>) params.get("dateList");
    List<Member> memberCheckList = (List<Member>) params.get("memberCheckList");
    List<FreeBoard> reportList = (List<FreeBoard>) params.get("reportList");
    List<DoctorBoard> doctorReportList = (List<DoctorBoard>) params.get("doctorReportList");


    saveObjects("member.json", memberList);
    saveObjects("counselingMember.json",counselingMemberList);
    saveObjects("noticeBoard.json", noticeBoardList);
    saveObjects("freeBoard.json", freeBoardList);
    saveObjects("doctorBoard.json", doctorBoardList);
    saveObjects("medicine.json", medicineList);
    saveObjects("mailBox.json", mailBoxList);
    saveObjects("bucketList.json",bucketList);
    saveObjects("dateList.json",dateList);
    saveObjects("memberCheckList.json",memberCheckList);
    saveObjects("report.json", reportList);
    saveObjects("doctorReport.json", doctorReportList);

  }

  private <E> void loadObjects(
      String filepath,
      List<E> list,
      Class<E> domainType
      ) {
    try (BufferedReader in = new BufferedReader(
        new FileReader(filepath))){

      StringBuilder strBuilder = new StringBuilder();
      String str;
      while((str = in.readLine()) != null) { 
        strBuilder.append(str);
      }

      Type type = TypeToken.getParameterized(Collection.class, domainType).getType();
      Collection<E> collection =  new Gson().fromJson(strBuilder.toString(), type);

      list.addAll(collection);

      System.out.printf("%s 데이터 로딩 완료!\n", filepath);
    } catch (Exception e) {
      System.out.printf("%s 데이터 로딩 오류!\n", filepath);
    }

  }


  private void saveObjects(String filepath, List<?> list) {
    try (PrintWriter out = new PrintWriter(
        new BufferedWriter(
            new FileWriter(filepath)))){

      out.println(new Gson().toJson(list));
      System.out.printf("%s 데이터 출력 완료!\n",filepath);

    } catch (Exception e) {
      System.out.printf("%s 데이터 출력 오류!\n",filepath);
    }
  }

}