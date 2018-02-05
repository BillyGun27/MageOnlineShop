package com.lvxv.billy.mager;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    GridView androidGridView;


    //set title to main menu
    String[] gridViewString = {
            "Gadget", "Movie", "Pulsa"
    } ;


    //set icon to main menu
    int[] gridViewImageId = {
            R.drawable.ic_gadget ,R.drawable.ic_book ,R.drawable.ic_pulsa
    };


    CarouselView carouselView;

        int[] sampleImages = {R.drawable.iklan1 , R.drawable.iklan2, R.drawable.iklan3
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_home, container, false);

        GridViewAdapter adapterViewAndroid = new GridViewAdapter(getContext(), gridViewString, gridViewImageId);
        androidGridView=(GridView) rootView.findViewById(R.id.grid_view_image_text);
        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
                Toast.makeText(getContext() , "GridView Item: " + gridViewString[+i], Toast.LENGTH_LONG).show();
                Intent intent;
                intent = new Intent( getContext(), ShopActivity.class);
                intent.putExtra("ShopCat",gridViewString[+i]);
                startActivity(intent);
                /*
                switch (gridViewString[+i]){
                    case "Gadget":
                        intent = new Intent( getContext(), ShopActivity.class);
                        startActivity(intent);
                        break;
                    case "Movie":
                        //intent = new Intent( getApplicationContext(), PantauIMBActivity.class);
                        //startActivity(intent);
                        break;
                    case "Pulsa":
                        //intent = new Intent( getApplicationContext(), HistoryActivity.class);
                        //startActivity(intent);
                        break;


                }*/

            }
        });

        //carousel
        carouselView = (CarouselView) rootView.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length+1);

        carouselView.setImageListener(imageListener);


        return rootView;

    }


    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            if(position<3){
                imageView.setImageResource(sampleImages[position]);
            }else{
                Picasso.with(getContext()).load("http://i.imgur.com/7spzG.png").into(imageView);
            }


        }
    };


}
