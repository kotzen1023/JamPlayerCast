package com.seventhmoon.jamplayercast.nanohttpd.webserver;

import com.seventhmoon.jamplayercast.nanohttpd.protocols.http.IHTTPSession;
import com.seventhmoon.jamplayercast.nanohttpd.protocols.http.response.Response;

import java.io.File;
import java.util.Map;

public interface WebServerPlugin {
    boolean canServeUri(String uri, File rootDir);

    void initialize(Map<String, String> commandLineOptions);

    Response serveFile(String uri, Map<String, String> headers, IHTTPSession session, File file, String mimeType);
}
