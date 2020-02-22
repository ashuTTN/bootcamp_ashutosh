package Ques2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Write a method that takes a string and returns the number of unique characters in the string.
public class Ques2 {
    public static void main(String[] args) {
        String txt = "ashutoshhhhhhh";          //number of unique(not repeated) are 4
        txt.toUpperCase();
        Map<Character, Integer> map = new HashMap<>();
        char[] charArr = txt.toCharArray();

        for (int i = 0; i < charArr.length; i++) { // initializing the  hash map
            map.put(charArr[i],0);
        }

        for (int i = 0; i < charArr.length; i++) {  //counting occurencee of each charecter and storing with respective keys
            int count = map.get(charArr[i]);
            map.put(charArr[i],count+1);
        }
        int uniqueCount = 0 ;
        for (Map.Entry<Character, Integer> entry: map.entrySet()) { // counting the keys whoes values are less than 2 only
            if(entry.getValue() <2 ){
                uniqueCount++;
            }
        }
        System.out.println(uniqueCount);

    }

}
