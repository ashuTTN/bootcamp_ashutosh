package ques_5;
//5. WAP to show object cloning in java
// using cloneable and copy constructor both.
public class MainQues5 {
    public static void main(String[] args) throws CloneNotSupportedException
    {
        Abc obj = new Abc();
        obj.i = 5;
        obj.j = 6;
        Abc obj1 = (Abc)obj.clone();
        System.out.println(obj1);
        System.out.println(obj);


        xyz a1 = new xyz(20,30);
        System.out.println(a1);
        xyz copyOfa1 = new xyz(a1);
        System.out.println(copyOfa1);

    }
}


class xyz{
    int x ;
    int y ;
    xyz(int x, int y){
        this.x = x;
        this.y = y;
    }
    xyz(xyz copy){
        this.x = copy.x;
        this.y = copy.y;
    }
    public String  toString(){
        return("x = "+ x + "y = "+ y);
    }
}



class Abc implements Cloneable
{
    int i;
    int j;

    public String toString(){
        return "Abc("+" i = "+ i +"j="+j+")";
    }
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
