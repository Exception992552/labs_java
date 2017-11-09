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
      Scanner  sc = new Scanner(System.in);

      String serverName = "127.0.0.1";
      int port = 7777;
     
         System.out.println("Connecting to " + serverName + " on port " + port);
         Socket client = new Socket(serverName, port);
         
         System.out.println("Just connected to " + client.getRemoteSocketAddress());
        while (true){ 
        try {
          String firstname = sc.nextLine();
          String lastname = sc.nextLine();
          String nickname = sc.nextLine();
          String marks = sc.nextLine();
          String xml="<?xml version = \"1.0\"?>"+
        "<class>"+
        "  <student rollno = \"393\">"+
        "    <firstname>\""+firstname+"\"</firstname>"+
        "   <lastname>\""+lastname+"\"</lastname>"+
        "  <nickname>\""+nickname+"\"</nickname>"+
        " <marks>\""+marks+"\"</marks>"+
        "</student>"+
        "</class>";
         OutputStream outToServer = client.getOutputStream();
         DataOutputStream out = new DataOutputStream(outToServer);
         
         out.writeUTF(xml);
         InputStream inFromServer = client.getInputStream();
         DataInputStream in = new DataInputStream(inFromServer);
         
         System.out.println("Server says " + in.readUTF());
      }catch(IOException e) {
         e.printStackTrace();
      }
      }
   }
}
