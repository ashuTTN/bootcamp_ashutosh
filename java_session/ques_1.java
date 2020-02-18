package java_session;
// Write a program to replace a substring inside a string with other string .
public class ques_1 {
    public static void main(String[] args) {
        String s1 = "Hello";
        String s2 = "World";
        System.out.println(s1.replace(s1.substring(0,3),s2));
    }
}

