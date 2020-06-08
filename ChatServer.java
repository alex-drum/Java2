package Lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {
    static Socket socket = null;
    static String msg = "";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (ServerSocket serverSocket = new ServerSocket( 8000 )) {
            System.out.println( "Сервер запущен, ожидаем подключения..." );
            socket = serverSocket.accept();
            System.out.println( "Клиент подключился" );
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            // Прослушивание входящих
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Сервер слушает.");
                    while (!msg.equals("end")) {
                        try {
                            String str = in.readUTF();
                            System.out.println("\nПользователь: " + str);
                            System.out.print("Сервер: ");
                            if (str.equals("end")) {
                                System.out.println("Соединение с клиентом потеряно.");
                                scanner.close();
                                in.close();
                                out.close();
                                socket.close();
                                break;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

            // Отправка сообщений
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Сервер готов отправлять сообщения.");
                    while (true) {
                        try {
                        System.out.print("Сервер: ");
                        msg = scanner.nextLine();
                            out.writeUTF(msg);
                        if (msg.equals("end")) {
                            System.out.println("Соединение с клиентом потеряно.");
                            scanner.close();
                            in.close();
                            out.close();
                            socket.close();
                            break;
                        }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
