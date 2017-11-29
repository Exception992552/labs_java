package socket.core;

public class UniqueMessage {
    public final IMessage message;
    public final int uniqueId;

    public UniqueMessage(IMessage message, int uniqueId) {
        this.message = message;
        this.uniqueId = uniqueId;
    }
}
