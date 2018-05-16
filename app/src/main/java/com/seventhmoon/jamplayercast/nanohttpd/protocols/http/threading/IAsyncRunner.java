package com.seventhmoon.jamplayercast.nanohttpd.protocols.http.threading;

import com.seventhmoon.jamplayercast.nanohttpd.protocols.http.ClientHandler;

public interface IAsyncRunner {
    void closeAll();

    void closed(ClientHandler clientHandler);

    void exec(ClientHandler code);
}
