package com.example.ihome;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ForgotPassActivity1 extends AppCompatActivity {

    private EditText email;
    private Button valid;



    private String res[]=null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgpass);

        email = (EditText) findViewById(R.id.replyquest);
        valid = (Button) findViewById(R.id.valid);



        // Instantiate the RequestQueue.
        RequestQueue queue =Singleton.getInstance(ForgotPassActivity1.this).getRequestQueue();// Volley.newRequestQueue(this);

        final String url =AllUrls.getlistemail;

        // Request a string response from the provided URL.
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //textView.setText("Response is: "+ response.substring(0,500));





                        res=response.toString().substring(1, response.toString().length() - 1).split(",");







    }
}, new Response.ErrorListener() {
@Override
public void onErrorResponse(VolleyError error) {
        // textView.setText("That didn't work!");
        Toast.makeText(getApplicationContext(), "Error Server", Toast.LENGTH_SHORT).show();
        }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

        valid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String stremail = email.getText().toString();

                int m=0;
                for(int o=0;o<res.length;o++){
                    String eso=res[o];
                    Log.d("ForgotPassActivity","------------------->"+eso);
                    if(eso.equals("\""+stremail+"\"")){
                        Toast.makeText(ForgotPassActivity1.this,"email existed",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ForgotPassActivity1.this,ForgotPassActivity2.class);
                        intent.putExtra("email", stremail);
                        startActivity(intent);
                        finish();
                    }
                }




               /* Intent intent = new Intent(getBaseContext(), ForgotPassActivity2.class);

                startActivity(intent);
                finish();*/
            }
        });


    }
}
