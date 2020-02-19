package ques_8;

import java.util.Scanner;
//8. WAP to read words from the keyboard until the word done
// is entered.
// For each word except done,
// report whether its first character is equal
// to  its last character. For the required loop, use a
//a)while statement
//b)do-while statement
public class MainQues8PartA {
    public static void main(String[] args) {
        Scanner s1 = new Scanner(System.in);
        String word = s1.nextLine();
        while(!word.equals("done")){
            if(word.charAt(0) == word.charAt(word.length()-1)){
                System.out.println("first charecter = last charecter");
            }
            else{
                System.out.println("first charecter is not equal to last charecter");
            }
            word = s1.nextLine();
        }
    }
}
