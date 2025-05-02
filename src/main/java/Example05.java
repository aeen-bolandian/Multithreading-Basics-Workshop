public class Example05 {
    //TODO: create the loading runnable
    //TODO: create the task runnable

    public static class LoadingRunnable implements Runnable {

        @Override
        public void run() {

                System.out.print("Loading");
                for (int i = 0; !Thread.interrupted(); i++) {
                    if (i % 6 == 0)
                        System.out.print(".");
                    if (i % 6 == 1)
                        System.out.print(".");
                    if (i % 6 == 2)
                        System.out.print(".");
                    if (i % 6 == 3)
                        System.out.print("\b");
                    if (i % 6 == 4)
                        System.out.print("\b");
                    if (i % 6 == 5)
                        System.out.print("\b");

                    try { Thread.sleep(100); } catch (InterruptedException e) { break;}
                }
        }

    }

    public static class TaskRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 20 ; i++) {
                try { Thread.sleep(200); } catch (InterruptedException e) { break;}
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Thread taskThread = new Thread(new TaskRunnable());
        Thread loadingThread = new Thread(new LoadingRunnable());
        //TODO: start the task thread
        //TODO: start the loading thread
        //TODO: interrupt the loading thread when the task thread is done processing
        //TODO: print a message showing that the task is done

        taskThread.start();
        loadingThread.start();
        taskThread.join();
        loadingThread.interrupt();

        System.out.println("\ntask completed");

    }
}
