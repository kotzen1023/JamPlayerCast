package com.seventhmoon.jamplayercast.nanohttpd.protocols.http.tempfiles;

import com.seventhmoon.jamplayercast.nanohttpd.protocols.http.NanoHTTPD;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class DefaultTempFileManager implements ITempFileManager {
    private final File tmpdir;

    private final List<ITempFile> tempFiles;

    public DefaultTempFileManager() {
        this.tmpdir = new File(System.getProperty("java.io.tmpdir"));
        if (!tmpdir.exists()) {
            tmpdir.mkdirs();
        }
        this.tempFiles = new ArrayList<ITempFile>();
    }

    @Override
    public void clear() {
        for (ITempFile file : this.tempFiles) {
            try {
                file.delete();
            } catch (Exception ignored) {
                NanoHTTPD.LOG.log(Level.WARNING, "could not delete file ", ignored);
            }
        }
        this.tempFiles.clear();
    }

    @Override
    public ITempFile createTempFile(String filename_hint) throws Exception {
        DefaultTempFile tempFile = new DefaultTempFile(this.tmpdir);
        this.tempFiles.add(tempFile);
        return tempFile;
    }
}
