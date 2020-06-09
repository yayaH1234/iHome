package com.example.ihome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ModiffMaisActivity1  extends AppCompatActivity {


    private Button supp;
    private Button modifff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modiffic1);
        modifff=(Button)findViewById(R.id.next);
        modifff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModiffMaisActivity1.this,ModiffMaisActivity2.class);

                startActivity(intent);
                finish();

            }
        });
    }
}
