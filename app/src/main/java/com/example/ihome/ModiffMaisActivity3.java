package com.example.ihome;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ModiffMaisActivity3 extends AppCompatActivity {


    private Button supp;
    private Button modifff;

    private String mais_name;
    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmaison3);

        Bundle extras = getIntent().getExtras();

        mais_name =extras.getString("nom_maison");
        email =extras.getString("email");
        Toast.makeText(ModiffMaisActivity3.this,"Your email "+extras.getString("email")+" mais nom "+mais_name ,Toast.LENGTH_LONG).show();



    }
}
