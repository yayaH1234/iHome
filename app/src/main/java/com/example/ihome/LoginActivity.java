package com.example.ihome;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class LoginActivity extends AppCompatActivity {
    RelativeLayout rellay1,rellay2,rellay3;
    private EditText email;
    private EditText password;
    private Button login;
    private Button Forg;
    private Button createacc;
    Handler handler=new Handler();
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
            rellay3.setVisibility(View.VISIBLE);


        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);
        rellay3 = (RelativeLayout) findViewById(R.id.rellay3);
        password = (EditText) findViewById(R.id.passwordId);
        email = (EditText) findViewById(R.id.emailEd);
        login = (Button) findViewById(R.id.LoginButton);
        createacc= (Button) findViewById(R.id.btnsign);
        Forg= (Button) findViewById(R.id.forgetpass);

        handler.postDelayed(runnable, 4000);//2000 time of splach




    Forg.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
            Toast.makeText(LoginActivity.this,"Ooh forgotten password ",Toast.LENGTH_LONG).show();
            startActivity(new Intent(LoginActivity.this,ForgotPassActivity1.class));
            finish();
        }});

    login.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
            final String emailString = email.getText().toString();
            String pwd = password.getText().toString();
         /*   Toast.makeText(LoginActivity.this,"Singed in  ",Toast.LENGTH_LONG).show();
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();

          */
            if(emailString.equals("") &&pwd.equals("")){

                Toast.makeText(LoginActivity.this,"Empty field ",Toast.LENGTH_LONG).show();
            }

            if(!emailString.equals("") && !pwd.equals("")){
            /*    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

             */


                // Instantiate the RequestQueue.
                RequestQueue queue =Singleton.getInstance(LoginActivity.this).getRequestQueue();// Volley.newRequestQueue(this);
                final String url =AllUrls.userlogin+emailString+"__"+pwd;//email__password



                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                //textView.setText("Response is: "+ response.substring(0,500));
                                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();



                                Log.d("mapForRentActivity","Response server   "+response);
                                System.out.println("------------------->   "+response);
                                //       Toast.makeText(maisonshowActivity.this,response.toString(),Toast.LENGTH_SHORT).show();



                                if(response.equals("succes")){
                                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                    intent.putExtra("email",emailString);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    Toast.makeText(LoginActivity.this,"Incorrect information ",Toast.LENGTH_LONG).show();
                                }



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





            }



        }
    });
        createacc.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(getApplicationContext(), SignupActivity1.class);
            startActivity(intent);
            finish();
        }
    });

} }
