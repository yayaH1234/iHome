package com.example.ihome;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import org.json.JSONException;
import org.json.JSONObject;

public class maisonshowActivity extends AppCompatActivity {


    private String mais_name;




    private String res[]=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showms);


        final ImageView imageView =(ImageView) findViewById(R.id.viewImageForMais);

        final TextView txtviewNaMe = (TextView) findViewById(R.id.textNameMais);
        final TextView txtAdressMais = (TextView) findViewById(R.id.textAdrssMais);
        final TextView txtOwnerMs = (TextView) findViewById(R.id.textOwnerMais);
        final TextView txtprissMs = (TextView) findViewById(R.id.textPrissMais);
        final TextView txtLocMais = (TextView) findViewById(R.id.textLocMais);

        final Button btnRes=(Button) findViewById(R.id.rsrv);

        Bundle extras = getIntent().getExtras();

        mais_name =extras.getString("Mais_name");

        Toast.makeText(maisonshowActivity.this,mais_name,Toast.LENGTH_LONG).show();



        // Instantiate the RequestQueue.
        RequestQueue queue =Singleton.getInstance(this).getRequestQueue();// Volley.newRequestQueue(this);
        final String url =AllUrls.getmsJSON+mais_name;


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //textView.setText("Response is: "+ response.substring(0,500));
                        Toast.makeText(getApplicationContext(), "Getting information succes", Toast.LENGTH_SHORT).show();



                        Log.d("mapForRentActivity","Response server   "+response.toString());
                        System.out.println("------------------->"+response.toString());
/*

                        res=response.toString().substring(1, response.toString().length() - 1).split(",");


                        Toast.makeText(maisonshowActivity.this,res[0]+" nom mais "+res[1]+" Owner "+res[2]+" image "+res[3]+" nome loc "+res[4]+" prix "+res[5]+" adress ",Toast.LENGTH_SHORT).show();

                        if(res[0].equals(null)){
                            res[0]="_";
                        }
                        if(res[1].equals(null)){
                            res[1]="_";
                        }
                        if(res[2].equals(null)){
                            res[2]="_";
                        }
                        if(res[3].equals(null)){
                            res[3]="_";
                        }
                        if(res[4].equals(null)){
                            res[4]="_";
                        }
                        if(res[5].equals(null)){
                            res[5]="_";
                        }
                        final String nom_mais=res[0];
                        final String Owner=res[1];
                        final String image=res[2];
                        final String nom_loc=res[3];
                        final String prix=res[4];
                        final String adress=res[5];

                        byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);




                        txtviewNaMe.setText(nom_mais);
                        txtAdressMais.setText(adress);
                        txtOwnerMs.setText(Owner);
                        txtprissMs.setText(prix);
                        txtLocMais.setText(nom_loc);


                        imageView.setImageBitmap(decodedByte);

*/

                        try {
                            JSONObject jo = new JSONObject(response);
                            String  nom_mais = jo.getString("nom_mais");
                            String nom_prop =jo.getString("nom_prop");
                            String nomLoc =jo.getString("nom_loc");
                            String adress = jo.getString("adress");
                            String prixserv = jo.getString("prix_serv");
                            JSONObject imagedbjson = jo.getJSONObject("imagedp");
                            String imagedp=imagedbjson.getString("data");
                            byte[] decodedString = Base64.decode(imagedp, Base64.DEFAULT);
                            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
/*
                            nameuser.setText(jo.getString("nom").toString());
                            numtel.setText(jo.getString("numeroTel"));
                            eml.setText(jo.getString("email"));
                            imageView.setImageBitmap(decodedByte);
*/

                            txtviewNaMe.setText(nom_mais);
                            txtAdressMais.setText(adress);
                            txtOwnerMs.setText(nom_prop);
                            txtprissMs.setText(prixserv);
                            if (nomLoc.equals(null)){
                                txtLocMais.setText("____");
                            }else{
                            txtLocMais.setText(nomLoc);}


                            imageView.setImageBitmap(decodedByte);

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // textView.setText("That didn't work!");
                Toast.makeText(getApplicationContext(), "Error Getting information", Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);



        btnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle extras = getIntent().getExtras();
                Toast.makeText(maisonshowActivity.this,"Your email "+extras.getString("email"),Toast.LENGTH_LONG).show();

                String nom_us=extras.getString("email");

                String urlAch =AllUrls.achatMs+nom_us+"__"+mais_name;

                // Instantiate the RequestQueue.
                RequestQueue queue2 =Singleton.getInstance(maisonshowActivity.this).getRequestQueue();// Volley.newRequestQueue(this);

                // Request a string response from the provided URL.
                StringRequest stringRequest2 = new StringRequest(Request.Method.GET, urlAch,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                //textView.setText("Response is: "+ response.substring(0,500));
                                Toast.makeText(getApplicationContext(), "Getting information succes", Toast.LENGTH_SHORT).show();



                                Log.d("maisonshowActivity","Response server   "+response.toString());
                                System.out.println("------------------->"+response.toString());
                                //       Toast.makeText(maisonshowActivity.this,response.toString(),Toast.LENGTH_SHORT).show();

                                if(response.toString().equals("succes")){
                                    txtLocMais.setText("Reserved");
                                }


                                Toast.makeText(maisonshowActivity.this,response.toString(),Toast.LENGTH_SHORT).show();











                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // textView.setText("That didn't work!");
                        Toast.makeText(getApplicationContext(), "Error Getting information", Toast.LENGTH_SHORT).show();
                    }
                });

                queue2.add(stringRequest2);
            }
                                                             }
            );



    }

}