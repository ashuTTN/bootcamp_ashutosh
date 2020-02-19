package ques_13;

//Q13. Create a custom exception that do not have any stack trace.
class except1 extends Exception{
    String s1;

    except1(String s2){
        s1=s2;
    }
    public String toString(){
        return ("My Exception occured " + s1);
    }

}


public class MainQues13 {
    public static void main(String[] args){
        try {
            System.out.println("In try block");
            throw new except1("Good Error is present");
        }catch (except1 e){
            System.out.println("In Catch block");
            System.out.println(e);

        }
    }
}
