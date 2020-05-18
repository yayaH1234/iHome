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
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ForgotPassActivity1 extends AppCompatActivity {

    private EditText email;
    private Button valid;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgpass);

        email = (EditText) findViewById(R.id.replyquest);
        valid = (Button) findViewById(R.id.valid);

        valid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stremail = email.getText().toString();


                final String url = "http://192.168.1.108:8080/custommer/listemail";

                final RequestQueue request = Volley.newRequestQueue(ForgotPassActivity1.this);
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,new Response.Listener<JSONArray>()
                {

                    @Override
                    public void onResponse(JSONArray response)
                    {
                        List<String> result = new ArrayList<String>();
                        for (int i = 0; i < response.length(); i++)
                        {
                            try
                            {
                                result.add(response.getJSONObject(i).toString());
                                Toast.makeText(ForgotPassActivity1.this,result.get(0),Toast.LENGTH_LONG).show();
                            }
                            catch (JSONException e)
                            {

                            }
                        }
                    }
                }, new Response.ErrorListener()
                {

                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        // Handle error
                    }
                });

                request.add(jsonArrayRequest);


               /* Intent intent = new Intent(getBaseContext(), ForgotPassActivity2.class);

                startActivity(intent);
                finish();*/
            }
        });


    }
}
