package java_session;

import java.nio.charset.CharsetEncoder;

public class ques_4 {
    /*
    Calculate the number & Percentage Of Lowercase Letters,Uppercase Letters,
     Digits And Other Special Characters In A String
    */
    public static void main(String[] args) {
        int uCount = 0;
        int lCount = 0;
        int digitCount = 0;
        int specialCount = 0;

        String str = "HHHHHH World !! 12";
        for(int i = 0 ; i < str.length() ; i++){
            char ch = str.charAt(i);
            if(Character.isUpperCase(ch)){
                uCount++;
            }
            else if (Character.isLowerCase(ch)){
                lCount++;
            }
            else if (Character.isDigit(ch)){
                digitCount++;
            }
            else{
                specialCount++;
            }
        }
        double length = str.length();
        double upperPercent = (uCount/length)*100;
        //System.out.println(upperPercent);
        double lowerPercent = (lCount/length)*100;
        double otherPercent = (digitCount/length)*100;
        double specialPercent = (specialCount/length)*100;
        System.out.println("upper percentage = " + upperPercent);
        System.out.println("upper percentage = " + lowerPercent);
        System.out.println("upper percentage = " + otherPercent);
        System.out.println("upper percentage = " + specialPercent);
    }
}
