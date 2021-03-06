package com.example.book.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.book.Object.Product;
import com.example.book.R;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CustomAdapterProductSeen extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<Product> data;


    public CustomAdapterProductSeen(Context context, int resource, ArrayList<Product> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //
        NumberFormat currentLocale = NumberFormat.getInstance();
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);
        //
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, null);

            viewHolder = new ViewHolder();
            viewHolder.tenSach = convertView.findViewById(R.id.txtTenSach2);
            viewHolder.imgHinhAnh = convertView.findViewById(R.id.imgHinhAnhSach2);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Product pc = data.get(position);
        viewHolder.tenSach.setText(pc.getTenSanPham());
        Picasso.get().load(pc.getHinhAnh().toString()).into(viewHolder.imgHinhAnh);
        return convertView;
    }

    // viewholder
    private static class ViewHolder {
        TextView tenSach;
        ImageView imgHinhAnh;
    }
}
