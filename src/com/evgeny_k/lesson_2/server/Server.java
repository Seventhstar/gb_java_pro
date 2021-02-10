package  com.evgeny_k.lesson_2.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private static final int SERVER_PORT = 1234;

    public static void main(String[] args) {
        new Server();
    }

    public Server() {
        Socket socket = null;
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Сервер запущен! Жду клиента...");

            socket = serverSocket.accept();
            System.out.println("Климент подключился.");
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Scanner fromSocket = new Scanner(socket.getInputStream());
            new Thread(() -> {
                while (true) {
                    String message = fromSocket.nextLine();
                    if (message.equals("/q")) break;
                    System.out.println("Клиент: " + message);
                }
            }).start();

            Scanner fromConsole = new Scanner(System.in);
            while (true) {
                String message = fromConsole.nextLine();
                System.out.println("Сообщение отправлено.");
                out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
