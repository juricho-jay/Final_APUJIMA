package pms.listener;

import java.util.Map;
import pms.context.ApplicationContextListener;

public class AppInitListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String,Object> params) {
    System.out.println("\r\n"
        + "|￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣ |\r\n"
        + "|[APUJIMA]에 오신 것을 환영합니다.|\r\n"
        + "|＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿ |\r\n"
        + "(\\__/) ||\r\n"
        + "(•ㅅ•).||\r\n"
        + "/ . . . .づ\r\n"
        + "");
  }

  @Override
  public void contextDestroyed(Map<String,Object> params) {
    System.out.println("  (\\_(\\\r\n"
        + "(„• ֊ •„)\r\n"
        + "(  O☕O  )");  
  }

}
