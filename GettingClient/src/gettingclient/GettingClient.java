/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gettingclient;
import java.net.*;
import java.io.*;
import java.util.Scanner;

/**                                                                              
 *
 * @author lab-2
 */
public class GettingClient {

  
  public static void main(String [] args) throws UnknownHostException, IOException {
      

      String serverName = "127.0.0.1";
      int port = 7777;
     
         System.out.println("Connecting to " + serverName + " on port " + port);
         Socket client = new Socket(serverName, port);
         Thread sendMsg=new Thread(new SendMsg(client));
         sendMsg.start();
         System.out.println("Just connected to " + client.getRemoteSocketAddress());
       while (true){ 
        try {
         
        InputStream inFromServer = client.getInputStream();
         DataInputStream in = new DataInputStream(inFromServer);
         
        System.out.println("Server says " + in.readUTF());
      }catch(IOException e) {
         e.printStackTrace();
      }
      }
   }
}
