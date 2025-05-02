public class Example02 {
    //TODO(1): Create a class that implements the Runnable interface

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for(int i = 0; i < 20; i++) {
                System.out.println("hello from " + Thread.currentThread().getName());
            }
        }
    }
    public static void main(String[] args)
    {
        //TODO(2): start a thread that runs the Runnable
        //TODO(0): print 20 greetings
        Thread thread = new Thread(new MyRunnable());
        thread.start();
    }
}
