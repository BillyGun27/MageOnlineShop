package com.lvxv.billy.mager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ProductActivity extends AppCompatActivity {
    ImageLoader mImageLoader;
    NetworkImageView mNetworkImageView;

    String ProductName ;
    String ThumbnailUrl ;
    String Price ;
    String Stock;
    int ReleaseYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Intent intent = getIntent();
        ProductName = intent.getStringExtra("ProductName");
        ThumbnailUrl = intent.getStringExtra("ThumbnailUrl");
        Price = intent.getStringExtra("Price");
        Stock = intent.getStringExtra("Stock");
        ReleaseYear = intent.getIntExtra("ReleaseYear",2000);

        TextView TextProduct = (TextView) findViewById(R.id.productName);
        TextView TextPrice = (TextView) findViewById(R.id.price);
        TextView TextStock = (TextView) findViewById(R.id.stock);
        TextView TextYear = (TextView) findViewById(R.id.year);

        // Get the NetworkImageView that will display the image.
        mNetworkImageView = (NetworkImageView) findViewById(R.id.thumbnail);

// Get the ImageLoader through your singleton class.
        mImageLoader = VolleySingleton.getInstance(this).getImageLoader();

// Set the URL of the image that should be loaded into this view, and
// specify the ImageLoader that will be used to make the request.
        mNetworkImageView.setImageUrl(ThumbnailUrl, mImageLoader);

        TextProduct.setText(ProductName);
        TextPrice.setText(Price);
        TextStock.setText(Stock);
        TextYear.setText("Keluaran Tahun " + String.valueOf(ReleaseYear));
    }

    public void buy(View view){
        SharedPreferences data = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        final String  Email = data.getString("Email", "user@mage.com");

        String url = "http://inmost.azurewebsites.net/mager/buy.php";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("Error, Please try again")){
                            Toast toast = Toast.makeText(getApplication(), response ,Toast.LENGTH_SHORT);
                            toast.show();

                        }else {
                            Intent intent = new Intent( getApplicationContext(), ConfirmPurchaseActivity.class);
                            startActivity(intent);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTextView.setText("That didn't work!");
                VolleyLog.e("Error : ",error);
                Toast toast = Toast.makeText(getApplication(), "No Connection" ,Toast.LENGTH_SHORT);
                toast.show();



            }
        }){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", Email);
                params.put("name", ProductName );
                params.put("image", ThumbnailUrl);
                params.put("price", Price);
                params.put("year", String.valueOf(ReleaseYear));

                return params;
            }

        };

        // Add the request to the RequestQueue.
        // Access the RequestQueue through your singleton class.
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);


    }
    public void cancel(View view){
        finish();
        // Intent intent = new Intent( getApplicationContext(), ShopActivity.class);
       // startActivity(intent);
    }


}
