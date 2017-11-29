package socket.core.requests;

import socket.core.Request;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
//Запросы
public class StringRequest extends Request {
    private static String stringRequest;

    public StringRequest() {

    }

    public static String getStringRequest() {
        return stringRequest;
    }

    public StringRequest(String stringRequest) {
        StringRequest.stringRequest = stringRequest;
    }

    @Override
    public void writeExternal(DataOutputStream dos) throws IOException {
        dos.writeUTF(stringRequest);
    }

    @Override
    public void readExternal(DataInputStream dis) throws IOException {
        stringRequest = dis.readUTF();
    }
}
