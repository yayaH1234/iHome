package com.example.ihome;

import android.Manifest;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;


import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.LocationListener;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.*;


import java.io.IOException;
import java.util.List;

public class mapForSellActivity extends AppCompatActivity implements LocationListener,
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    private LocationManager lm;
    private MapFragment mapFragment;
    private GoogleMap mMap;

    private Location location;


    private String res[]=null;



    private GoogleApiClient googleApiClient;

    private LocationRequest locationRequest;

    private Location lastLocation;

    private static final int Request_User_Location_Code=99;
    private int ProximityRadius=10000;

    //widget
    private Marker currentUserLocationMarker;


    private double latitude,longitude;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            checkUserLocationPermission();
        }

        FragmentManager fragmentManager = getFragmentManager();
        mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);




    }

    @SuppressWarnings("MissingPermission")
    private void loadMap() {
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mapForSellActivity.this.mMap = googleMap;
                googleMap.moveCamera(CameraUpdateFactory.zoomTo(15));

                googleMap.setMyLocationEnabled(true);
                //googleMap.addMarker(new MarkerOptions().position(new LatLng(43.799345, 6.725426)));
                //location.





            }
        });

    }

    public boolean checkUserLocationPermission(){
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) !=PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},Request_User_Location_Code);
            }else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},Request_User_Location_Code);

            }
            return false;

        }else{return true;}

    }



    @Override
    protected void onResume() {
        super.onResume();

        //      checkPe();
    }

/*

    private void checkPe() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, PERMS_CALL_ID);
            return;
        }
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, this);
        }
        if (lm.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)) {
            lm.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 10000, 0, this);
        }
        if (lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 0, this);
        }
        loadMap();
    }
*/

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
/*        if (requestCode == PERMS_CALL_ID) {
            checkPe();

        }*/
        switch (requestCode){
            case Request_User_Location_Code:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                        if(googleApiClient==null){
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                }else{
                    Toast.makeText(this,"permission denied",Toast.LENGTH_SHORT).show();
                }return;
        }

    }
    @Override
    protected void onPause() {
        super.onPause();

    }


    public void onLocationChanged(Location location){

        latitude=location.getLatitude();
        longitude=location.getLongitude();


        lastLocation=location;
        if(currentUserLocationMarker!=null){
            currentUserLocationMarker.remove();
        }
        LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
        MarkerOptions markerOptions=new MarkerOptions();

        markerOptions.position(latLng);
        markerOptions.title("User curent location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        currentUserLocationMarker=mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(14));
        if(googleApiClient!=null){
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient,this);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.map_option, menu);
        return true;
    }
    // attention cette fonction pour la boutter d'application a l'avenier il change le type de map....
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Change the map type based on the user's selection.
        switch (item.getItemId()) {
            case R.id.normal_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case R.id.hybrid_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            case R.id.satellite_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case R.id.terrain_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED) {


            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);


        }

        // Instantiate the RequestQueue.
        RequestQueue queue = Singleton.getInstance(this).getRequestQueue();
        String url =AllUrls.listNearbyhomeMForSell;




        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //textView.setText("Response is: "+ response.substring(0,500));
                        Toast.makeText(getApplicationContext(), "Getting nearby succes", Toast.LENGTH_SHORT).show();

             /*           try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            allLatitude.add((String) jsonObject.get("attitude"));
                            allLongitude.add((String) jsonObject.getString("longiture"));
                            alltitle.add((String) jsonObject.getString("adress"));

                            //                        Toast.makeText(mapForRentActivity.this,"latt"+jsonObject.toString(),Toast.LENGTH_SHORT).show();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                */

                        Log.d("mapForRentActivity","response server   "+response.toString());
                        System.out.println("------------------->"+response.toString());
                        Toast.makeText(mapForSellActivity.this,"latt"+response.toString(),Toast.LENGTH_SHORT).show();


                        res=response.toString().substring(1, response.toString().length() - 1).split(",");

                        int i=0;
                        while(i<res.length-1) {
                            MarkerOptions markerOptions = new MarkerOptions();
                            markerOptions.title(res[i]);

                            Log.d("mapForRentActivity","Adding title "+i);
                            i++;
                            double d1=Double.parseDouble(res[i].toString());

                            Log.d("mapForRentActivity","valeur d1 "+d1);
                            double d2=Double.parseDouble(res[i+1].toString());
                            Log.d("mapForRentActivity","valeur d2 "+d2);
                            LatLng latLng=new LatLng(d1,d2);

                            Log.d("mapForRentActivity","Adding Marker "+i);
                            markerOptions.position(latLng);
                            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

                            mMap.addMarker(markerOptions);
                            i+=2;
                        }



