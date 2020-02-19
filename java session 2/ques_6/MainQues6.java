package ques_6;
import java.util.Scanner;

public class MainQues6 {
    public static void main(String args[])
    {
        Scanner scn = new Scanner(System.in);
        try
        {
            int a[]=new int[5];
            a[3]=30/0;
            System.out.println(a[10]);
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println("This is ArrayIndexOutOfBounds Exception");
        }
        catch(ArithmeticException e)
        {
            System.out.println("This is Arithmetic Exception");
        }
        finally
        {
            System.out.println("This is finally");
        }

    }
}