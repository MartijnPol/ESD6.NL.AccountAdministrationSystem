package web.core.helper;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

public final class RestHelper {

	private static String dsJwtToken;

	private static String Identity = "uUQYP0AjSmFJ5rMAYoK5";

	public static String getDsJwtToken() throws UnirestException {
		if(dsJwtToken == null){
			HttpResponse<JsonNode> response = Unirest.get("http://192.168.25.122:77/DisplacementSystem/api/authenticate/"+Identity).asJson();
			JSONObject obj = response.getBody().getObject();
			dsJwtToken = obj.getString("Token");
		}
		return dsJwtToken;
	}
}
