package Challenge;

import java.util.Scanner;

/**
 * Created by hyunwoo on 2017-03-06.
 */
public class DigitalCounter {
    static int[] num = { 6, 2, 5, 5, 4, 5, 6, 3, 7, 5 };
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input = s.next();
        long ans = 1;
        long inputToInt = Long.parseLong(input)+1;
        int originLength = input.length();
        int count = counter(input,originLength);
        while(count != counter(String.valueOf(inputToInt),originLength)){

            inputToInt++;
            if(String.valueOf(inputToInt).length() > originLength) inputToInt = 0;
            ans++;
        }
        System.out.println(inputToInt);
        System.out.println(ans);
    }
    private static int counter(String str, int length){
        int count = num[0]*(length - str.length());
        for(int i = 0; i < str.length(); i++){
            count += num[str.charAt(i)-48];
        }
        return count;
    }
}
