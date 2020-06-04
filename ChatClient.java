package Lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    private final String SERVER_ADDR = "localhost" ;
    private final int SERVER_PORT = 8000 ;
       private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    Scanner scanner = new Scanner(System.in);
    String strFromServer = "";
    public ChatClient() {
        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openConnection() throws IOException {
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        // Прослушивание входящих
        new Thread( new Runnable() {
            @Override
            public void run() {
                System.out.println("Клиент слушает.");
                try {
                    while (true) {
                         strFromServer = in.readUTF();
                        if (strFromServer.equals("end")) {
                            System.out.println("Соединение с сервером потеряно.");
                            closeConnection();
                            break;
                        }
                        System.out.println("\nСервер: " + strFromServer);
                        System.out.print("Пользователь: ");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // Отправка сообщений
        new Thread( new Runnable() {
            @Override
            public void run() {
                System.out.println("Клиент готов отправлять сообщения.");
                try {
                    while (!strFromServer.equals("end")) {
                        System.out.print("Пользователь: ");
                        sendMessage();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    public void closeConnection() {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        scanner.close();
    }

    public void sendMessage() {
            try {
                String msg = scanner.nextLine();
                out.writeUTF(msg);
                if (msg.equals("end")) {
                    System.out.println("Соединение с сервером потеряно.");
                    closeConnection();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Ошибка отправки сообщения.");
            }
    }

    public static void main(String[] args) {
        ChatClient chatClient = new ChatClient();
    }
}

