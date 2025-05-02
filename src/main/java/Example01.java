import org.w3c.dom.ls.LSOutput;

public class Example01 {
    //TODO(1): Create a class that extends Thread class.
    //TODO(2): print greetings in the Thread class
    //TODO(4): repeat the greetings using a loop

    static class MyThread extends Thread {

        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("hello from " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public static void main(String[] args)
    {
        //TODO(3): Create an object of the class you created, and call start()
        //TODO(0): Write a greeting from the current thread
        MyThread t1 = new MyThread();
        t1.start();
        for (int i = 0; i < 10; i++) {
            System.out.println("hello from " + Thread.currentThread().getName());
        }
    }
}
