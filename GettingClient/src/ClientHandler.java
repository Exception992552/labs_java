
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lab-2
 */
public class ClientHandler implements Runnable{
    private Socket mySocket;
    public List<Socket> clients=new ArrayList<Socket>();
    
    public ClientHandler(){
    }
    public ClientHandler(Socket socket){
        this.mySocket=socket;
    }
    public ClientHandler(Socket socket, List<Socket> clients){
        this.mySocket=socket;
        this.clients=new ArrayList<Socket>(clients);
    }
    
    public List<?>getClients(){
    return this.clients;
    }
    public void setClients(List<Socket> clients){
    this.clients=new ArrayList<Socket>(clients);
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
    

    @Override
    public void run() {
    while(true){
            try {
                
                DataInputStream in = new DataInputStream(this.mySocket.getInputStream());
                String s = in.readUTF();
               for(int i=0; i<clients.size();i++){
                   Socket temp;
                   temp = this.clients.get(i);
                   if(temp!=this.mySocket){
         System.out.println("Others");
         DataOutputStream out = new DataOutputStream(temp.getOutputStream());
                out.writeUTF(XmlToString(s)+"\n");
                  }
                    
               }
                       DataOutputStream out = new DataOutputStream(this.mySocket.getOutputStream());
                out.writeUTF(XmlToString(s)+"\n");
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            } 
            }    
    }
}
