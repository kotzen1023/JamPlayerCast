package com.seventhmoon.jamplayercast.nanohttpd.nanolets.test;

import android.os.Environment;
import android.util.Log;

import com.seventhmoon.jamplayercast.nanohttpd.nanolets.main.RouterNanoHTTPD;
import com.seventhmoon.jamplayercast.nanohttpd.protocols.http.IHTTPSession;
import com.seventhmoon.jamplayercast.nanohttpd.protocols.http.response.IStatus;
import com.seventhmoon.jamplayercast.nanohttpd.protocols.http.response.Response;
import com.seventhmoon.jamplayercast.nanohttpd.protocols.http.response.Status;
import com.seventhmoon.jamplayercast.nanohttpd.util.ServerRunner;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class AppNanolets extends RouterNanoHTTPD {
    private static final String TAG = AppNanolets.class.getName();
    private static final int PORT = 9090;

    public static class UserHandler extends DefaultHandler {

        @Override
        public String getText() {
            return "not implemented";
        }

        public String getText(Map<String, String> urlParams, IHTTPSession session) {
            String text = "<html><body>User handler. Method: " + session.getMethod().toString() + "<br>";
            text += "<h1>Uri parameters:</h1>";
            for (Map.Entry<String, String> entry : urlParams.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                text += "<div> Param: " + key + "&nbsp;Value: " + value + "</div>";
            }
            text += "<h1>Query parameters:</h1>";
            for (Map.Entry<String, String> entry : session.getParms().entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                text += "<div> Query Param: " + key + "&nbsp;Value: " + value + "</div>";
            }
            text += "</body></html>";

            return text;
        }

        @Override
        public String getMimeType() {
            return "text/html";
        }

        @Override
        public IStatus getStatus() {
            return Status.OK;
        }

        public Response get(UriResource uriResource, Map<String, String> urlParams, IHTTPSession session) {
            String text = getText(urlParams, session);
            ByteArrayInputStream inp = new ByteArrayInputStream(text.getBytes());
            int size = text.getBytes().length;
            return Response.newFixedLengthResponse(getStatus(), getMimeType(), inp, size);
        }

    }

    static public class StreamUrl extends DefaultStreamHandler {

        @Override
        public String getMimeType() {
            return "text/plain";
        }

        @Override
        public IStatus getStatus() {
            return Status.OK;
        }

        @Override
        public InputStream getData() {
            return new ByteArrayInputStream("a stream of data ;-)".getBytes());
        }

    }

    static public class VideoStreamUrl extends DefaultStreamHandler {



        @Override
        public String getMimeType() {
            return "video/mp4";
        }

        @Override
        public IStatus getStatus() {
            return Status.OK;
        }

        @Override
        public InputStream getData() {
            File RootDirectory = new File("/");
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                //path = Environment.getExternalStorageDirectory();
                RootDirectory = Environment.getExternalStorageDirectory();
                Log.d(TAG, "path = "+RootDirectory.getAbsolutePath());
            }

            String path = RootDirectory+"/Download/test_1m.mp4";
            File file = new File(path);
            FileInputStream fileInputStream=null;
            try {
                fileInputStream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            return fileInputStream;
        }



    }

    public static class StaticPageTestHandler extends StaticPageHandler {

        @Override
        protected BufferedInputStream fileToInputStream(File fileOrdirectory) throws IOException {

            if ("exception.html".equals(fileOrdirectory.getName())) {
                throw new IOException("trigger something wrong");
            }
            return super.fileToInputStream(fileOrdirectory);
        }
    }

    /**
     * Create the server instance
     */
    public AppNanolets() throws IOException {
        super(PORT);
        addMappings();
        System.out.println("\nRunning! Point your browers to http://localhost:" + PORT + "/ \n");
    }

    /**
     * Add the routes Every route is an absolute path Parameters starts with ":"
     * Handler class should implement @UriResponder interface If the handler not
     * implement UriResponder interface - toString() is used
     */
    @Override
    public void addMappings() {

        File RootDirectory = new File("/");
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //path = Environment.getExternalStorageDirectory();
            RootDirectory = Environment.getExternalStorageDirectory();
            Log.d(TAG, "path = "+RootDirectory.getAbsolutePath());
        }

        super.addMappings();
        addRoute("/user", UserHandler.class);
        addRoute("/user", UserHandler.class); // add it twice to execute the
        // priority == priority case
        addRoute("/user/help", GeneralHandler.class);
        addRoute("/user/:id", UserHandler.class);
        addRoute("/general/:param1/:param2", GeneralHandler.class);
        addRoute("/photos/:customer_id/:photo_id", null);
        addRoute("/test", String.class);
        addRoute("/interface", UriResponder.class); // this will cause an error
        // when called
        addRoute("/toBeDeleted", String.class);
        removeRoute("/toBeDeleted");
        addRoute("/stream", StreamUrl.class);
        addRoute("/browse/(.)+", StaticPageTestHandler.class, new File("src/test/resources").getAbsoluteFile());

        addRoute("/video", VideoStreamUrl.class, RootDirectory+"/Download/test_1m.mp4");
    }

    /**
     * Main entry point
     *
     * @param args
     */
    public static void main(String[] args) {
        ServerRunner.run(AppNanolets.class);
    }
}
