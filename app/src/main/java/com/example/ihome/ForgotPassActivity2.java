package com.example.ihome;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Map;

public class ForgotPassActivity2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText quest;
    private EditText reply;
    private EditText password;
    private Button valid;


    private String mail;
    private String questsecSel;



    private String res[]=null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgpass2);

      //  quest = (EditText) findViewById(R.id.qstsec);
        Spinner spinner=findViewById(R.id.qstsec);
        reply = (EditText) findViewById(R.id.emailreponse);
        password = (EditText) findViewById(R.id.pwd);
        valid = (Button) findViewById(R.id.valid);

        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this,R.array.lit,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Bundle extras = getIntent().getExtras();

        mail =extras.getString("email");


        valid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String eml=mail;
                final String rep=reply.getText().toString();

                // Instantiate the RequestQueue.
                RequestQueue queue =Singleton.getInstance(ForgotPassActivity2.this).getRequestQueue();// Volley.newRequestQueue(this);
                String url = AllUrls.gettingPassword+eml+"__"+rep;

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                //textView.setText("Response is: "+ response.substring(0,500));
                                Toast.makeText(getApplicationContext(), "Getting nearby succes", Toast.LENGTH_SHORT).show();



                                Log.d("mapForRentActivity","response server   "+response.toString());
                                System.out.println("------------------->"+response.toString());
                                //Toast.makeText(ForgotPassActivity2.this,"latt"+response.toString(),Toast.LENGTH_SHORT).show();


                             //   res=response.toString().substring(1, response.toString().length() - 1).split(",");





                    password.setText(response.toString());



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


            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        questsecSel=parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),questsecSel,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
