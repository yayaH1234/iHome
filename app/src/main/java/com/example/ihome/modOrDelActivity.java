package com.example.ihome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;



public class modOrDelActivity extends AppCompatActivity {


    private Button supp;
    private Button modifff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delormod);




        supp = (Button) findViewById(R.id.dellll);

        modifff = (Button) findViewById(R.id.modf);


        supp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(modOrDelActivity.this, SuppressMaisActivity.class);

                startActivity(intent);
            }
        });
        modifff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(modOrDelActivity.this, ModiffMaisActivity.class);

                startActivity(intent);
            }
        });

    }
}
