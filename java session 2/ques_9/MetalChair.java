package ques_9;

public class MetalChair extends Chair {
    @Override
    public void stressTest() {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("passes the stress test");
    }
    public String chairType(){
        return "metal chair";
    }
    public void fireTest() {
        System.out.println("pases the fire test");
        //To change body of implemented methods use File | Settings | File Templates.
    }
}