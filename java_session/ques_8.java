package java_session;
/* Write a program to reverse a string and remove
 character from index 4 to index 9 from the reversed string using String Buffer*/

public class ques_8 {
    public static void main(String[] args) {
        StringBuffer s = new StringBuffer("ashutosh srivastava");
        //reverse
        s = s.reverse();
        System.out.println(s);
        s.delete(4,10);
        System.out.println(s);
    }
}
