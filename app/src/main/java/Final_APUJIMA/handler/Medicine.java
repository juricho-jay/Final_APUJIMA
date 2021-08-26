package Final_APUJIMA.handler;
import Final_APUJIMA.util.Prompt;

public class Medicine {
  String [] medicineArr = {"브린텔릭스", "아빌리파이", "로라반" , "프로작", "알프람"};
 
  



  public void Mlist() {
    System.out.println("[약 리스트]");
    System.out.println();
    for(int i = 0; i< medicineArr.length; i++) {
      System.out.println(medicineArr[i]);
    }
    System.out.println();

  }

  public void MSearch() {
    while(true) {
    int input0 = Prompt.inputInt("0)종료 1) 약 찾기 > "  );
    if (input0 == 0) {
      return;
    }else {
    String input = Prompt.inputString("찾을 약의 이름을 입력해주세요 > ");
    String medicine = FindM(input);
    if(medicine == null) {
      System.out.println("찾는 약이 없습니다.");
    }
    else {
      System.out.println("약의 이름 : " + medicine);
    }

  }
    }
  }
  public String FindM(String input) {

    String [] arr = medicineArr.clone();
    for (String s : arr) {
      if (s.equals(input)) {
        return s;
      }


    }
    return null;
  }
  
}

