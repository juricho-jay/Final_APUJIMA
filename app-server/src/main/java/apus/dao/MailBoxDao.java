package apus.dao;

import java.util.List;
import apus.domain.MailBox;

public interface MailBoxDao {

  void insert (MailBox mailbox) throws Exception;
  List<MailBox> findAll() throws Exception;
  MailBox findByNo(int no) throws Exception;
  //  MailBox findById(String id) throws Exception;
  //MailBox findByName(String name) throws Exception;
  void update(MailBox mailBox) throws Exception;
  void delete(int no) throws Exception;
  List<MailBox> findByKeyword(String keyword) throws Exception;


}
