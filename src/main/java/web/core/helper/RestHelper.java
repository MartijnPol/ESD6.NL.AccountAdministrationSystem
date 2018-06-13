package web.core.helper;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

public final class RestHelper {

	private static String dsJwtToken;

	private static String Identity = "uUQYP0AjSmFJ5rMAYoK5";

	/**
	 * Get dsJwtToken.
	 * In case that the jwtToken is not currently set ,a authenticate request is send to the Displacement System.
	 *
	 * @return dsJwtToken is String format.
	 * @throws UnirestException Exception that is thrown when something goes wrong during the request.
	 */
	public static String getDsJwtToken() throws UnirestException {
		if(dsJwtToken == null){
			HttpResponse<JsonNode> response = Unirest.get("http://192.168.25.122:77/DisplacementSystem/api/authenticate/"+Identity).asJson();
			JSONObject obj = response.getBody().getObject();
			dsJwtToken = obj.getString("Token");
		}
		return dsJwtToken;
	}
}
