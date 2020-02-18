package java_session;
/*Write a program to display values of enums using a constructor &
getPrice() method (Example display house & their prices)*/

enum House{
    HOUSETYPE1(5000000),HOUSETYPE2(1000000), HOUSETYPE3(200000), HOUSETYPE4(350000);
    private int price;
    House(int p){
        price = p;
    }
    int getPrice(){
        return price;
    }

}

public class ques_9 {

    public static void main(String[] args){
        System.out.println("Price of "+House.HOUSETYPE1 +" is "+ House.HOUSETYPE1.getPrice());
        System.out.println("Price of "+House.HOUSETYPE2 +" is "+House.HOUSETYPE2.getPrice());
        System.out.println("Price of "+House.HOUSETYPE3 +" is "+House.HOUSETYPE3.getPrice());
        System.out.println("Price of "+House.HOUSETYPE4 +" is "+House.HOUSETYPE4.getPrice());
    }
}
