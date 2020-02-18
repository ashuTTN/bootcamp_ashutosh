package java_session;
//Write a program to print your Firstname,LastName & age using static block,static method & static variable respectively
public class ques_7 {
    static{
        System.out.println("Static block called");
        System.out.println("Ashutosh");
        System.out.println("Srivastava");
        System.out.println(20);
    }


    static String fname = "Ashutosh";
    static  String lname = "Srivastava";
    static int age = 20 ;


    public static void main(String[] args) {

        System.out.println(ques_7.fname);
        System.out.println(ques_7.lname);
        System.out.println(ques_7.age);
        myfun();
    }

    static void myfun(){
        System.out.println("static function");
        System.out.println(fname);
        System.out.println(lname);
        System.out.println(age);
    }
}
