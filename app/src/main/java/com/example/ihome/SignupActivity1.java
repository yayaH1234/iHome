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


public class SignupActivity1 extends AppCompatActivity {

    private EditText FirstName;
    private EditText LastName;
    private EditText Email;
    private EditText password;
    private EditText password1;
    private Button Next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup1);

        FirstName= (EditText) findViewById(R.id.firstname);
        LastName= (EditText) findViewById(R.id.lastname);
        password = (EditText) findViewById(R.id.passwordId1);
        password1 = (EditText) findViewById(R.id.passwordId2);
        Email = (EditText) findViewById(R.id.email);
        Next = (Button) findViewById(R.id.next);



        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstNm=FirstName.getText().toString();
                String lastNm=LastName.getText().toString();
                String email=Email.getText().toString();
                String pwd1=password.getText().toString();
                String pwd2=password1.getText().toString();


                if(firstNm.equals("") || lastNm.equals("") || email.equals("") || pwd1.equals("") || pwd2.equals("")){
                    Toast.makeText(SignupActivity1.this,"Empty fields  ",Toast.LENGTH_LONG).show();

                }
                if(!pwd1.equals(pwd2)){

                    Toast.makeText(SignupActivity1.this,"Password invalid  ",Toast.LENGTH_LONG).show();

                }
                if(!firstNm.equals("") || !lastNm.equals("") || !email.equals("") || !pwd1.equals("") || !pwd2.equals("")
                ){
                    Toast.makeText(SignupActivity1.this,"First step successfully completed  ",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getBaseContext(), SignupActivity2.class);
                    intent.putExtra("First_name", firstNm);
                    intent.putExtra("Last_name", lastNm);
                    intent.putExtra("Email", email);
                    intent.putExtra("Password", pwd1);

                    startActivity(intent);
                    finish();
                }

            }
        });
    }

}

