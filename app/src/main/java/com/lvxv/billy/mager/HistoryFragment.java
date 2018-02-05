package com.lvxv.billy.mager;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    ImageLoader mImageLoader;

    private List<Product> movieList = new ArrayList<Product>();
    private ListView listView;
    private ProductAdapter adapter;


    private String url ;

    public HistoryFragment() {
        // Required empty public constructor
    }

    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_history, container, false);

        SharedPreferences data = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String  Email = data.getString("Email", "user@mage.com");

        url = "http://inmost.azurewebsites.net/mager/hist.php?email="+Email;

        // Get the ImageLoader through your singleton class.
        mImageLoader = VolleySingleton.getInstance(getContext()).getImageLoader();

        listView = (ListView) rootview.findViewById(R.id.lv);
        adapter = new ProductAdapter(getContext(), movieList);
        listView.setAdapter(adapter);

        showList();

        // Inflate the layout for this fragment
        return rootview;
    }

    public void showList(){
        // Creating volley request obj
        JsonArrayRequest movieReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Product movie = new Product();
                                movie.ProductName = obj.getString("name") ;
                                movie.thumbnailUrl = obj.getString("image") ;
                                movie.Price = obj.getString("price");
                                movie.Stock = obj.getString("process");
                                movie.ReleaseYear = obj.getInt("releaseYear");



                                // adding movie to movies array
                                movieList.add(movie);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(MainActivity.class.getSimpleName(), "Error: " + error.getMessage());


            }
        });

        // Adding request to request queue
        VolleySingleton.getInstance(getContext()).addToRequestQueue(movieReq);
    }


}
