package com.seventhmoon.jamplayercast.nanohttpd.protocols.http.threading;

import com.seventhmoon.jamplayercast.nanohttpd.protocols.http.ClientHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultAsyncRunner implements IAsyncRunner {
    protected long requestCount;

    private final List<ClientHandler> running = Collections.synchronizedList(new ArrayList<ClientHandler>());

    /**
     * @return a list with currently running clients.
     */
    public List<ClientHandler> getRunning() {
        return running;
    }

    @Override
    public void closeAll() {
        // copy of the list for concurrency
        for (ClientHandler clientHandler : new ArrayList<ClientHandler>(this.running)) {
            clientHandler.close();
        }
    }

    @Override
    public void closed(ClientHandler clientHandler) {
        this.running.remove(clientHandler);
    }

    @Override
    public void exec(ClientHandler clientHandler) {
        ++this.requestCount;
        this.running.add(clientHandler);
        createThread(clientHandler).start();
    }

    protected Thread createThread(ClientHandler clientHandler) {
        Thread t = new Thread(clientHandler);
        t.setDaemon(true);
        t.setName("NanoHttpd Request Processor (#" + this.requestCount + ")");
        return t;
    }
}
