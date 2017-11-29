package lab4;

import java.io.*;
import java.net.Socket;

public class GreetingClient {
    public static void main(String[] args) {
        String serverName = "localhost";
        int port = 7777;
        try {
            System.out.println("Connecting to " + serverName + " on port " + port);
            Socket client = new Socket(serverName, port);
            System.out.println("Just connected to " + client.getRemoteSocketAddress());

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String line;
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            System.out.println("Type something here: ");
            while (true) {
                line = keyboard.readLine();
                System.out.println("Sending to server...");
                out.writeUTF(line);
                out.flush();
                line = in.readUTF();
                System.out.println("Server says: " + line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}