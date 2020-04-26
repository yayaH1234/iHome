package com.example.ihome;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {



    RelativeLayout rellay1,rellay2;

    Handler handler=new Handler();
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);

        }
    };

    private EditText username;
    private EditText email,Matricule;
    private EditText password1;
    private EditText password2;
    private ProgressDialog mProgressDialog;
    private Button createc;


    // Write a message to the database


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);
        createc = (Button) findViewById(R.id.cretc);

        username = (EditText) findViewById(R.id.username);
        Matricule = (EditText) findViewById(R.id.fonc);
        email = (EditText) findViewById(R.id.emailEd2);
        password1 = (EditText) findViewById(R.id.passwordId);
        password2 = (EditText) findViewById(R.id.passwordId2);



        handler.postDelayed(runnable, 500);//2000 time of splach
        rellay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        rellay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        createc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  if(password1 == password2){
                // createNewAccount();
                final String fnc = Matricule.getText().toString().trim();
                final  String em = username.getText().toString().trim();
                final  String pwd = password1.getText().toString().trim();
                final  String pwd1 = password2.getText().toString().trim();

                if (!TextUtils.isEmpty(fnc) && !TextUtils.isEmpty(em) && !TextUtils.isEmpty(pwd) && (pwd.equals(pwd1))) {
                    /*mProgressDialog= new ProgressDialog(SignupActivity.this);
                    mProgressDialog.setMessage("Creating Account ...");
                    mProgressDialog.show();*/
                    Toast.makeText(SignupActivity.this,"Verification d'existense ",Toast.LENGTH_SHORT);
                    Toast.makeText(SignupActivity.this,"Creation ",Toast.LENGTH_LONG);
                    startActivity(new Intent(SignupActivity.this,MapActivity.class));
                    finish();
                }else
                    Toast.makeText(SignupActivity.this,"Erreur de connection",Toast.LENGTH_LONG);
                //}
                //  else Toast.makeText(SignupActivity.this,"attention password incorrct ",Toast.LENGTH_LONG);

            }
        });




    }

    private void createNewAccount(){


    }
}
