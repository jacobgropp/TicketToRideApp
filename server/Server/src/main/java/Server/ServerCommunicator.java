package Server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;

import Handlers.HandlerBase;
import Handlers.ParseDoubleHandler;
import Handlers.ToLowerCaseHandler;
import Handlers.TrimHandler;

/**
 * Created by jakeg on 9/14/2018.
 */

public class ServerCommunicator {

    private static final int MAX_WAITING_CONNECTIONS = 10;

    private HttpServer server;

    public static void main(String[] arguments){

        String portNumber = arguments[0];
        new ServerCommunicator().run(portNumber);
    }

    private void run(String portNumber){
        System.out.println("Initializing Http server");
        try{
            server = HttpServer.create(new InetSocketAddress(Integer.parseInt(portNumber)), MAX_WAITING_CONNECTIONS);
        }
        catch(IOException e){
            e.printStackTrace();
            return;
        }

        server.setExecutor(null);

        //Create all the contexts.
        System.out.println("Creating contexts");

        //Trim Handler
        server.createContext("/trim", new TrimHandler());

        //ToLowerCaseHandler
        server.createContext("/tolowercase", new ToLowerCaseHandler());

        //ParseDoubleHandler
        server.createContext("/parsedouble", new ParseDoubleHandler());

        //Default handler
        server.createContext("/", new HandlerBase());

        System.out.println("Server started.");

        server.start();

    }

    public static void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }

    public static String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }
}