/*                      Toast.makeText(mapForRentActivity.this,"latt"+allLatitude.toString(),Toast.LENGTH_SHORT).show();
                        Toast.makeText(mapForRentActivity.this,"long"+allLongitude.toString(),Toast.LENGTH_SHORT).show();
                        Toast.makeText(mapForRentActivity.this,"addrs"+alltitle.toString(),Toast.LENGTH_SHORT).show();
*/


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // textView.setText("That didn't work!");
                Toast.makeText(getApplicationContext(), "Error Getting nearby", Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                Toast.makeText(mapForSellActivity.this,"For more information, re-tape marker.",Toast.LENGTH_LONG).show();

                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Toast.makeText(mapForSellActivity.this,"Please click on the house  ",Toast.LENGTH_LONG).show();

                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        String maisName = marker.getTitle();
                        Intent intent = new Intent(mapForSellActivity.this, maisonshowActivity.class);
                        intent.putExtra("Mais_name", maisName);
                        startActivity(intent);

                        return true;
                    }
                });

                        return true;
                    }
                });

                return true;
            }
        });


    }

    protected synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
    }

    public void onClick(View v){

        String hospital="hospital",school="school",restaurant="restaurant";

        Object transferData[] =new Object[2];
        GetNearbyPlaces getNearbyPlaces=new GetNearbyPlaces();

        switch(v.getId()){
            case R.id.search_address :
                EditText addressField =(EditText)findViewById(R.id.location_search);
                String address=addressField.getText().toString();

                List<Address> addressList=null;
                MarkerOptions userMarkerOption=new MarkerOptions();

                if(!TextUtils.isEmpty(address)){
                    Geocoder geocoder=new Geocoder(this);
                    try {
                        addressList =geocoder.getFromLocationName(address,6);
                        if(addressList!=null){
                            for (int i=0;i<addressList.size();i++){
                                Address userAddress=addressList.get(i);
                                LatLng latLng =new LatLng(userAddress.getLatitude(),userAddress.getLongitude());
                                userMarkerOption.position(latLng);
                                userMarkerOption.title(address);
                                userMarkerOption.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                                mMap.addMarker(userMarkerOption);

                                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                                mMap.moveCamera(CameraUpdateFactory.zoomTo(10));


                            }
                        }
                        else{
                            Toast.makeText(this,"Location introuvable",Toast.LENGTH_LONG).show();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }else{
                    Toast.makeText(this,"veuillez saisir une address",Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.hospitals_nearby:
                mMap.clear();
                String url=getUrl(latitude,longitude,hospital);
                transferData[0]=mMap;
                transferData[1]=url;
                getNearbyPlaces.execute(transferData);
                Toast.makeText(this,"Searching for nearby hospital",Toast.LENGTH_SHORT).show();
                Toast.makeText(this,"Showing for nearby hospital",Toast.LENGTH_SHORT).show();
                break;

            case R.id.shools_nearby:
                mMap.clear();
                url=getUrl(latitude,longitude,school);
                transferData[0]=mMap;
                transferData[1]=url;
                getNearbyPlaces.execute(transferData);
                Toast.makeText(this,"Searching for nearby Shools",Toast.LENGTH_SHORT).show();
                Toast.makeText(this,"Showing for nearby Shools",Toast.LENGTH_SHORT).show();

                break;
            case R.id.restaurants_nearby:
                mMap.clear();
                url=getUrl(latitude,longitude,restaurant);
                transferData[0]=mMap;
                transferData[1]=url;
                getNearbyPlaces.execute(transferData);
                Toast.makeText(this,"Searching for nearby restaurants",Toast.LENGTH_SHORT).show();
                Toast.makeText(this,"Showing for nearby restaurants",Toast.LENGTH_SHORT).show();

                break;

        }

    }

    private  String getUrl(double latitude,double longitude,String nearbyPlace){
        StringBuilder googleUrl=new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googleUrl.append("location="+latitude+","+longitude);
        googleUrl.append("&radius="+ProximityRadius);
        googleUrl.append("&types="+nearbyPlace);
        googleUrl.append("&name=harbour");
        googleUrl.append("&key="+"AIzaSyAjFyLZuE9sX99woBW8mbapxL8bY3U5rR8");

        Log.d("MapActivity","url="+googleUrl.toString());
        return googleUrl.toString();
    }



    @Override
    public void onConnected(@Nullable Bundle bundle) {

        locationRequest =new LocationRequest();
        locationRequest.setInterval(1100);
        locationRequest.setFastestInterval(1100);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
