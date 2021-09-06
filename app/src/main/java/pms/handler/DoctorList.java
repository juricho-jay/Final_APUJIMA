package pms.handler;

import java.util.List;
import pms.domain.DoctorMember;

public class DoctorList extends AbstractDoctor{

  public DoctorList(List<DoctorMember> doctormemberList) {
    super(doctormemberList);
    // TODO Auto-generated constructor stub
  }

  public void list() {
    System.out.println("[회원 목록]");


    for (DoctorMember doctormember : doctormemberList) {
      System.out.printf("%s, %s, %s ,\n", 
          doctormember.getName(),
          doctormember.getId(),
          doctormember.getPassword()
          );
    }
  }

}
