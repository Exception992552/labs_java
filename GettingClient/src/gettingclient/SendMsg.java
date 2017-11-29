/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gettingclient;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lab-2
 */
public class SendMsg implements Runnable{
    Socket mySocket;
    public SendMsg(){}
    public SendMsg(Socket mySocket){
    this.mySocket=mySocket;
    }
public void setMsg(String msg){
    
}
    @Override
    public void run() {
        while(true){
        try {
            Scanner  sc = new Scanner(System.in);
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
            OutputStream outToServer  = mySocket.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            out.writeUTF(xml);
            
        } catch (IOException ex) {
            Logger.getLogger(SendMsg.class.getName()).log(Level.SEVERE, null, ex);
        } 
        }
    }
    
}
