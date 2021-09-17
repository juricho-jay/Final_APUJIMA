package pms.handler;

import java.util.ArrayList;
import java.util.List;

public class WiseSaying implements Command{

  @Override
  public void execute(CommandRequest request) {

    List<String> list = new ArrayList<>();
    list.add("[힘이들땐 하늘을 봐~ 나는 항상 혼자가 아니야~]");
    list.add("[나는 지금 힘이 들지만 집에가서 침대에 누우면 모든게 행복해 질거야~]");
    list.add("[공부하는게 힘든데 자고 일어나면 코딩 만렙]");
    list.add("[여보세요~ 나야~ 거기 잘 지내니~ 여보세요 왜 말 안하니~]");
    list.add("[늦었다고 생각될때는 정말 늦은 것이다.. 그래도 지금 시작해야 더 늦지 않을 수 있다.]");
    list.add("[내가 더 좋은 사람이 되고싶어~ 더 아름답게 널 안을수 있게~]");
    list.add("[많이... 힘들지..? 나도...ㅜㅜ]");
    list.add("[크게 실패할 용기있는 자만이 크게 이룰 수 있습니다.]");
    list.add("[작은 일에서 성장할 능력이 있는 사람에게 큰일이 주어진다.]");
    list.add("[끝까지 해보기 전까지는 늘 불가능해 보입니다.]");
    list.add("[고통은 잠시이지만 포기는 평생 남는다.]");
    list.add("[생생하게 꿈꾸면 이루어진다.]");
    list.add("[당신의 오늘 하루는 어떠셨나요?]");
    list.add("[100개의 응원글 중 이것을 뽑은 당신은 오늘 행복해질 것입니다.]");


    int index = (int)(Math.random() * list.size());

    System.out.println(list.get(index));


  }

}
