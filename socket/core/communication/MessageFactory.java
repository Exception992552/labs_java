package socket.core.communication;

import socket.core.IMessage;
import socket.core.requests.HandshakeRequest;
import socket.core.requests.StringRequest;
import socket.core.responses.HandshakeResponse;
import socket.core.responses.StringResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class MessageFactory {
    /**
     * Идентификаторы запросов
     */
    private static final int REQUEST_HANDSHAKE = 1;
    private static final int REQUEST_STRING = 2;

    /**
     * ID ответов
     */
    private static final int RESPONSE_BASE = 0x40_000_000;
    private static final int RESPONSE_HANDSHAKE = RESPONSE_BASE + 1;
    private static final int RESPONSE_STRING = RESPONSE_BASE + 2;

    private static final Map<Class<? extends IMessage>, Integer> idMap = new HashMap<>();

    static {
        idMap.put(HandshakeRequest.class, REQUEST_HANDSHAKE);
        idMap.put(HandshakeResponse.class, RESPONSE_HANDSHAKE);
        idMap.put(StringRequest.class, REQUEST_STRING);
        idMap.put(StringResponse.class, RESPONSE_STRING);
    }

    private MessageFactory(){

    }

    static IMessage createMessage(int messageId) throws IOException {
        if (messageId > RESPONSE_BASE) {
            switch (messageId) {
                case RESPONSE_HANDSHAKE:
                    return new HandshakeResponse();

                case RESPONSE_STRING:
                    System.out.println("RESPONSE_STRING = " + RESPONSE_STRING);
                    return new StringResponse();

                default:
                    throw new IOException("Unknown message type " + messageId);
            }
        } else {
            switch (messageId) {
                case REQUEST_HANDSHAKE:
                    return new HandshakeRequest();

                case REQUEST_STRING:
                    System.out.println("REQUEST_STRING = " + REQUEST_STRING);
                    return new StringRequest();

                default:
                    throw new IOException("Unknown message type " + messageId);
            }
        }
    }

    static int getMessageId(final IMessage message) {
        return idMap.get(message.getClass());
    }
}
