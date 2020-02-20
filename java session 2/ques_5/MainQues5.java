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

