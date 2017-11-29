package Server;
import java.net.*;
import java.util.HashMap;
import java.io.*;

public class Server {


    public static void main(String[] ar)    {
        int port = 6666;
        HashMap<String,Socket> connectionList= new HashMap();
        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Waiting for a client...");

            while(true){
                Socket socket = ss.accept();
                InputStream sin = socket.getInputStream();
                DataInputStream in = new DataInputStream(sin);
                String name="";
                name = in.readUTF();

                System.out.println("Got a client named "+name);
                System.out.println();

                connectionList.put(name,socket);
                Thread newClient = new Thread(new MessageSender(socket, connectionList,name));
                newClient.start();
            }



        } catch(Exception x) {
            x.printStackTrace(); 

        }
    }
}

