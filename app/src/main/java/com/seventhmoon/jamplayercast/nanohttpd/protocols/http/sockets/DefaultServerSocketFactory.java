package com.seventhmoon.jamplayercast.nanohttpd.protocols.http.sockets;

import com.seventhmoon.jamplayercast.nanohttpd.util.IFactoryThrowing;

import java.io.IOException;
import java.net.ServerSocket;

public class DefaultServerSocketFactory implements IFactoryThrowing<ServerSocket, IOException> {
    @Override
    public ServerSocket create() throws IOException {
        return new ServerSocket();
    }
}
