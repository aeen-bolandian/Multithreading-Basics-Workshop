import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Example06 {

    public static class LoadingRunnable implements Runnable {
        private volatile boolean running = true;

        public void stop() {
            running = false;
        }

        @Override
        public void run() {
            System.out.print("Loading");
            while (running) {
                for (int i = 0; i < 3 && running; i++) {
                    System.out.print(".");
                    try {
                        Thread.sleep(300); // Delay for better visibility
                    } catch (InterruptedException e) {
                        running = false;
                    }
                }
                System.out.print("\b\b\b   \b\b\b"); // Erase dots
            }
        }
    }

    public static class TaskRunnable implements Runnable {
        private final File inputFile;
        private final File outputFile;

        public TaskRunnable(File inputFile, File outputFile) {
            this.inputFile = inputFile;
            this.outputFile = outputFile;
        }

        @Override
        public void run() {
            if (!inputFile.exists() || !inputFile.isFile()) {
                System.out.println("\nInput file does not exist or is not a valid file.");
                return;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    String hashedPassword = hashText(line);
                    writer.write(hashedPassword);
                    writer.newLine();
                }

                System.out.println("\nHashing complete.");

            } catch (IOException e) {
                System.err.println("Error while processing file: " + e.getMessage());
            } catch (NoSuchAlgorithmException e) {
                System.err.println("Hashing algorithm not found: " + e.getMessage());
            }
        }

        private String hashText(String input) throws NoSuchAlgorithmException {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Locate the input file using getClass().getResource()
        File inputFile = null;
        try {
            inputFile = new File(Example06.class.getResource("/plain-text-passwords.txt").getFile());
        } catch (Exception e) {
            System.out.println("Error locating input file. Make sure the file exists in the resources folder.");
            return;
        }

        File outputFile = new File("hashed-passwords.txt");

        LoadingRunnable loadingRunnable = new LoadingRunnable();
        Thread loadingThread = new Thread(loadingRunnable);

        TaskRunnable taskRunnable = new TaskRunnable(inputFile, outputFile);
        Thread taskThread = new Thread(taskRunnable);

        // Start threads
        loadingThread.start();
        taskThread.start();

        // Wait for the hashing task to finish
        taskThread.join();
        loadingRunnable.stop();
        loadingThread.join();

        System.out.println("\nTask completed! Hashed passwords saved to: " + outputFile.getAbsolutePath());
    }
}
