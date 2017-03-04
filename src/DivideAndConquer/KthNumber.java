package DivideAndConquer;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by hyunwoo on 2017-02-21.
 * 문제
 수 N개 A1, A2, ..., AN이 주어진다. A를 오름차순 정렬했을 때, 앞에서부터 K번째 있는 수를 구하는 프로그램을 작성하시오.

 * 입력
 첫째 줄에 N(1 ≤ N ≤ 5,000,000)과 K (1 ≤ K ≤ N)이 주어진다.
 둘째에는 A1, A2, ..., AN이 주어진다. (-109 ≤ Ai ≤ 109)

 * 출력
 A를 정렬했을 때, 앞에서부터 K번째 있는 수를 출력한다.
 */
public class KthNumber {
    public int kthNumber(int[] input, int k){
        Arrays.sort(input);
        return input[k-1];
    }

    public static void main(String[] args) {
        KthNumber kthNumber = new KthNumber();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] input = new int[n];
        for(int idx = 0; idx < n; idx++){
            input[idx] = scanner.nextInt();
        }
        int out = kthNumber.kthNumber(input, k);
        System.out.println(out);
    }
}
