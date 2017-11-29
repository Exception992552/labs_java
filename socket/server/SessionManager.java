package socket.server;

import java.util.HashSet;
import java.util.Set;

public class SessionManager {
    private final Set<ClientSession> sessionSet = new HashSet<>();

    synchronized void addSession(ClientSession session) {
        sessionSet.add(session);
    }

    public synchronized void removeSession(ClientSession session) {
        sessionSet.remove(session);
    }
}
