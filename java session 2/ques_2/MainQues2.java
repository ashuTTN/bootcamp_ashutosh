package ques_2;
//WAP to sorting string without using string Methods?.
public class MainQues2 {
    public static void main(String[] args) {
        //Strings are immutable and cannot be sorted in-place.
        //String buffer is not used because not specified in question.
        String str = "lkjihgaossjyknfaxztrsnmwkeupwmdfeqdvcba";
        char ch[] = str.toCharArray();
        for(int j = 0 ; j < ch.length ; j++) {
            for (int i = 0; i < ch.length-1; i++) {
                if (ch[i] > ch[i + 1]) {
                    char temp = ch[i];
                    ch[i] = ch[i + 1];
                    ch[i + 1] = temp;
                }
            }
        }
        System.out.println(new String(ch)); // charecter array converted to string
    }
}
