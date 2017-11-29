    package omni.commons.core;
     
    import java.util.Map;
     
    import org.boon.json.JsonFactory;
    import org.boon.json.ObjectMapper;
     
    public class Request {
     
     private String commandName;
     private Map<String, String> properties;
     
     public String getCommandName() {
      return commandName;
     }
     
     public void setCommandName(String commandName) {
      this.commandName = commandName;
     }
     
     public Map getProperties() {
      return properties;
     }
     public void setProperties(Map<String, String> properties) {
      this.properties = properties;
     }
     @Override
     public String toString() {
      ObjectMapper mapper = JsonFactory.create();
      String formattedProperties = mapper.writeValueAsString(properties);
      return "Request [commandName=" + commandName + ", properties=" + formattedProperties + "]";
     }
    }
