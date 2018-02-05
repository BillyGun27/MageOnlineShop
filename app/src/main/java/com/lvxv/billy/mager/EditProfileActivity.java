package com.lvxv.billy.mager;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
    }

    public  void  save(View view){
        EditText name = (EditText) findViewById(R.id.name);
        EditText email = (EditText) findViewById(R.id.email);
        EditText notelp = (EditText) findViewById(R.id.notelp);
        EditText alamat = (EditText) findViewById(R.id.alamat);

        SharedPreferences data = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();

        editor.putString("UserName", name.getText().toString());
        editor.putString("Email", email.getText().toString());
        editor.putString("NoTelp", notelp.getText().toString());
        editor.putString("Address", alamat.getText().toString());

        // Commit the edits!
        editor.commit();

    }
}
