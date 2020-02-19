package ques_9;

public class WoodenChair extends Chair {
    @Override
    public void stressTest() {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("pases the stress test");
    }
    public String chairType(){
        return "Wooden Chair";
    }
    @Override
    public void fireTest() {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("FAILS Fire Test");
    }
}