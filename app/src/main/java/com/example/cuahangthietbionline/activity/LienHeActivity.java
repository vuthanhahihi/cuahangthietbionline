package com.example.cuahangthietbionline.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import com.example.cuahangthietbionline.R;
import com.example.cuahangthietbionline.ultil.CheckConnection;

public class LienHeActivity extends AppCompatActivity {
    Toolbar toolbarlaptop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lien_he);
        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
            Anhxa();
            ActionToolbar();
        }else {
            CheckConnection.showToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại Internet");
            finish();
        }
    }

    private void Anhxa() {
        toolbarlaptop = findViewById(R.id.toolbarlienhe);
    }
    private void ActionToolbar() {
        setSupportActionBar(toolbarlaptop);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarlaptop.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}