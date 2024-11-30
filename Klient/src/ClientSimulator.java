import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class ClientSimulator {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost";
        final int SERVER_PORT = 1234;
        final int NUM_CLIENTS = 250;

        ExecutorService threadPool = Executors.newFixedThreadPool(NUM_CLIENTS);

        for (int i = 0; i < NUM_CLIENTS; i++) {
            threadPool.execute(() -> {
                try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                    String question;
                    while ((question = in.readLine()) != null) {
                        System.out.println("Pytanie: " + question);
                        out.println("A");
                    }

                } catch (IOException e) {
                    System.err.println("Błąd klienta: " + e.getMessage());
                }
            });
        }

        threadPool.shutdown();
    }
}
