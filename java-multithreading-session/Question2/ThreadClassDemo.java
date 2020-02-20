package Question2;

class Thread1 extends Thread{
    @Override
    public void run() {
        for (int i=0;i<50;i++){
            System.out.println("Itertation: "+ i);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ThreadClassDemo {
    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        t1.start();

        Thread1 t2 = new Thread1();
        t2.start();
    }
}
