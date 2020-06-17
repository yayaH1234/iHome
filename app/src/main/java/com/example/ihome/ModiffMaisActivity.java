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

public class ModiffMaisActivity extends AppCompatActivity {


    private Button modifff;
    private EditText nameMais;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moddmaissu);

        modifff=(Button)findViewById(R.id.valid2);
        nameMais = (EditText) findViewById(R.id.homeModdd);

        final Bundle extras = getIntent().getExtras();
        Toast.makeText(ModiffMaisActivity.this,"Your email "+extras.getString("email"),Toast.LENGTH_LONG).show();

    modifff.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final String nomMs=nameMais.getText().toString();

            // Instantiate the RequestQueue.
            final RequestQueue queue = Singleton.getInstance(ModiffMaisActivity.this).getRequestQueue();// Volley.newRequestQueue(this);

            final String url = AllUrls.maistestprop+extras.getString("email")+"__"+nomMs;

            // Request a string response from the provided URL.
            final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                    if(response.equals("true")) {
                        Toast.makeText(ModiffMaisActivity.this, "Permission granted ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ModiffMaisActivity.this,ModiffMaisActivity1.class);
                        intent.putExtra("nom_maison", nomMs);
                        intent.putExtra("email", extras.getString("email"));
                        startActivity(intent);
                        finish();
                    }


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // textView.setText("That didn't work!");
                    Toast.makeText(ModiffMaisActivity.this, "Error Server ", Toast.LENGTH_SHORT).show();
                }
            });

            // Add the request to the RequestQueue.
            queue.add(stringRequest);



        }
    });

    }
}
