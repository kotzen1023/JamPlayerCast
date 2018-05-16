package com.seventhmoon.jamplayercast.nanohttpd.protocols.http;

import com.seventhmoon.jamplayercast.nanohttpd.protocols.http.content.CookieHandler;
import com.seventhmoon.jamplayercast.nanohttpd.protocols.http.request.Method;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface IHTTPSession {
    void execute() throws IOException;

    CookieHandler getCookies();

    Map<String, String> getHeaders();

    InputStream getInputStream();

    Method getMethod();

    /**
     * This method will only return the first value for a given parameter. You
     * will want to use getParameters if you expect multiple values for a given
     * key.
     *
     * @deprecated use {@link #getParameters()} instead.
     */
    @Deprecated
    Map<String, String> getParms();

    Map<String, List<String>> getParameters();

    String getQueryParameterString();

    /**
     * @return the path part of the URL.
     */
    String getUri();

    /**
     * Adds the files in the request body to the files map.
     *
     * @param files
     *            map to modify
     */
    void parseBody(Map<String, String> files) throws IOException, NanoHTTPD.ResponseException;

    /**
     * Get the remote ip address of the requester.
     *
     * @return the IP address.
     */
    String getRemoteIpAddress();

    /**
     * Get the remote hostname of the requester.
     *
     * @return the hostname.
     */
    String getRemoteHostName();
}
