package Question6;

public class SleepJoinDemo implements Runnable{
    private int count = 0;


    public static void main(String[] args)
    {
      SleepJoinDemo sleepJoinDemo = new SleepJoinDemo();
      sleepJoinDemo.run();
    }
    public void run() {
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                for(int i = 0; i < 10000; i++) {
                    count++;
                }
            }
        });
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                for(int i = 0; i < 10000; i++) {
                    count++;
                }
            }
        });
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Count is: " + count);
    }
}
