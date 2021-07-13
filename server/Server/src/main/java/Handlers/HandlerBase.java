package Handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by jakeg on 9/14/2018.
 */

public class HandlerBase implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
       boolean success = false;

       try{
           System.out.println("Determining command...");
           String uri = exchange.getRequestURI().toString();
           if(uri.equals("/")){
               System.out.println("Default handler requested...");
               //I guess this is where I need to figure out the command junk...?
           }
       }
       catch(Exception e){
           exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
           exchange.getResponseBody().close();
           e.printStackTrace();
       }
    }
}
