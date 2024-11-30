import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerTCP {
    public static final String RESULTS_FILE = "C:\\Users\\imjus\\Desktop\\Semestr5\\JAVA\\lab\\Server2\\src\\wyniki.txt";
    private static final String QUESTIONS_FILE = "C:\\Users\\imjus\\Desktop\\Semestr5\\JAVA\\lab\\Server2\\src\\bazaPytan.txt";
    public static final String ANSWERS_FILE = "C:\\Users\\imjus\\Desktop\\Semestr5\\JAVA\\lab\\Server2\\src\\bazaOdpowiedzi.txt";

    private static final int PORT = 1234;
    private static final int MAX_CLIENTS = 250;
    private static final List<String> questions = new ArrayList<>();
    private static final List<String[]> possibleAnswers = new ArrayList<>();
    private static final List<String> correctAnswers = new ArrayList<>();

    public static void main(String[] args) {
        loadQuestions();
        loadAnswers();
        ExecutorService threadPool = Executors.newFixedThreadPool(MAX_CLIENTS);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Serwer uruchomiony, nasłuchuje na porcie: " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nowe połączenie od: " + clientSocket.getInetAddress());
                threadPool.execute(new ServerTCPThread(clientSocket, questions, possibleAnswers, correctAnswers));
            }
        } catch (IOException e) {
            System.err.println("Błąd serwera: " + e.getMessage());
        } finally {
            threadPool.shutdown();
        }
    }

    private static void loadQuestions() {
        try (BufferedReader reader = new BufferedReader(new FileReader(QUESTIONS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String question = line;
                questions.add(question);
                String[] answers = new String[4];
                for (int i = 0; i < 4; i++) {
                    answers[i] = reader.readLine();  // Wczytanie odpowiedzi
                }
                possibleAnswers.add(answers);
                reader.readLine();  // Przechodzi do następnego pytania
            }
        } catch (IOException e) {
            System.err.println("Błąd podczas wczytywania pytań: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void loadAnswers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ANSWERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                correctAnswers.add(line.trim());
            }
        } catch (IOException e) {
            System.err.println("Błąd podczas wczytywania odpowiedzi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

