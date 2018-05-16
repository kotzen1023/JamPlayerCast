package com.seventhmoon.jamplayercast.nanohttpd.protocols.http.tempfiles;

import java.io.OutputStream;

public interface ITempFile {
    public void delete() throws Exception;

    public String getName();

    public OutputStream open() throws Exception;
}
