import java.io.*;
import java.net.Socket;
import java.util.List;

public class ServerTCPThread implements Runnable {

    private static final Object FILE_LOCK = new Object();
    private final Socket clientSocket;
    private final List<String> questions;
    private final List<String[]> possibleAnswers;
    private final List<String> correctAnswers;

    public ServerTCPThread(Socket clientSocket, List<String> questions, List<String[]> possibleAnswers, List<String> correctAnswers) {
        this.clientSocket = clientSocket;
        this.questions = questions;
        this.possibleAnswers = possibleAnswers;
        this.correctAnswers = correctAnswers;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            int score = 0;
            StringBuilder answers = new StringBuilder();
            for (int i = 0; i < questions.size(); i++) {
                String question = questions.get(i);
                String[] answersOptions = possibleAnswers.get(i);

                out.println(question);
                for (int j = 0; j < answersOptions.length; j++) {
                    out.println((char) ('A' + j) + ". " + answersOptions[j]);
                }
                String clientAnswer = in.readLine().trim();
                answers.append(clientAnswer).append(", ");

                if (clientAnswer.equalsIgnoreCase(correctAnswers.get(i))) {
                    score++;
                }
            }
            File resultFile = new File(ServerTCP.RESULTS_FILE);
            if (!resultFile.exists()) {
                try {
                    resultFile.createNewFile();
                } catch (IOException e) {
                    System.err.println("Błąd przy tworzeniu pliku wyników: " + e.getMessage());
                }
            }
            synchronized (FILE_LOCK) {
                try (FileWriter resultWriter = new FileWriter(ServerTCP.RESULTS_FILE, true)) {
                    resultWriter.write("Klient: " + clientSocket.getInetAddress() + " " + clientSocket.getPort() +
                            " Wynik: " + score + " Odpowiedzi: " + answers.toString() + "\n");
                } catch (IOException e) {
                    System.err.println("Błąd zapisu wyniku do pliku: " + e.getMessage());
                }
            }
            out.println("Twój wynik: " + score + " z " + questions.size());
        } catch (IOException e) {
            System.err.println("Błąd przy obsłudze klienta: " + e.getMessage());
        }
    }
}