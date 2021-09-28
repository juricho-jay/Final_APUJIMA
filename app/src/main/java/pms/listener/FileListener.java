package pms.listener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import pms.context.ApplicationContextListener;
import pms.domain.Bucket;
import pms.domain.CounselingMember;
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
<<<<<<< HEAD
    List<CounselingMember> counselingMemberList = new LinkedList<>();
    List<Medicine> medicineList = new LinkedList<>();
    List<FreeBoard> freeBoardList = new LinkedList<>();
    List<NoticeBoard> noticeBoardList = new LinkedList<>();
    List<FreeBoard> reportList = new LinkedList<>();
    List<MailBox> mailBoxList = new LinkedList<>();
    List<Bucket> bucketList = new LinkedList<>();
=======
    List<CounselingMember> counselingMemberList = (List<CounselingMember>) params.get("counselingMemberList");
    List<Medicine> medicineList = (List<Medicine>) params.get("medicineList");
    List<FreeBoard> freeBoardList = (List<FreeBoard>) params.get("freeBoardList");
    List<NoticeBoard> noticeBoardList = (List<NoticeBoard>) params.get("noticeBoardList");
    List<FreeBoard> reportList = (List<FreeBoard>) params.get("reportList");
    List<MailBox> mailBoxList = (List<MailBox>) params.get("mailBoxList");
    //    List<MailBox> bucketList = (List<MailBox>) params.get("bucketList");
>>>>>>> 191ac2dd32d0a65f35e3e6cf515ff295fd68b7d7

    loadObjects("member.json", memberList, Member.class);
    loadObjects("counselingmember.json", counselingMemberList,CounselingMember.class);
    loadObjects("medicine.json", medicineList, Medicine.class);
    loadObjects("freeboard.json", freeBoardList, FreeBoard.class);
    loadObjects("notice.json", noticeBoardList, NoticeBoard.class);
    loadObjects("report.json", reportList, FreeBoard.class);
    loadObjects("mailbox.json", mailBoxList, MailBox.class);
    //loadObjects("bucketlist.json",bucketList,Bucket.class);

  }

  @SuppressWarnings("unchecked")
  @Override
  public void contextDestroyed(Map<String, Object> params) {

    List<Member> memberList = (List<Member>) params.get("memberList");
<<<<<<< HEAD
    List<CounselingMember> counselingMemberList = new LinkedList<>();
    List<Medicine> medicineList = new LinkedList<>();
    List<FreeBoard> freeBoardList = new LinkedList<>();
    List<NoticeBoard> noticeBoardList = new LinkedList<>();
    List<FreeBoard> reportList = new LinkedList<>();
    List<MailBox> mailBoxList = new LinkedList<>();
    List<Bucket> bucketList = new LinkedList<>();
=======
    List<CounselingMember> counselingMemberList = (List<CounselingMember>) params.get("counselingMemberList");
    List<Medicine> medicineList = (List<Medicine>) params.get("medicineList");
    List<FreeBoard> freeBoardList = (List<FreeBoard>) params.get("freeBoardList");
    List<NoticeBoard> noticeBoardList = (List<NoticeBoard>) params.get("noticeBoardList");
    List<FreeBoard> reportList = (List<FreeBoard>) params.get("reportList");
    List<MailBox> mailBoxList = (List<MailBox>) params.get("mailBoxList");
    List<Bucket> bucketList = (List<Bucket>) params.get("bucketList");
>>>>>>> 191ac2dd32d0a65f35e3e6cf515ff295fd68b7d7

    saveObjects("freeboard.json", freeBoardList);
    saveObjects("report.json", reportList);
    saveObjects("member.json", memberList);
    saveObjects("medicine.json", medicineList);
    saveObjects("notice.json", noticeBoardList);
    saveObjects("mailbox.json", mailBoxList);
    saveObjects("counselingmember.json",counselingMemberList);
    saveObjects("bucketlist.json",bucketList);

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
