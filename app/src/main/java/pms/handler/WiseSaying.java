package pms.handler;

import java.util.ArrayList;
import java.util.List;

public class WiseSaying implements Command{

  @Override
  public void execute() {

    List<String> list = new ArrayList<>();
    list.add("[힘이들땐 하늘을 봐~ 나는 항상 혼자가 아니야~]");
    list.add("[나는 지금 힘이 들지만 집에가서 침대에 누우면 모든게 행복해 질거야~]");
    list.add("[공부하는게 힘든데 자고 일어나면 코딩 만렙]");
    list.add("[여보세요~ 나야~ 거기 잘 지내니~ 여보세요 왜 말 안하니~]");
    list.add("[늦었다고 생각될때는 정말 늦은 것이다.. 그래도 지금 시작해야 더 늦지 않을 수 있다.]");
    list.add("[내가 더 좋은 사람이 되고싶어~ 더 아름답게 널 안을수 있게~]");
    list.add("[많이... 힘들지..? 나도...ㅜㅜ]");

    int index = (int)(Math.random() * list.size());

    System.out.println(list.get(index));


  }

}
