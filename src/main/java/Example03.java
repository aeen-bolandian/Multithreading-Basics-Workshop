import java.sql.SQLOutput;

public class Example03 {
    //TODO(1): create a class that implements the Runnable interface
    //TODO(2): print greetings in a loop
    //TODO(5): add a way of ending the thread when it is interrupted
    //TODO(6): add sleep in the loop
    //TODO(7): handle InterruptedException

    public static class MyRunnable implements Runnable {
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    System.out.println("hello from " + Thread.currentThread().getName());
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " was interrupted!");
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args)
    {
        //TODO(0): print greetings in a loop
        //TODO(3): talk about thread.stop...
        //TODO(4): interrupt the other thread at some time
        //TODO(8): observe thread state

        Thread myThread = new Thread(new MyRunnable() , "myThread");

        System.out.println("Starting " + myThread.getName());
        myThread.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted!");
        }

        System.out.println("Interrupting " + myThread.getName());
        myThread.interrupt();

        while (myThread.isAlive()) {
            System.out.println(myThread.getName() + " state : " + myThread.getState());
            try {
                Thread.sleep(500);
            }
            catch (InterruptedException e) {
                System.out.println("Main thread interrupted!");
            }

            System.out.println(myThread.getName() + " has finished execution!");

        }

    }
}