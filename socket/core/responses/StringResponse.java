package socket.core.responses;

import socket.core.Response;
import socket.core.requests.StringRequest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
// Ответы
public class StringResponse extends Response {

    private static String stringResponse;

    public StringResponse() {
        stringResponse = StringRequest.getStringRequest();
    }

    @Override
    public void writeExternal(DataOutputStream dos) throws IOException {
        dos.writeUTF(stringResponse);
    }

    @Override
    public void readExternal(DataInputStream dis) throws IOException {
        stringResponse = dis.readUTF();
    }
}
