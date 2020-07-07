package project.client.internshipPlatform.adaptors;

import org.json.JSONObject;
import org.json.XML;

public class XMLAdapter implements IAdapterRequest{

    @Override
    public String request(Object data) {

        String xml = null;

        try {

            JSONObject json = new JSONObject(data.toString());
            xml = XML.toString(json);

        } catch (Exception e){}

        return xml;
    }
}
