package socket.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private final int port;
    private Context context;

    Server() {
        port = 5000;
        context = new Context();
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (!context.stopFlag) {
                System.out.println("Ожидание подключения к порту: " + port);
                Socket clientSocket = serverSocket.accept();
                System.out.println("Новый клиент подключен");

                ClientSession clientSession = new ClientSession(clientSocket, context);
                context.getSessionsManager().addSession(clientSession);
                clientSession.start();
            }

            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
