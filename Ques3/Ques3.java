package Ques3;

import java.util.HashMap;
import java.util.Map;

//Write a method that takes a string and print
// the number of occurrence of each character characters in the string.
public class Ques3 {
    public static void main(String[] args) {
        String txt = "AshutoshAshutoshAshutoshha";
        txt = txt.toLowerCase();
        Map<Character,Integer> map = new HashMap<>();
        char charArr[] = txt.toCharArray();
        for (int i = 0; i < charArr.length; i++) { //initializing the hash map
            map.put(charArr[i],0);
        }
        for (int i = 0; i < charArr.length; i++) { // counting the number of occurence
            int count = map.get(charArr[i]);
            map.put(charArr[i],count+1);
        }
        System.out.println(map);
    }
}
