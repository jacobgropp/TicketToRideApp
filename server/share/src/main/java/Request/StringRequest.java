package Request;

/**
 * Created by jakeg on 9/15/2018.
 */

public class StringRequest {
    private String initialString;

    public StringRequest(String initialString){
        this.initialString = initialString;
    }

    public String getInitialString() {
        return initialString;
    }

    public void setInitialString(String initialString) {
        this.initialString = initialString;
    }
}
