package Json;

import com.google.gson.Gson;

/**
 * Created by jakeg on 9/15/2018.
 */

public class JsonConverter {

    public static Object convertJsonToObject(String json, Object object){
        return new Gson().fromJson(json, object.getClass());
    }

    public static String convertObjectToJson(Object object){
        return new Gson().toJson(object);
    }
}
