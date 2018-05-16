package com.seventhmoon.jamplayercast.nanohttpd.protocols.http.tempfiles;

import com.seventhmoon.jamplayercast.nanohttpd.util.IFactory;

public class DefaultTempFileManagerFactory implements IFactory<ITempFileManager> {
    @Override
    public ITempFileManager create() {
        return new DefaultTempFileManager();
    }
}
