package pms.handler;

import java.util.List;
import pms.domain.CounselingMember;

public abstract class AbstractCounselingMemberHandler implements Command{

  List<CounselingMember> counselingMemberList;
  //static Member LoginUser;

  public AbstractCounselingMemberHandler(List<CounselingMember> counselingMemberList) {
    this.counselingMemberList = counselingMemberList; 
  }

}
