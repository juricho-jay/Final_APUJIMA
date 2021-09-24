package pms.handler;

import java.util.List;
import pms.domain.Bucket;

public abstract class AbstractBucketListHandler implements Command{

  List<Bucket> bucketList;

  public AbstractBucketListHandler(List<Bucket> bucketList) {
    this.bucketList = bucketList;
  }

  protected Bucket findByNo(int no) {
    for (Bucket bucket : bucketList) {
      if (bucket.getNo() ==  no) {
        return bucket;
      }
    }
    return null;
  }

}
