package lab4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class GreetingServer extends Thread {

    public static void main(String[] args) throws IOException {
        int port = 7777;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try {
                    System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
                    Socket server = serverSocket.accept();

                    System.out.println("Just connected to " + server.getRemoteSocketAddress());
                    // DataInputStream in = new DataInputStream(server.getInputStream());
                    System.out.println();

                    InputStream sin = server.getInputStream();
                    OutputStream sout = server.getOutputStream();

                    DataInputStream in = new DataInputStream(sin);
                    DataOutputStream out = new DataOutputStream(sout);

                    String line = null;
                    while (true) {
                        line = in.readUTF();
                        System.out.println("Client just sent: " + line);
                        System.out.println("Sending it back...");
                        out.writeUTF(line);
                        out.flush();
                        System.out.println("Waiting for the next line...");
                        System.out.println();
                    }
                } catch (SocketTimeoutException s) {
                    System.out.println("Socket timed out!");
                    break;
                }
            }
        }
    }
}
