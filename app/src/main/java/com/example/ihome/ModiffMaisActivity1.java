package com.example.ihome;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ModiffMaisActivity1  extends AppCompatActivity {



    private String res[]=null;

    private EditText Nom_mais;
    private EditText NomOwner;
    private EditText Service_type;
    private EditText adress;
    private EditText price;

    private Button supp;
    private Button modifff;

    private String nomMs;
    private String type;


    private RadioGroup radioGroup;
    private RadioButton radioButton;

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

        final String url = AllUrls.getpropformod1+nomMs;

        // Request a string response from the provided URL.
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        res=response.toString().substring(1, response.toString().length() - 1).split(",");



                        Nom_mais.setText(nomMs);
                        NomOwner.setText(res[1]);
                        adress.setText(res[3]);
                        price.setText(res[4]);

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

                radioGroup = (RadioGroup) findViewById(R.id.radio);
                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);

                type=radioButton.getText().toString();

                final String NmMs=Nom_mais.getText().toString();
                final String NmOw=NomOwner.getText().toString();
                final String tyServ=radioButton.getText().toString();//Service_type.getText().toString();
                final String adrs=adress.getText().toString();
                final String pr=price.getText().toString();

                String requestUrl = AllUrls.setpropformod1;
                StringRequest stringRequest = new StringRequest(Request.Method.POST, requestUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Volley Result", ""+response); //the response contains the result from the server, a json string or any other object returned by your server

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace(); //log the error resulting from the request for diagnosis/debugging

                    }
                }){

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> postMap = new HashMap<>();
                        postMap.put("eml", extras.getString("email"));
                        postMap.put("nom_mais", NmMs);
                        postMap.put("nom_prop", NmOw);
                        postMap.put("type_serv",type);
                        postMap.put("adress", adrs);
                        postMap.put("prix_serv", pr);





                        //..... Add as many key value pairs in the map as necessary for your request
                        return postMap;
                    }
                };
                //make the request to your server as indicated in your request url
                Volley.newRequestQueue(ModiffMaisActivity1.this).add(stringRequest);

                //getActivity().getApplicationContext()




                Intent intent = new Intent(ModiffMaisActivity1.this,ModiffMaisActivity2.class);
                intent.putExtra("nom_maison", nomMs);
                intent.putExtra("email", extras.getString("email"));
                startActivity(intent);
                finish();

            }
        });
    }
}
