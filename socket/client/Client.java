package socket.client;

import socket.core.UniqueMessage;
import socket.core.communication.MessageReader;
import socket.core.communication.MessageWriter;
import socket.core.requests.HandshakeRequest;
import socket.core.requests.StringRequest;
import socket.core.responses.HandshakeResponse;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

class Client {
    private final InetAddress host;
    private final int port;

    Client(InetAddress host, int port) {
        this.host = host;
        this.port = port;
    }

    void start() {
        try (
                Socket socket = new Socket(host, port)
                ){
            socket.setSoTimeout(100_000);
            MessageReader reader = new MessageReader(socket.getInputStream());
            MessageWriter writer = new MessageWriter(socket.getOutputStream());

            writer.writeRequest(new HandshakeRequest());
            UniqueMessage msg = reader.readMessage();
            if (!(msg.message instanceof HandshakeResponse)) {
                return;
            }

            logicStart(socket, reader, writer);
        } catch (IOException e) {
                    e.printStackTrace();
        }
    }

    private void logicStart(Socket socket, MessageReader reader, MessageWriter writer) {
        try {
            writer.writeRequest(new StringRequest("Hello"));
            UniqueMessage message = reader.readMessage();
            DataOutputStream dataOutputStream = new DataOutputStream(System.out);
            message.message.writeExternal(dataOutputStream);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
