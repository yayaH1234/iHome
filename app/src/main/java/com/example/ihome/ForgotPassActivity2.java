package com.example.ihome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ForgotPassActivity2 extends AppCompatActivity {
    private EditText quest;
    private EditText reply;
    private EditText password;
    private Button valid;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgpass2);

        quest = (EditText) findViewById(R.id.qstsec);
        reply = (EditText) findViewById(R.id.emailreponse);
        password = (EditText) findViewById(R.id.pwd);
        valid = (Button) findViewById(R.id.valid);

        valid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ForgotPassActivity2.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
