package com.seventhmoon.jamplayercast.nanohttpd.protocols.http.sockets;

import com.seventhmoon.jamplayercast.nanohttpd.util.IFactoryThrowing;

import java.io.IOException;
import java.net.ServerSocket;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

public class SecureServerSocketFactory implements IFactoryThrowing<ServerSocket, IOException> {
    private SSLServerSocketFactory sslServerSocketFactory;

    private String[] sslProtocols;

    public SecureServerSocketFactory(SSLServerSocketFactory sslServerSocketFactory, String[] sslProtocols) {
        this.sslServerSocketFactory = sslServerSocketFactory;
        this.sslProtocols = sslProtocols;
    }

    @Override
    public ServerSocket create() throws IOException {
        SSLServerSocket ss = null;
        ss = (SSLServerSocket) this.sslServerSocketFactory.createServerSocket();
        if (this.sslProtocols != null) {
            ss.setEnabledProtocols(this.sslProtocols);
        } else {
            ss.setEnabledProtocols(ss.getSupportedProtocols());
        }
        ss.setUseClientMode(false);
        ss.setWantClientAuth(false);
        ss.setNeedClientAuth(false);
        return ss;
    }
}
