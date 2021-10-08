package util;

import java.sql.Date;
import java.util.Scanner;

public class Prompt {

  static Scanner keyboardScan = new Scanner(System.in);

  public static String inputString(String title) {
    System.out.print(title);
    return keyboardScan.nextLine();
  }

  public static int inputInt(String title) {
    return Integer.parseInt(inputString(title));
  }

  public static Date inputDate(String title) {
    return Date.valueOf(inputString(title));
  }

  public static boolean inputBoolean(String title) {
    if(title.equals("의사")) 
      return true;
    else if(title.equals("일반")) 
      return false;

    return false;
  }

  public static void close() {
    keyboardScan.close();
  }
}



