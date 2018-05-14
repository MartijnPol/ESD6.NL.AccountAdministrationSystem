package main.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import main.domain.*;
import org.json.JSONArray;

import javax.ejb.Stateless;
import javax.ws.rs.core.Response;



@Stateless
public class CarTrackerService {



    public Response createCartracker() throws UnirestException {
        HttpResponse<JsonNode> getResponse = Unirest.get("http://localhost:8080/DisplacementSystem/api/CarTrackers/Create").asJson();
        JSONArray array = getResponse.getBody().getArray();
        System.out.println("array = " + array);
        return Response.ok(array.toString()).build();
    }


}
