package com.lvxv.billy.mager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ConfirmPurchaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_purchase);
    }

    public void home(View view){
        Intent intent = new Intent( getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
