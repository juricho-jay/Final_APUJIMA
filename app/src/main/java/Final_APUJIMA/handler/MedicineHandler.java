package Final_APUJIMA.handler;
import Final_APUJIMA.util.Prompt;

public class MedicineHandler {
  String [] medicineArr = {"브린텔릭스", "아빌리파이", "로라반" , "프로작", "알프람"};

  public void Mlist() {
    System.out.println("[약 리스트]");
    System.out.println();
    for(int i = 0; i< medicineArr.length; i++) {
      System.out.println(medicineArr[i]);
    }
    System.out.println();

  }

  public void SearchM() {
    while(true) {
      System.out.println("1) 의약품 검색");
      System.out.println("0) 종료");
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
  
 public void doMedicineMenu() {
    System.out.println("[약국] 페이지입니다. 선택해주세요");
    MedicineHandler medicine = new MedicineHandler();
    System.out.println("1) 약 목록");
    System.out.println("2) 약 찾기");
    int select = Prompt.inputInt("선택> ");
    if (select == 1) {
      medicine.Mlist();
    } else if (select == 2) {
      medicine.SearchM();
    } else {
      System.out.println("잘못 선택하셨습니다 ");
    }
  }

}

