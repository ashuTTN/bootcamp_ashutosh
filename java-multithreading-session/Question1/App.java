package Question1;

import java.util.Scanner;

class Process extends Thread{
    private volatile boolean runing = true;

    @Override
    public void run() {
        while (runing){
            System.out.println("Hello");
            System.out.println("\n");


            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown(){
        runing=false;
    }
}

public class App {
    public static void main(String[] args) {
        Process process1 = new Process();
        process1.start();

        Scanner scanner =new Scanner(System.in);
        scanner.nextLine();

        process1.shutdown();

    }

}
