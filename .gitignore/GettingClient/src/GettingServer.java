/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lab-2
 */
import java.net.*;
import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
public class GettingServer extends Thread {
    private ServerSocket serverSocket;

 
   public GettingServer(int port) throws IOException {
      serverSocket = new ServerSocket(port);
     // serverSocket.setSoTimeout(10000);
   }
   
   public String XmlToString(String xmlData)
   {String ansver="Error";
   try {
ansver="";
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         StringReader sr = new StringReader(xmlData);
         InputSource is=new InputSource();
        is.setCharacterStream(sr);
         Document doc;
           doc = dBuilder.parse(is);
         doc.getDocumentElement().normalize();
         System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
         NodeList nList = doc.getElementsByTagName("student");
         System.out.println("----------------------------");
         
         for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               ansver+="Student roll no : " 
                  + eElement.getAttribute("rollno");
               ansver+="First Name : " 
                  + eElement
                  .getElementsByTagName("firstname")
                  .item(0)
                  .getTextContent();
               ansver+="Last Name : " 
                  + eElement
                  .getElementsByTagName("lastname")
                  .item(0)
                  .getTextContent();
               ansver+="Nick Name : " 
                  + eElement
                  .getElementsByTagName("nickname")
                  .item(0)
                  .getTextContent();
               ansver+="Marks : " 
                  + eElement
                  .getElementsByTagName("marks")
                  .item(0)
                  .getTextContent();
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   return ansver;
       
   }

   public void run() {
      while(true) {
         try {
            System.out.println("Waiting for client on port " + 
               serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            
            System.out.println("Just connected to " + server.getRemoteSocketAddress());
            while(true){
            DataInputStream in = new DataInputStream(server.getInputStream());
            String s = in.readUTF();
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            out.writeUTF(XmlToString(s)+"\n");
            }
            
         }catch(SocketTimeoutException s) {
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e) {
            e.printStackTrace();
            break;
         }
      }
   }
   
   public static void main(String [] args) {
      int port = 7777;
      try {
         Thread t = new GettingServer(port);
         t.start();
      }catch(IOException e) {
         e.printStackTrace();
      }
   }
    
}
