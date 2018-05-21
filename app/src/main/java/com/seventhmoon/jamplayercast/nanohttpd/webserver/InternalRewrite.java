package com.seventhmoon.jamplayercast.nanohttpd.webserver;

import com.seventhmoon.jamplayercast.nanohttpd.protocols.http.NanoHTTPD;
import com.seventhmoon.jamplayercast.nanohttpd.protocols.http.response.Response;
import com.seventhmoon.jamplayercast.nanohttpd.protocols.http.response.Status;

import java.io.ByteArrayInputStream;
import java.util.Map;

public class InternalRewrite extends Response {
    private final String uri;

    private final Map<String, String> headers;

    public InternalRewrite(Map<String, String> headers, String uri) {
        super(Status.OK, NanoHTTPD.MIME_HTML, new ByteArrayInputStream(new byte[0]), 0);
        this.headers = headers;
        this.uri = uri;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public String getUri() {
        return this.uri;
    }
}
