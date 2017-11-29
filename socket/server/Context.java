package socket.server;

class Context {
    private SessionManager sessionManager;
    boolean stopFlag;

    Context() {
        stopFlag = false;
        sessionManager = new SessionManager();
    }

    SessionManager getSessionsManager() {
        return sessionManager;
    }
}
