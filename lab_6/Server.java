    package omni.server;
     
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.net.ServerSocket;
    import java.net.Socket;
     
    import org.apache.log4j.Logger;
    import org.boon.json.JsonFactory;
    import org.boon.json.ObjectMapper;
     
    import omni.commons.core.Request;
     
    public class Server {
     
     private static final Logger log = Logger.getLogger(Server.class);
     
     private static final int port = 9876;
     private static Socket socket;
     private static BufferedReader input;
     private static String request;
     private static ObjectMapper mapper;
     private static Request requestObject;
     
     public static void main(String[] args) {
     
      try (ServerSocket serverSocket = new ServerSocket(port)) {
       log.info("Server started and listening at port : " + port);
       socket = serverSocket.accept();
       input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
       request = input.readLine();
     
       log.info("JSON Request " + request);
     
       mapper = JsonFactory.create();
       requestObject = mapper.readValue(request, Request.class);
     
       log.info("Request from client : " + requestObject.toString());
      } catch (IOException ioe) {
       log.error(ioe);
      }
     }
    }
