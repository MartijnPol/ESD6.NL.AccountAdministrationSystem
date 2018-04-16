package main.utils;

import com.google.gson.Gson;
import main.domain.RDW;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Martijn van der Pol on 06-04-18
 **/
public final class JSONHelper {

    /**
     * Function to return valid JSONObject from given URL
     *
     * @param url
     * @return
     */
    public static RDW getJSONObjectFromUrl(String url) {
        try {
            Gson gson = new Gson();
            String json = IOUtils.toString(new URL(url), Charset.forName("UTF-8"))
                    .replace("[", "")
                    .replace("]", "");
            return gson.fromJson(json, (Type) RDW.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
