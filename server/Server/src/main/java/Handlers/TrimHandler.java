package Handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import Request.StringRequest;
import Response.StringResponse;
import StringProcessor.StringProcessor;

import static Json.JsonConverter.convertJsonToObject;
import static Json.JsonConverter.convertObjectToJson;
import static Server.ServerCommunicator.readString;
import static Server.ServerCommunicator.writeString;

/**
 * Created by jakeg on 9/14/2018.
 */

public class TrimHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        boolean success = false;
        try{
            if(httpExchange.getRequestMethod().toLowerCase().equals("post")){
                System.out.println("Running ToLowerCase operation...");

                //Write the InputStream request body to a String
                InputStream iStream = httpExchange.getRequestBody();
                String requestBody = readString(iStream);

                //Convert the requestbody string, the json string, into a stringRequest object
                StringRequest request = (StringRequest)convertJsonToObject(requestBody,
                        new StringRequest("initial"));

                //Send the request along for processing. Store the response.
                StringProcessor processor = new StringProcessor();
                StringResponse response = new StringResponse(processor.trim(request.getInitialString()));

                //Convert the response into a json string
                String responseJsonData = convertObjectToJson(response);

                //Send the initial HTTP_ok response
                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                //Grab the response body of the httpExchange
                OutputStream responseBody = httpExchange.getResponseBody();

                //Write the JSON string to the output stream
                writeString(responseJsonData, responseBody);

                //Close the responseBody signifying it is complete
                responseBody.close();

                httpExchange.getResponseBody().close();

                success = true;
            }

            if(!success){
                //There was an internal server error. Send a response.
                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
                httpExchange.getResponseBody().close();
            }
        }
        catch(IOException e){
            //There was an internal server error. Send a response.
            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            httpExchange.getResponseBody().close();
            e.printStackTrace();
        }
    }
}
