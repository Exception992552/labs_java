package Client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Listener extends Thread{

    DataInputStream in;
    public Listener(Socket clientSocket){
        try {
            in = new DataInputStream(clientSocket.getInputStream());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
       try{

        String message;
        while ((message = in.readUTF()) != null){
            System.out.println(message);
        }
       }
        catch (Exception e){
           e.printStackTrace();
        }
    }
}
