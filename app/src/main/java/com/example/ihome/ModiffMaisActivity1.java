package com.example.ihome;

import android.content.Intent;
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

public class ModiffMaisActivity1  extends AppCompatActivity {



    private EditText Nom_mais;
    private EditText NomOwner;
    private EditText Service_type;
    private EditText adress;
    private EditText price;

    private Button supp;
    private Button modifff;

    private String nomMs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modiffic1);
        modifff=(Button)findViewById(R.id.next);


        Nom_mais= (EditText) findViewById(R.id.homename);
        NomOwner= (EditText) findViewById(R.id.ownername);
        //    Service_type = (EditText) root.findViewById(R.id.service);
        adress= (EditText) findViewById(R.id.adresss);
        price = (EditText) findViewById(R.id.price);

        final Bundle extras = getIntent().getExtras();
        nomMs=extras.getString("nom_maison");
        Toast.makeText(ModiffMaisActivity1.this,"Your email "+extras.getString("email")+" maison "+nomMs,Toast.LENGTH_LONG).show();

        // Instantiate the RequestQueue.
        final RequestQueue queue = Singleton.getInstance(ModiffMaisActivity1.this).getRequestQueue();// Volley.newRequestQueue(this);

        final String url = AllUrls.getpropformod1+extras.getString("email")+"__"+nomMs;

        // Request a string response from the provided URL.
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {




                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // textView.setText("That didn't work!");
                Toast.makeText(ModiffMaisActivity1.this, "Error Server ", Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

        modifff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModiffMaisActivity1.this,ModiffMaisActivity2.class);
                intent.putExtra("nom_maison", nomMs);
                intent.putExtra("email", extras.getString("email"));
                startActivity(intent);
                finish();

            }
        });
    }
}
