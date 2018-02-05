package com.lvxv.billy.mager;

import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity {

    private String url ;//= "https://api.androidhive.info/json/movies.json";

    ImageLoader mImageLoader;
    //NetworkImageView mNetworkImageView;
   // private static final String IMAGE_URL =
          //  "http://developer.android.com/images/training/system-ui.png";
   //   "http://i.imgur.com/7spzG.png";

    private List<Product> movieList = new ArrayList<Product>();
    private ListView listView;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        Intent intent = getIntent();
        String ShopCat = intent.getStringExtra("ShopCat");

        switch (ShopCat){
            case "Gadget":
                url = "http://inmost.azurewebsites.net/mager/gadget.php";
                break;
            case "Movie":
                url = "http://inmost.azurewebsites.net/mager/movie.php";
                break;
            case "Pulsa":
                url = "http://inmost.azurewebsites.net/mager/pulsa.php";
                break;


        }


// Get the NetworkImageView that will display the image.
     //   mNetworkImageView = (NetworkImageView) findViewById(R.id.imgn);

// Get the ImageLoader through your singleton class.
        mImageLoader = VolleySingleton.getInstance(this).getImageLoader();

// Set the URL of the image that should be loaded into this view, and
// specify the ImageLoader that will be used to make the request.
      //  mNetworkImageView.setImageUrl(IMAGE_URL, mImageLoader);

     //   showData();

        listView = (ListView) findViewById(R.id.lv);
        adapter = new ProductAdapter(this, movieList);
        listView.setAdapter(adapter);

        showList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                String ProductName = movieList.get(position).ProductName;
                String ThumbnailUrl = movieList.get(position).thumbnailUrl;
                String Price = movieList.get(position).Price;
                String Stock = movieList.get(position).Stock;
                int ReleaseYear = movieList.get(position).ReleaseYear;

                    Intent intent = new Intent( getApplicationContext(), ProductActivity.class);
                intent.putExtra("ProductName",ProductName);
                intent.putExtra("ThumbnailUrl", ThumbnailUrl);
                intent.putExtra("Price",Price);
                intent.putExtra("Stock",Stock);
                intent.putExtra("ReleaseYear",ReleaseYear);
                startActivity(intent);


                Toast.makeText(getBaseContext(), ProductName, Toast.LENGTH_LONG).show();

            }
        });


    }

public void showList(){
    // Creating volley request obj
    JsonArrayRequest productReq = new JsonArrayRequest(url,
            new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {


                    // Parsing json
                    for (int i = 0; i < response.length(); i++) {
                        try {

                            JSONObject obj = response.getJSONObject(i);
                            Product product = new Product();
                            product.ProductName = obj.getString("name") ;
                            product.thumbnailUrl = obj.getString("image") ;
                            product.Price = obj.getString("price");
                            product.Stock = obj.getString("stock");
                            product.ReleaseYear = obj.getInt("releaseYear");



                            // adding movie to movies array
                            movieList.add(product);

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
    VolleySingleton.getInstance(getApplication()).addToRequestQueue(productReq);
}
/*
public void showData(){

    Toast toast = Toast.makeText(getApplication(), "Response: init" ,Toast.LENGTH_SHORT);
    toast.show();
    final TextView sample = (TextView) findViewById(R.id.sample);
    //String url = "http://192.168.43.51/datamanager/displaychat.php";
    String url = "http://linkdb.000webhostapp.com/displaychat.php";

    JsonArrayRequest jsArrRequest = new JsonArrayRequest
            (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response)  {
                    try {

                        //Toast toast = Toast.makeText(getContext(), Os.get("username").toString()  ,Toast.LENGTH_SHORT);
                        //toast.show();
                            JSONObject chatdata  = response.getJSONObject(2);
                            sample.setText(chatdata.getString("email").toString()+chatdata.getString("username").toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }




                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    // TODO Auto-generated method stub
                    //mTxtDisplay.setText("Response: Error");
                    Toast toast = Toast.makeText(getApplication(), "Response: Error Chat" ,Toast.LENGTH_SHORT);
                    toast.show();
                }
            });

// Access the RequestQueue through your singleton class.
    VolleySingleton.getInstance(getApplication()).addToRequestQueue(jsArrRequest);
}*/

}
