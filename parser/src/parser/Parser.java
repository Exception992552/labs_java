/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author lab-2
 */
public class Parser {
 private static final String FILENAME = "test.xml";
    static void  outXML() throws SAXException, IOException{
                try {
 
            // Строим объектную модель исходного XML файла
            final File xmlFile = new File(System.getProperty("user.dir")
                    + File.separator + "orders.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(xmlFile);
 
            // Выполнять нормализацию не обязательно, но рекомендуется
            doc.getDocumentElement().normalize();
 
            // Получаем все узлы с именем "staff"
            NodeList nodeList = doc.getElementsByTagName("orders");
 
            for (int i = 0; i < nodeList.getLength(); i++) {
                // Выводим информацию по каждому из найденных элементов
                Node node = nodeList.item(i);
                
                
                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    Element element = (Element) node;
                    System.out.println("Адрес: "
                            + element.getElementsByTagName("adress").item(0)
                            .getTextContent());
                    System.out.println("ФИО: " + element
                            .getElementsByTagName("FIO").item(0)
                            .getTextContent());
                    System.out.println("Телефон: " + element
                            .getElementsByTagName("tel").item(0)
                            .getTextContent());
                    System.out.println("Дата: " + element
                            .getElementsByTagName("date").item(0)
                            .getTextContent());
                    System.out.println("Корзина: " + element
                            .getElementsByTagName("bucket").item(0)
                            .getTextContent());
                    System.out.println("Сумма к оплате: " + element
                            .getElementsByTagName("summ").item(0)
                            .getTextContent());
                    System.out.println("\n");
                  
                }
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Parser.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
     public static void  gets(String tag) throws SAXException, IOException, ParserConfigurationException{   
          // ArrayList<String> nams = new ArrayList<>();
           final File xmlFile = new File(System.getProperty("user.dir")
                    + File.separator + FILENAME);
           
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
           Document doc = db.parse(xmlFile);
           
            NodeList nodeList = doc.getElementsByTagName("Pizza");
            //System.out.println("parser2");
            for (int i = 0; i < nodeList.getLength(); i++) {
                // Выводим информацию по каждому из найденных элементов
               // System.out.println("parser3");
                Node node = nodeList.item(i);
                if (Node.ELEMENT_NODE == node.getNodeType()) {
                  //  System.out.println("parser4");
                    Element element = (Element) node;
                    String str = element.getElementsByTagName(tag).item(0).getTextContent();
                    System.out.println(str);
                   // System.out.println(str);
                 //nams.add(str);
                 //for(String u : nams)
                     
                 //System.out.println(u);
                 //System.out.println("parser5");
                }
            }
           
          // return nams;

      }
     public static void main(String[] args){
        try {
            outXML();
        } catch (SAXException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            gets("Names");
        } catch (SAXException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
}
