package socket.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface IMessage {
    void writeExternal(DataOutputStream dos) throws IOException;
    void readExternal(DataInputStream dis) throws IOException;
}
