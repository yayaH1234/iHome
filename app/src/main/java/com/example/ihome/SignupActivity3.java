package com.example.ihome;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity3 extends AppCompatActivity {

    private EditText questSec;
    private EditText repsec;
    private EditText numeT;
    private Button valid;

    private String FirstNm ;
    private String LasstNm ;
    private String Email;
    private String Password ;
    private String numero;
    private Bitmap bitmap;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup3);
        questSec = (EditText) findViewById(R.id.questSec);
        repsec = (EditText) findViewById(R.id.replyquest);
        valid = (Button) findViewById(R.id.signup);

        numeT = (EditText)findViewById(R.id.telephone);

        Bundle extras = getIntent().getExtras();
        FirstNm = extras.getString("First_name");
        LasstNm = extras.getString("Last_name");
        Email = extras.getString("Email");
        Password = extras.getString("Password");
        numero = extras.getString("Email");
        bitmap = this.getIntent().getParcelableExtra("bitmap");


        String numer=numeT.getText().toString().trim();


        System.out.println("-------------->"+FirstNm+" "+ LasstNm +" "+ Email +" "+Password+"  "+numero+"" +
                "------------------->"+bitmap);

        valid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String qstsec=questSec.getText().toString();
            String rpsc=repsec.getText().toString();




            }
        });
    }
}
