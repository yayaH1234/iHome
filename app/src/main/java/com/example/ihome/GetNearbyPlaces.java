package com.example.ihome;

import android.os.AsyncTask;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GetNearbyPlaces extends AsyncTask<Object,String,String> {

    private String googlePlacesData,url;
    private GoogleMap mMap;



    @Override
    protected String doInBackground(Object... objects) {
        mMap=(GoogleMap)objects[0];
        url=(String)objects[1];
        DownloadUrl downloadUrl=new DownloadUrl();
        try {
            googlePlacesData=downloadUrl.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return googlePlacesData;
    }

    @Override
    protected void onPostExecute(String s) {

        List<HashMap<String,String>> nearbyPlaceList=null;
        DataParser dataParser=new DataParser();
        nearbyPlaceList=dataParser.parse(s);


        DisplayNearbyPlace(nearbyPlaceList);

    }
    private void DisplayNearbyPlace(List<HashMap<String,String>> nearbyPlaceList){
        for(int i=0;i<nearbyPlaceList.size();i++){
            MarkerOptions markerOptions=new MarkerOptions();
            HashMap<String,String> googleNearbyPlace=nearbyPlaceList.get(i);
            String nameOfPlace=googleNearbyPlace.get("place_name");
            String vicinity=googleNearbyPlace.get("vicinity");
            double lat=Double.parseDouble(googleNearbyPlace.get("lat"));
            double lng=Double.parseDouble(googleNearbyPlace.get("lng"));



            LatLng latLng =new LatLng(lat,lng);
            markerOptions.position(latLng);
            markerOptions.title(nameOfPlace+" : "+vicinity);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            mMap.addMarker(markerOptions);

            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.moveCamera(CameraUpdateFactory.zoomTo(10));
        }
    }
}
