package com.seventhmoon.jamplayercast.nanohttpd.protocols.http.tempfiles;

public interface ITempFileManager {
    void clear();

    public ITempFile createTempFile(String filename_hint) throws Exception;
}
