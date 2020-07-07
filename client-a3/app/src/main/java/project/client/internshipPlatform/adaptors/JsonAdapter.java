package project.client.internshipPlatform.adaptors;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class JsonAdapter implements IAdapterRequest {



    @Override
    public String request(Object o) {

        String json = null;


        try {

            JSONObject xmlJSONObj = XML.toJSONObject(o.toString());
            json = xmlJSONObj.toString();


        } catch (JSONException e){}

        return json;
    }
}
