import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by hyunwoo on 2017-03-06.
 */
public class Doodle {
    public static void main(String[] args) {
        ArrayList<Integer> numberList = new ArrayList<>();
        ArrayList<Integer> numDigit = new ArrayList<>();
        int tmpNumber;
        int count;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String input = br.readLine();
            String[] numStrList = input.split(" ");
            for (String numStr : numStrList) {
                numberList.add(Integer.parseInt(numStr));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Integer number : numberList) {
            tmpNumber = number;
            count = 0;
            while (tmpNumber > 0) {
                tmpNumber = tmpNumber / 10;
                count++;
            }
            numDigit.add(count);
        }
        for (int i = 0; i < numberList.size(); i++){
            if(numDigit.get(i) == 1){
                numberList.set(i, numberList.get(i)*11);
            }
        }
        for (int i = 0; i < numberList.size(); i++) {
            for (int j = 0; j < numberList.size() - 1; j++) {
                if (numberList.get(j) < numberList.get(j + 1)) {
                    tmpNumber = numberList.get(j);
                    numberList.set(j, numberList.get(j + 1));
                    numberList.set(j + 1, tmpNumber);
                    tmpNumber = numDigit.get(j);
                    numDigit.set(j, numDigit.get(j + 1));
                    numDigit.set(j + 1, tmpNumber);
                }
            }
        }
        for (int i = 0; i < numberList.size(); i++) {
            if(numDigit.get(i) == 1){
                numberList.set(i, (int)numberList.get(i).toString().charAt(0) - 48);
            }
        }
        String result1 = "";
        String result2 = "";
        for(int i = 0; i < numberList.size(); i++){
            result1 += numberList.get(i);
            result2 += numberList.get(numberList.size() - 1 - i);
        }
        System.out.println(Integer.parseInt(result1) +  Integer.parseInt(result2));
    }
}

