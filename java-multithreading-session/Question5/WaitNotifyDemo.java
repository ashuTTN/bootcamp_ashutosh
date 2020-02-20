package Question5;

public class WaitNotifyDemo {
    public static void main(String[] args) throws InterruptedException {
        final Process process = new Process();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process.produce();
                }catch (InterruptedException e){

                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process.consumer();
                }catch (InterruptedException e){

                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }
}
