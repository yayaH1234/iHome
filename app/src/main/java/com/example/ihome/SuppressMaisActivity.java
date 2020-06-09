package com.example.ihome;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class SuppressMaisActivity extends AppCompatActivity {

    private EditText dellhome;
    private Button send;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supressmais);



        dellhome = (EditText) findViewById(R.id.homedelll);
        send = (Button) findViewById(R.id.valid2);


        final Bundle extras = getIntent().getExtras();
        Toast.makeText(SuppressMaisActivity.this,"Your email "+extras.getString("email"),Toast.LENGTH_LONG).show();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idOrName=dellhome.getText().toString();


                // Instantiate the RequestQueue.
                final RequestQueue queue = Singleton.getInstance(SuppressMaisActivity.this).getRequestQueue();// Volley.newRequestQueue(this);

                final String url = AllUrls.userinfDell+idOrName+"___"+extras.getString("email");

                // Request a string response from the provided URL.
                final StringRequest stringRequest2 = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {



                                Toast.makeText(SuppressMaisActivity.this, "Susses delletting ", Toast.LENGTH_SHORT).show();



                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // textView.setText("That didn't work!");
                        Toast.makeText(SuppressMaisActivity.this, "Error Server ", Toast.LENGTH_SHORT).show();
                    }
                });

                // Add the request to the RequestQueue.
                queue.add(stringRequest2);

            }
        });

    }
}
