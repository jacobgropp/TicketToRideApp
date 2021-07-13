package StringProcessor;

import Request.DoubleRequest;
import Request.StringRequest;
import Response.DoubleResponse;
import Response.StringResponse;
import Server.ClientCommunicator;

/**
 * Created by jakeg on 9/15/2018.
 */

public class StringProcessorProxy implements IStringProcessor {

    /**
     * Lower Case Variables
     */
    private String initialLowerCase;

    public String toLowerCase(String s){
        //Create a client communicator
        ClientCommunicator comm = new ClientCommunicator("10.0.2.2", 8080);

        //Create a string request with the initial string
        StringRequest request = new StringRequest(s);

        //Send the request to the server using the client communicator
        StringResponse response = comm.toLowerCase(request);

        return response.getNewString();
    }

    public String getInitialLowerCase() {
        return initialLowerCase;
    }

    public void setInitialLowerCase(String initialLowerCase) {
        this.initialLowerCase = initialLowerCase;
    }

    /**
     * Trim Variables
     */
    private String intialTrim;

    public String trim(String s){
        //Create a client communicator
        ClientCommunicator comm = new ClientCommunicator("10.0.2.2", 8080);

        //Create a string request with the initial string
        StringRequest request = new StringRequest(s);

        //Send the request to the server using the client communicator
        StringResponse response = comm.trim(request);

        return response.getNewString();
    }

    public String getIntialTrim() {
        return intialTrim;
    }

    public void setIntialTrim(String intialTrim) {
        this.intialTrim = intialTrim;
    }

    /**
     * Parse Double Variables
     */
    private String initialDouble;

    public Double parseDouble(String s){
        //Create a client communicator
        ClientCommunicator comm = new ClientCommunicator("10.0.2.2", 8080);

        //Create a double request with the initial string
        DoubleRequest request = new DoubleRequest(s);

        //Send the request to the server using the client communicator
        DoubleResponse response = comm.parseDouble(request);

        return response.getNewDouble();
    }

    public String getInitialDouble() {
        return initialDouble;
    }

    public void setInitialDouble(String initialDouble) {
        this.initialDouble = initialDouble;
    }

}
