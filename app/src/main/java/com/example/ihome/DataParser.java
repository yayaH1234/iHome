package com.example.ihome;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DataParser {

    private HashMap<String,String> getSingleNearbyPlace(JSONObject googlePlacesJSON){
        HashMap<String,String> googlePlaceMap=new HashMap<>();
        String  nameOfPlace="-NA-";
        String  vicinity="-NA-";
        String  latitude="-NA-";
        String  longitude="-NA-";
        String  reference="-NA-";

        try {
            if (!googlePlacesJSON.isNull("name")) {
                nameOfPlace = googlePlacesJSON.getString("name");

            }
            if (!googlePlacesJSON.isNull("vicinity")) {
                vicinity = googlePlacesJSON.getString("vicinity");

            }
            latitude=googlePlacesJSON.getJSONObject("geometry").getJSONObject("location").getString("lat");
            longitude=googlePlacesJSON.getJSONObject("geometry").getJSONObject("location").getString("lng");
            reference=googlePlacesJSON.getString("reference");

            googlePlaceMap.put("place_name",nameOfPlace);
            googlePlaceMap.put("vicinity",vicinity);
            googlePlaceMap.put("lat",latitude);
            googlePlaceMap.put("lng",longitude);
            googlePlaceMap.put("reference",reference);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return googlePlaceMap;
    }


    private List<HashMap<String,String>> getAllNearbyPlace(JSONArray jsonArray){
        int counter=jsonArray.length();

        List<HashMap<String,String>> NearbyPlaceList=new ArrayList<>();
        HashMap<String,String> NearbyPlaceMap=null;
        for(int i=0;i<counter;i++){
            try {
                NearbyPlaceMap=getSingleNearbyPlace((JSONObject)jsonArray.get(i));
                NearbyPlaceList.add(NearbyPlaceMap);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return NearbyPlaceList;
    }


    public List<HashMap<String,String>> parse(String jSONdata){
        JSONArray jsonArray=null;
        JSONObject jsonObject;

        try {
            jsonObject=new JSONObject(jSONdata);
            jsonArray=jsonObject.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return getAllNearbyPlace(jsonArray);
    }


}

