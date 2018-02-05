package com.lvxv.billy.mager;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_profile, container, false);

        SharedPreferences data = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String Name = data.getString("UserName", "Miko");
        String  Email = data.getString("Email", "user@mage.com");
        String  NoTelp = data.getString("NoTelp", "5458593");
        String  Address = data.getString("Address", "Jl. Teknik Komputer");

        TextView name = (TextView) rootview.findViewById(R.id.name);
        TextView email = (TextView) rootview.findViewById(R.id.email);
        TextView notelp = (TextView) rootview.findViewById(R.id.notelp);
        TextView alamat = (TextView) rootview.findViewById(R.id.address);

        name.setText(Name);
        email.setText(Email);
        notelp.setText(NoTelp);
        alamat.setText(Address);

        Button SendMessage = (Button) rootview.findViewById(R.id.edit);
        SendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent( getContext(), EditProfileActivity.class);
               // intent.putExtra("ShopCat",gridViewString[+i]);
                startActivity(intent);

            }
        });

        return rootview;
    }

}
