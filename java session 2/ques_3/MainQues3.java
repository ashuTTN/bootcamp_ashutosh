package ques_3;

public class MainQues3 {
    public static void main(String[] args) {

        try{
            Class.forName("obj"); //No class obj       
        }
        catch (ClassNotFoundException e){
            System.out.println(e);
        }
    }
}
