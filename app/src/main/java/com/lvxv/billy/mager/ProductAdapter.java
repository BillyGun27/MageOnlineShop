package com.lvxv.billy.mager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by ASUS on 1/6/2018.
 */

public class ProductAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<Product> ProductItems;
    // Get the ImageLoader through your singleton class.
    ImageLoader mImageLoader = VolleySingleton.getInstance(context).getImageLoader();

    public ProductAdapter(Context context, List<Product> productItems) {
        this.context = context;
        this.ProductItems = productItems;
    }

    @Override
    public int getCount() {
        return ProductItems.size();
    }

    @Override
    public Object getItem(int position) {
        return ProductItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.row_product, null);

        if (mImageLoader == null)
            mImageLoader = VolleySingleton.getInstance(context).getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView title = (TextView) convertView.findViewById(R.id.name);
        TextView price = (TextView) convertView.findViewById(R.id.price);
        TextView available = (TextView) convertView.findViewById(R.id.stock);
        TextView year = (TextView) convertView.findViewById(R.id.releaseYear);

        // getting movie data for the row
        Product m = ProductItems.get(position);

        // thumbnail image
        thumbNail.setImageUrl(m.thumbnailUrl, mImageLoader);

        // title
        title.setText(m.ProductName);

        // price
        price.setText("Price: " + m.Price);

        // available
        available.setText(m.Stock);

        // release year
        year.setText("Keluaran Tahun " + String.valueOf(m.ReleaseYear));

        return convertView;

    }
}
