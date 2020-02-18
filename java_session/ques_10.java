package java_session;
/*Write a single program for following operation using overloading
        A) Adding 2 integer number
        B) Adding 2 double
        C) multiplying 2 float
        D) multiplying 2 int
        E) concate 2 string
        F) Concate 3 String*/
public class ques_10 {
    // A) Adding 2 integer number
    int sum(int a,int b){
        return(a+b);
    }
    // B) Adding 2 double
    double sum(double a, double b){
        return (a+b);
    }
    // C) multiplying 2 float
    float multiply(float a , float b){
        return (a*b);
    }
    // D) multiply 2 int
    int multiply(int a , int b){
        return (a*b);
    }
    //E) concat 2 strings
    String concat(String a, String b){
        return (a+b);
    }
    //F) concat 3 strings
    String concat(String a , String b, String c){
        return (a+b+c);
    }

    public static void main(String[] args){
        ques_10 q = new ques_10();
        System.out.println(q.sum(4,5));
        System.out.println(q.sum(4.4,7.9));
        System.out.println(q.multiply(9,8));
        System.out.println(q.multiply(9.9f, 8.8f));
        System.out.println(q.concat("Ashutosh ","Srivastava"));
        System.out.println(q.concat("Ashutosh ", "Srivastava ", "Hello!"));
    }
}
