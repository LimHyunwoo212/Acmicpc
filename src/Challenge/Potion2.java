package Challenge;

/**
 * Created by hyunwoo on 2017-03-02.
 */
import java.util.*;

/**
 * <pre>
 *       Test
 *          |_ Potion2
 * </pre>
 * <p>
 * Desc <p>
 * <pre>
 *
 * </pre>
 *
 */
public class Potion2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        List<PotionVO> resultList = new ArrayList<>();

        // 재료입력받는 부분
        Map<String, Integer> map = new HashMap<>();
        for(int idx=0; idx<n; idx++){
            map.put(scanner.next(), scanner.nextInt());
        }

        /**
         * 식을 계산하는 부분
         * 새로운 재료가 등장하면 그걸 다시 맵에 넣어서 나중에 최종적으로 더해지도록 만들거야
         */
        for(int idx=0; idx<m; idx++){
            int result = 0;
            boolean isNewMt = false;
            String newPotion = scanner.next();
            String[] split = newPotion.split("=");
            String[] mt = split[1].split("\\+");

            PotionVO vo = new PotionVO();
            vo.setPotion(split[0]);
            List<String> tmpList =  new ArrayList<>();
            for(int j=0; j<mt.length; j++){
                Integer mtCnt = Integer.valueOf(mt[j].substring(0,1));
                String mtName = mt[j].substring(1,mt[j].length());
                tmpList.add(mt[j]);

                if(!map.containsKey(mtName)) {
                    isNewMt = true;
                }
                else {
                    result += mtCnt * map.get(mtName);
                }
            }
            vo.setMtList(tmpList);
            if(!isNewMt) {
                vo.setPrice(result);
                map.put(vo.getPotion(),vo.getPrice());
            }

            resultList.add(vo);
        }

        int totalResultCnt = 0;
        for(PotionVO vo : resultList){
            if(vo.getPrice() == -1 ) {
                List<String> mtList = vo.getMtList();
                for(String mt : mtList){
                    Integer mtCnt = Integer.valueOf(mt.substring(0,1));
                    String mtName = mt.substring(1, mt.length());
                    totalResultCnt += mtCnt * map.get(mtName); // NULLPOINTEREXEPTION
                }
            }
        }

        if(totalResultCnt>1000000000) System.out.println(1000000001);
        else if (totalResultCnt != -1) System.out.println(totalResultCnt);
        else System.out.println(-1);

    }

    public static class PotionVO{
        String potion;
        List<String> mtList;
        Integer price = -1;

        public String getPotion() {
            return potion;
        }

        public void setPotion(String potion) {
            this.potion = potion;
        }

        public List<String> getMtList() {
            return mtList;
        }

        public void setMtList(List<String> mtList) {
            this.mtList = mtList;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "PotionVO{" +
                    "potion='" + potion + '\'' +
                    ", mtList=" + mtList +
                    ", price=" + price +
                    '}';
        }
    }
}