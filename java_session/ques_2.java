package java_session;
//Q2. Write a program to find the number of occurrences of the duplicate words in a string and print them ?
public class ques_2 {
    public static void main(String[] args) {
        String s = "Hello world hello HELLO this world is beautiful hello boy";
        String lower = s.toLowerCase();
        String ss[]= lower.split(" ");

        for(int i=0; i<ss.length; i++){
            int counter = 1;
            for(int j=i+1; j<ss.length;j++){
                if (ss[i].equals(ss[j])){
                    counter++;

                    ss[j] = "0";
                }
            }


            if (counter > 1 && ss[i]!="0"){
                System.out.println("Word: " + ss[i] + " Occurences: "+ counter);
            }
        }



    }

}


