package Pashing;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by hyunwoo on 2017-02-22.
 * 문제
 다솜이는 기타를 많이 가지고 있다. 그리고 각각의 기타는 모두 다른 시리얼 번호를 가지고 있다.
 다솜이는 기타를 빨리 찾아서 빨리 사람들에게 연주해주기 위해서 기타를 시리얼 번호 순서대로 정렬하고자 한다.
 모든 시리얼 번호는 알파벳 대문자 (A-Z)와 숫자 (0-9)로 이루어져 있다.
 시리얼번호 A가 시리얼번호 B의 앞에 오는는 경우는 다음과 같다.
 A와 B의 길이가 다르면, 짧은 것이 먼저 온다.
 만약 서로 길이가 같다면, A의 모든 자리수의 합과 B의 모든 자리수의 합을 비교해서 작은 합을 가지는 것이 먼저온다. (숫자인 것만 더한다)
 만약 1,2번 둘 조건으로도 비교할 수 없으면, 사전순으로 비교한다. 숫자가 알파벳보다 사전순으로 작다.
 시리얼이 주어졌을 때, 정렬해서 출력하는 프로그램을 작성하시오.

 * 입력
 첫째 줄에 기타의 개수 N이 주어진다. N은 1,000보다 작거나 같다. 둘째 줄부터 N개의 줄에 시리얼 번호가 하나씩 주어진다. 시리얼 번호의 길이는 최대 50이고, 알파벳 대문자 또는 숫자로만 이루어져 있다. 시리얼 번호는 중복되지 않는다.

 * 출력
 첫째 줄부터 차례대로 N개의 줄에 한줄에 하나씩 시리얼 번호를 정렬한 결과를 출력한다.

 *예제 입력
 5
 ABCD
 145C
 A
 A910
 Z321
 *
 바바따빠바바따뿌
 빠마파빠바바뚜무
 도밬탕빠맣붏두부
 보뫃박발뚷투뭏부
 모도뫃희멓뭏뭏부
 모봌토범더벌뿌뚜
 뽀뽀멓멓더벓뻐뚜
 뽀더버머뻐더더버
 */
public class SerialNumber {

    private String[] serialNumber(String[] input, int num){
        //sort1 : 문자열길이 input[i].length  Selection sort
        // + 길이가 같은것들 문자열의 모든 수의 합 오름차순
        int idxMin;
        String temp;
        for (int i = 0; i < num-1; i++) {
            idxMin = i;
            for(int j = i+1; j < num; j++) {
                if (sumOfSerial(input[j]) < sumOfSerial(input[idxMin])) {
                    idxMin = j;
                }
            }
            temp = input[idxMin];
            input[idxMin] = input[i];
            input[i] = temp;
        }
        //sort3 : 1,2에서 비교 안되는것들 사전순 오름차순 (숫자 - 알파벳)
        end:for(int i = 0; i < num-1; i++){
            for(int j = i+1; j < num; j++){
                if(sumOfSerial(input[i]) == sumOfSerial(input[j])){
                    if(j == num-1){
                        Arrays.sort(input, i,j+1);
                        break end;
                    }
                }
                else{
                    if(j-i > 1){
                        Arrays.sort(input, i,j);
                    }
                    break;
                }
            }
        }
    return input;
    }

    private int sumOfSerial(String serial){
        int length = serial.length();
        int sum = length*length*10;
        for(int i = 0; i < length; i++){
            if(serial.charAt(i) > 47 && serial.charAt(i) < 58){
                sum += serial.charAt(i) - 48;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        SerialNumber serialNumber = new SerialNumber();
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        String[] input = new String[num];
        for (int i = 0; i < num; i++) {
            input[i] = scanner.next();
        }
        String[] tmp =  serialNumber.serialNumber(input, num);
        for (int idx = 0; idx < num; idx++) {
            System.out.println(tmp[idx] + " : " + serialNumber.sumOfSerial(tmp[idx]));
        }
    }

}
