import java.io.*;
import java.net.Socket;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 1234;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {
            String serverResponse;
            int questionNumber = 1;

            while ((serverResponse = in.readLine()) != null) {
                if (serverResponse.startsWith("Twój wynik:")) {
                    System.out.println(serverResponse);
                    break;
                }
                if (serverResponse.startsWith("Pytanie " + questionNumber)) {
                    System.out.println(serverResponse);

                    for (int i = 0; i < 4; i++) {
                        System.out.println(in.readLine());
                    }

                    System.out.print("Podaj odpowiedz: ");
                    String answer = userInput.readLine();
                    out.println(answer);

                    questionNumber++;
                }

            }
            System.out.println("Test zakończony!");
        } catch (IOException e) {
            System.err.println("Błąd klienta: " + e.getMessage());
        }
    }
}
