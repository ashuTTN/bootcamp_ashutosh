package test;

public class test {
    static int foo(int i){
        try{
            System.out.println("inside try");
        }
        catch (Exception e){
            System.out.println("inside catch");
            return 10;
        }
        finally{
            System.out.println("inside finally");

        }
        return 7;
    }

    public static void main(String[] args) {
        int c = foo(7);
        System.out.println(c);
    }
}

