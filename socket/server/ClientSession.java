package socket.server;

import socket.core.UniqueMessage;
import socket.core.communication.MessageReader;
import socket.core.communication.MessageWriter;
import socket.core.requests.HandshakeRequest;
import socket.core.responses.HandshakeResponse;
import socket.core.responses.StringResponse;

import java.io.IOException;
import java.net.Socket;

public class ClientSession extends Thread {
    private final Socket socket;
    private final MessageReader reader;
    private final MessageWriter writer;
    private final Context context;

    ClientSession(final Socket socket, final Context context) throws IOException {
        this.socket = socket;
        this.reader = new MessageReader(socket.getInputStream());
        this.writer = new MessageWriter(socket.getOutputStream());
        this.context = context;
    }

    @Override
    public void run() {
        UniqueMessage msg;
        try {
            msg = reader.readMessage();

            if (msg.message instanceof HandshakeRequest) {
                if (((HandshakeRequest)msg.message).match()) {
                    writer.writeResponse(new HandshakeResponse(), msg.uniqueId);
                }
            } 
            doWork();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doWork() {
        UniqueMessage message;
        try {
            message = reader.readMessage();
            writer.writeResponse(new StringResponse(), message.uniqueId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
