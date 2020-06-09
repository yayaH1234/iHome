package com.example.ihome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ModiffMaisActivity extends AppCompatActivity {


    private Button modifff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moddmaissu);
        Bundle extras = getIntent().getExtras();
        Toast.makeText(ModiffMaisActivity.this,"Your email "+extras.getString("email"),Toast.LENGTH_LONG).show();
    modifff=(Button)findViewById(R.id.valid2);
    modifff.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ModiffMaisActivity.this,ModiffMaisActivity1.class);

            startActivity(intent);
            finish();

        }
    });

    }
}
