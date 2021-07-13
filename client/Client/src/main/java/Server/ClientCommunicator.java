package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;

import Request.DoubleRequest;
import Request.StringRequest;
import Response.DoubleResponse;
import Response.StringResponse;

import static Json.JsonConverter.convertJsonToObject;

/**
 * Created by jakeg on 9/15/2018.
 */

public class ClientCommunicator {

    private String serverHost;
    private int serverPort;

    public ClientCommunicator(String serverHost, int serverPort){
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }

    private HttpURLConnection post(URL url){
        try {
            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            http.setRequestMethod("POST");

            http.setDoOutput(true);

            http.addRequestProperty("Accept", "application/json");

            return http;
        }
        catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public StringResponse toLowerCase(StringRequest request){
        //Make a string command API operation
        if(testForTimeouts()){
            try{
                URL url = new URL("http://" + serverHost + ":" + serverPort + "/tolowercase");
                HttpURLConnection http = post(url);
                String reqData =
                        "{" +
                                "\"InitialString\":\"" + request.getInitialString() + "\"" +
                                "}";
                OutputStream reqBody = http.getOutputStream();
                writeString(reqData, reqBody);
                reqBody.close();
                http.connect();
                if(http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    System.out.println("ToLowerCase operation is a success.");
                    InputStream iStream = http.getInputStream();
                    String requestBody = readString(iStream);
                    StringResponse response = (StringResponse) convertJsonToObject(requestBody,
                            new StringResponse("new"));

                    return response;
                }else{
                    System.out.println("ERROR: " + http.getResponseMessage());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public StringResponse trim(StringRequest request){
        //Make a string command API operation
        if(testForTimeouts()){
            try{
                URL url = new URL("http://" + serverHost + ":" + serverPort + "/trim");
                HttpURLConnection http = post(url);
                String reqData =
                        "{" +
                                "\"InitialString\":\"" + request.getInitialString() + "\"," +
                                "}";
                OutputStream reqBody = http.getOutputStream();
                writeString(reqData, reqBody);
                reqBody.close();
                http.connect();
                if(http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    System.out.println("Trim operation is a success.");
                    InputStream iStream = http.getInputStream();
                    String requestBody = readString(iStream);
                    StringResponse response = (StringResponse) convertJsonToObject(requestBody,
                            new StringResponse("new"));

                    return response;
                }else{
                    System.out.println("ERROR: " + http.getResponseMessage());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public DoubleResponse parseDouble(DoubleRequest request){
        //Make a double command API operation
        if(testForTimeouts()){
            try{
                URL url = new URL("http://" + serverHost + ":" + serverPort + "/parsedouble");
                HttpURLConnection http = post(url);
                String reqData =
                        "{" +
                                "\"InitialString\":\"" + request.getInitialString() + "\"," +
                                "}";
                OutputStream reqBody = http.getOutputStream();
                writeString(reqData, reqBody);
                reqBody.close();
                http.connect();
                if(http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    System.out.println("Parse Double operation is a success.");
                    InputStream iStream = http.getInputStream();
                    String requestBody = readString(iStream);
                    DoubleResponse response = (DoubleResponse) convertJsonToObject(requestBody,
                            new DoubleResponse(0.0));

                    return response;
                }else{
                    System.out.println("ERROR: " + http.getResponseMessage());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private boolean testForTimeouts(){
        boolean exists = false;
        try {
            SocketAddress address = new InetSocketAddress(serverHost, serverPort);
            Socket sock = new Socket();
            int timeoutMS = 10000;
            sock.connect(address, timeoutMS);
            exists = true;
            return exists;
        }
        catch(IOException e){
            e.printStackTrace();
            return exists;
        }
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
