package ques_8;

import java.util.Scanner;

public class MainQues8partB {
    public static void main(String[] args) {
        Scanner s1 = new Scanner(System.in);
        System.out.println("enter word");
        String word = s1.nextLine();

        do{

            if (word.charAt(0) == word.charAt(word.length()-1)){
                System.out.println("First char = Last char");
            }
            else if(word.equals("done")){
                break;
            }
            else{
                System.out.println("first not equal to last");
            }
            word = s1.nextLine();
        }while(!word.equals("done"));

    }
}
