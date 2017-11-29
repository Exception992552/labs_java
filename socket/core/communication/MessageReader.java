package socket.core.communication;

import socket.core.IMessage;
import socket.core.UniqueMessage;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MessageReader {
    static  final int HEADER_LENGTH = 12;

    private final DataInputStream dis;

    public MessageReader(InputStream is) {
        dis = new DataInputStream(is);
    }

    public UniqueMessage readMessage() throws IOException {
        int packageLength = dis.readInt();
        if (packageLength < HEADER_LENGTH) {
            throw new IOException("Wrong package length");
        }

        byte[] buff = new byte[packageLength - 4];
        dis.readFully(buff);

        DataInputStream messageIS = new DataInputStream(new ByteArrayInputStream(buff));

        int uniqueId = messageIS.readInt();
        int messageId = messageIS.readInt();

        IMessage message = MessageFactory.createMessage(messageId);

        message.readExternal(messageIS);
        System.out.println("Message " + message.getClass().getName());
        return new UniqueMessage(message, uniqueId);
    }
}
