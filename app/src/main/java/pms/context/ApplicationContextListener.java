package pms.context;

import java.util.Map;

public interface ApplicationContextListener {
  void contextInitialized(Map<String,Object> params); // 애플리케이션이 시작한 후 즉시 호출된다.
  void contextDestroyed(Map<String,Object> params); // 애플리케이션이 종료하기 직전에 호출된다.
}


