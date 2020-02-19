package ques_9;
import java.util.Scanner;

public class MainQues9 {
    public static void main(String[] args){
        System.out.println("Enter Table Type");
        Table table = null;
        Scanner input =  new Scanner(System.in);
        String str = input.next();
        if(str.equals("wooden")){
            table = new WoodenTable();
        }
        else if (str.equals("metal")){
            table = new MetalTable();
        }

        System.out.println(table.tableType());
        table.stressTest();
        table.fireTest();

        System.out.println("Enter Chair Type");
        Chair chair = null;
        Scanner input1 =  new Scanner(System.in);
        String str1 = input.next();
        if(str1.equals("wooden")){
            chair = new WoodenChair();

        }
        else if (str1.equals("metal")){
            chair = new MetalChair();
        }

        System.out.println(chair.chairType());
        chair.stressTest();
        chair.fireTest();

    }
}