package socket.core.communication;

import socket.core.IMessage;
import socket.core.Request;
import socket.core.Response;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MessageWriter {
    private static final int INITIAL_BUFFER_SIZE = 128;

    private final DataOutputStream out;
    private Integer requestIdCounter = 0;

    public MessageWriter(OutputStream os) {
        out = new DataOutputStream(os);
    }

    private int getNewRequestId() {
        synchronized (requestIdCounter) {
            return ++requestIdCounter;
        }
    }

    private void writeMessage(final IMessage message, final int uniqueId) throws IOException {
        int messageId = MessageFactory.getMessageId(message);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(INITIAL_BUFFER_SIZE);
        message.writeExternal(new DataOutputStream(baos));
        int messageLength = baos.size() + MessageReader.HEADER_LENGTH;

        synchronized (out) {
            out.writeInt(messageLength);
            out.writeInt(uniqueId);
            out.writeInt(messageId);
            baos.writeTo(out);
            out.flush();
        }
        System.out.println("Message " + message.getClass().getName() + " sent.");
    }

    public void writeRequest(final Request request) throws IOException {
        int uniqueId = getNewRequestId();
        writeMessage(request, uniqueId);
    }

    public void writeResponse(final Response response, int requestId) throws IOException {
        writeMessage(response, requestId);
    }
}
