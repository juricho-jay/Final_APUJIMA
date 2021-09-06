package pms.handler;

import java.sql.Date;
import java.util.List;
import pms.domain.DoctorMember;

public class AbstractDoctor {

  List<DoctorMember> doctormemberList; 
  static DoctorMember loginUser;

  public AbstractDoctor(List<DoctorMember> doctormemberList) {
    this.doctormemberList = doctormemberList; // => ?? 생성자 초기화

    DoctorMember testUser = new DoctorMember();
    testUser.setName("aaa");
    testUser.setId("aaaaaa");
    testUser.setPassword("1111");
    testUser.setBirthDay("0101");
    testUser.setEmail("ggg@test.com");
    testUser.setPhoneNum("010-1111-1111");
    testUser.setPhoto("aaa.gif");
    testUser.setSex("female");
    testUser.setRegisteredDate(new Date(System.currentTimeMillis()));

    doctormemberList.add(testUser);

    testUser = new DoctorMember();
    testUser.setName("bbb");
    testUser.setId("bbbbbb");
    testUser.setPassword("2222");
    testUser.setBirthDay("0202");
    testUser.setEmail("hhh@test.com");
    testUser.setPhoneNum("010-2222-2222");
    testUser.setPhoto("bbb.gif");
    testUser.setSex("male");
    testUser.setRegisteredDate(new Date(System.currentTimeMillis()));

    doctormemberList.add(testUser);

    testUser = new DoctorMember();
    testUser.setName("ccc");
    testUser.setId("cccccc");
    testUser.setPassword("3333");
    testUser.setBirthDay("0303");
    testUser.setEmail("iii@test.com");
    testUser.setPhoneNum("010-3333-3333");
    testUser.setPhoto("ccc.gif");
    testUser.setSex("female");
    testUser.setRegisteredDate(new Date(System.currentTimeMillis()));

    doctormemberList.add(testUser);

  }


  protected DoctorMember vaildLogin(String id, String password) {
    for (DoctorMember doctormember : doctormemberList) {
      if (doctormember.getId().equals(id) && doctormember.getPassword().equals(password)) {
        System.out.println();
        System.out.println("로그인 성공!");
        loginUser = doctormember;
        System.out.println();
        return doctormember;
      }
    }
    return null;
  }


  protected DoctorMember validFindId(String Name,String PhoneNo)   {
    DoctorMember [] arr = doctormemberList.toArray(new DoctorMember[0]);
    for (DoctorMember doctormember : arr) {
      if (doctormember.getName().equals(Name) && (doctormember.getPhoneNum().equals(PhoneNo))) {
        return doctormember;
      }
    }
    return null;
  }

  protected DoctorMember validFindPassword(String id,String PhoneNo)   {
    DoctorMember [] arr = doctormemberList.toArray(new DoctorMember[0]);
    for (DoctorMember doctormember : arr) {
      if (doctormember.getId().equals(id) && (doctormember.getPhoneNum().equals(PhoneNo))) {
        return doctormember;
      }
    }
    return null;
  }



}
