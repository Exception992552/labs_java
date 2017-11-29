    package omni.client;
     
    import java.io.IOException;
    import java.io.OutputStreamWriter;
    import java.net.Socket;
    import java.nio.charset.StandardCharsets;
    import java.util.HashMap;
    import java.util.Map;
    import org.apache.log4j.Logger;
    import org.boon.Boon;
    import omni.commons.core.Request;
    public class Client {
     private static final Logger log = Logger.getLogger(Client.class);
     private static final String host = "localhost";
     private static final int port = 9876;
     private static Request requestServer;
     private static Map<String, String> properties;
     
     public static void main(String[] args) {
     
      properties = new HashMap<>();
      properties.put("mockProperty1", "mockValue1");
      properties.put("mockProperty2", "mockValue2");
      properties.put("mockProperty3", "mockValue3");
     
      requestServer = new Request();
      requestServer.setCommandName("Mock Command");
      requestServer.setProperties(properties);
     
      String jsonRequest = Boon.toJson(requestServer);
     
      try (Socket socket = new Socket(host, port)) {
       try (OutputStreamWriter request = new OutputStreamWriter(socket.getOutputStream(),
         StandardCharsets.UTF_8)) {
        request.write(jsonRequest);
        request.flush();
       }
      } catch (IOException ioe) {
       log.error("Unable to send the request", ioe);
      }
     }
    }
