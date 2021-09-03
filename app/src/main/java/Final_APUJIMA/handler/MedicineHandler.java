package Final_APUJIMA.handler;
import Final_APUJIMA.util.Prompt;

public class MedicineHandler {
  String [] medicineArr = {"브린텔릭스", "아빌리파이", "로라반" , "프로작", "알프람"};

  public void Mlist() {
    System.out.println();
    System.out.println("[약 리스트]");
    System.out.println();
    for(int i = 0; i< medicineArr.length; i++) {
      System.out.println(medicineArr[i]);
    }
    System.out.println();

    SearchM();

  }

  public void SearchM() {
    while(true) {
      System.out.println("1) 의약품 검색");
      System.out.println("0) 뒤로가기");
      int input0 = Prompt.inputInt("선택> ");
      if (input0 == 0) {
        return;
      }else {
        String input = Prompt.inputString("찾을 약의 이름을 입력해주세요 > ");
        System.out.println();
        String medicine = FindM(input);
        if(medicine == null) {
          System.out.println("찾는 약이 없습니다.");
        }
        else {
          System.out.println("약의 이름 : " + medicine + "이며 안정제 역할을 합니다.");
          System.out.println();
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

