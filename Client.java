package lesson8;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    static Socket socket = null;
    private static final int TIMEOUT = 3000;
    static long startTime;
    static long durationTime;
    static long currentTime;

    public static void setAuthorized(boolean authorized) {
        Client.authorized = authorized;
    }

    static boolean authorized;

    public static void main(String[] args) throws IOException {
//        Socket socket = null;
        socket = new Socket("localhost", 8189);
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
//            Scanner sc = new Scanner(System.in);
//            System.out.println("Введите логин и пароль через пробел");
//            String logAndPass = sc.nextLine();
//            System.out.println(logAndPass);
//            String authMsg = "/auth " + logAndPass;
//            System.out.println(authMsg);
//            out.writeUTF(authMsg);
//            out.writeUTF("/auth login1 pass1");
            setAuthorized(false);
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    startTime = System.currentTimeMillis();
                    try {
                        while (durationTime < TIMEOUT) {
                            currentTime = System.currentTimeMillis();
                            durationTime = currentTime - startTime;
                            if (durationTime >= TIMEOUT && in.available() == 0) {
                                System.out.println("TIME OUT");
                                out.writeUTF("/timeOut");
                            } else if(in.available()>0) {
                                String strFromServer = in.readUTF();
                                currentTime = System.currentTimeMillis();
                                durationTime = currentTime - startTime;
                                System.out.println(durationTime);
                                if (durationTime < TIMEOUT) {
                                    if (strFromServer.startsWith("/authOk")) {
                                        setAuthorized(true);
                                        System.out.println("Authorized on server");
                                        Client.runOutputThread(out);
                                        break;
                                    }
                                System.out.println(strFromServer + "\n");
                                }
                            }
                        }

                        while (true) {
                            if (in.available()>0) {
                                String strFromServer = in.readUTF();
                                if (strFromServer.equalsIgnoreCase("/end")) {
                                    break;
                                }
                                System.out.println(strFromServer);
                                System.out.println("\n");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
            t.join();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }

    private static Thread runOutputThread(DataOutputStream out) {
        Thread thread = new Thread(()-> {
            while (!Thread.currentThread().isInterrupted()) {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    String message = scanner.nextLine();
                    try {
                        out.writeUTF(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (message.equals("/end")) {
                        break;
                    }
                }
            }
        });
        thread.start();
        return thread;
    }
}
