package pms.handler;

import java.util.List;
import pms.domain.CounselingMember;

public class AbstractCounselingMember {

  List<CounselingMember> counselingmemberList;
  //static Member LoginUser;

  public AbstractCounselingMember(List<CounselingMember> counselingmemberList) {
    this.counselingmemberList = counselingmemberList; 
  }

}
