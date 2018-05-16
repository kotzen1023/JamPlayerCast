package com.seventhmoon.jamplayercast.nanohttpd.protocols.http.tempfiles;

import com.seventhmoon.jamplayercast.nanohttpd.protocols.http.NanoHTTPD;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DefaultTempFile implements ITempFile {
    private final File file;

    private final OutputStream fstream;

    public DefaultTempFile(File tempdir) throws IOException {
        this.file = File.createTempFile("NanoHTTPD-", "", tempdir);
        this.fstream = new FileOutputStream(this.file);
    }

    @Override
    public void delete() throws Exception {
        NanoHTTPD.safeClose(this.fstream);
        if (!this.file.delete()) {
            throw new Exception("could not delete temporary file: " + this.file.getAbsolutePath());
        }
    }

    @Override
    public String getName() {
        return this.file.getAbsolutePath();
    }

    @Override
    public OutputStream open() throws Exception {
        return this.fstream;
    }
}
