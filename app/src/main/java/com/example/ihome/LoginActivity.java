package com.example.ihome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

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






    login.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
            final String emailString = email.getText().toString();
            String pwd = password.getText().toString();
            Toast.makeText(LoginActivity.this,"Singed in  ",Toast.LENGTH_LONG).show();
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
            if(!emailString.equals("") && !pwd.equals("")){

            }

            if(!emailString.equals("") && !pwd.equals("")){
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);}



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
