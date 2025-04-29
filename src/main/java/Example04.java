public class Example04 {

    public static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 20; i++)
            {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {

                }
                System.out.println("Hello from " + Thread.currentThread().getName());
            }
        }
    }
    public static void main(String[] args)
    {
        //TODO: create a thread and start it
        //TODO: print a message after the thread is done

        Thread t = new Thread(new MyRunnable());
        t.start();

        try {
            t.join();
        }
        catch (InterruptedException e) {
            System.out.println("Main thread interrupted while waiting for thread t");
        }

        System.out.println("Thread has been finished\nExiting main thread");

    }
}