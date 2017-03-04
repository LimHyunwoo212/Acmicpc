package Challenge;

import java.util.*;

/**
 * Created by hyunwoo on 2017-02-21.
 * 문제
 과학자 임문빈은 마법의 물약을 만든다. 임문빈은 많은 재료를 가지고 있고, 다음과 같은 식을 이용해서 만든다.
 S=N1×S1+...+Nk×Sk
 여기서 N1, ..., Nk는 1보다 크거나 같고, 9보다 작거나 같은 한 자리 숫자이고, S1, ..., Sk는 재료의 이름이다.
 그리고, k는 1보다 크거나 같은 자연수이다. 마지막으로 S는 마법의 물약의 이름이다.
 위의 식은 N1만큼 S1을 넣고, ..., Nk만큼 Sk를 넣으면 S가 1만큼 만들어 진다는 얘기와 같다.
 같은 물약을 만드는데 여러가지 재료법이 있을 수도 있다. 이 때는, 아무거나 사용하면 된다.
 마법의 물약은 또 다른 물약을 만드는데 재료가 될 수도 있고, 이 중 어떤 재료는 시장에서 살 수 있다.
 임문빈은 이름이 LOVE인 마법의 물약을 만들려고 한다. (이 물약을 먹으면 임문빈을 사랑하게 된다)
 시장에서 파는 재료와 그 가격이 주어지고, 임문빈이 만들 수 있는 모든 물약의 식이 주어진다.
 이 때, LOVE를 1만큼 만드는데 드는 비용의 최솟값을 출력한다.

 * 입력
 첫째 줄에 시장에서 파는 재료의 개수 N과 임문빈이 만들 수 있는 물약의 식의 개수 M이 주어진다.
 둘째 줄 부터 N개의 줄에는 시장에서 파는 재료의 이름과 그 가격이 공백으로 구분해서 주어진다.
 재료의 이름은 오직 알파벳 대문자로만 이루어져 있고, 파는 재료는 중복되지 않는다.
 그 다음 줄 부터 M개의 줄에는 물약의 식이 문제 상단과 같은 형식으로 주어진다.
 N은 50보다 작거나 같은 자연수이고, 재료의 이름의 길이는 최대 50이다. 가격은 100보다 작거나 같은 자연수이다.
 M은 50보다 작거나 같은 자연수 또는 0이다. 각 식의 길이도 최대 50이다.

 * 출력
 첫째 줄에 LOVE를 1만큼 만드는데 드는 비용의 최솟값을 출력한다.
 만약 그 값이 1000000000보다 크다면 1000000001을 출력한다. 만약 LOVE를 만드는 것이 불가능 하다면 -1을 출력한다.

 * 입력 예시
3 2
WATER 2
HONEY 6
HOP 9
LOVE=2WATER+4HONEY+2BEER
BEER=1HOP+3WATER+1HOP

 * 출력
 76
 */

public class Potion {

    public static void main(String[] args) {

        long answer;
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        String[][] potions = new String[2][m];

        //ArrayList<HashMap<String, Integer>> formula = new ArrayList<>();
        ArrayList<Tuple2<String, Integer>[]> formula = new ArrayList<>();

        //HashMap으로 market정보 받기
        HashMap<String, Integer> market = new HashMap<>();

        for(int i = 0; i < n; i++) {
            market.put(scanner.next(), scanner.nextInt());
        }
        for(int i = 0; i < m; i++){
            String tmpStr = scanner.next();
            potions[0][i] = tmpStr.split("=")[0];
            potions[1][i] = tmpStr.split("=")[1];
            String[] tmpStrArr = potions[1][i].split("\\+");
            Tuple2<String, Integer>[] tmpTuple = new Tuple2[tmpStrArr.length];
            int tupleIdx = 0;
            for(String idx : tmpStrArr) {
                tmpTuple[tupleIdx++] = Tuple2.create(idx.substring(1),(int)idx.charAt(0)-48);
            }
            formula.add(tmpTuple);
        }
        answer = combiFormula("LOVE", formula, potions, market);

        System.out.println(answer);
    }

    private static long combiFormula(String potionName, ArrayList<Tuple2<String, Integer>[]> formula, String[][] potions, HashMap<String, Integer> market){
        long answer = 0;
        for(int i = 0; i < potions[0].length; i ++) {
            if(potionName.equals(potions[0][i])){

                for(Tuple2 t : formula.get(i)){
                    if(market.containsKey(t._1)) {
                        answer += (market.get(t._1) * (int)t._2);
                    }
                    else{
                        answer += (int)t._2*combiFormula((String)t._1,formula, potions, market);
                    }
                    if(answer > 1000000000) return 1000000001;
                }
            }
        }
        return answer;
    }

     static class Tuple2<T1, T2> {
        final T1 _1;
        final T2 _2;
        Tuple2(T1 _1, T2 _2) {
            this._1 = _1;
            this._2 = _2;
        }
        static <T1, T2> Tuple2<T1, T2> create(T1 a, T2 b) {
            return new Tuple2<>(a, b);
        }
    }

}
