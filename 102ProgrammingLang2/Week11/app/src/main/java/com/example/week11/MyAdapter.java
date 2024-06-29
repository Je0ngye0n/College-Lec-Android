package com.example.week11;

import android.content.Context;
import android.media.Image;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends ArrayAdapter<MyData> {

    ArrayList<MyData> items;

    public MyAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MyData> objects) {
        super(context, resource, objects);
        items = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_layout, parent, false);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.itemImageView);
        TextView nameView = (TextView) convertView.findViewById(R.id.itemNameView);
        TextView ratingView = (TextView) convertView.findViewById(R.id.itemRatingView);

        //뷰에 해당 내용 채워 넣음
        MyData item = items.get(position);
        imageView.setImageResource(item.imageId);
        nameView.setText(item.name);
        ratingView.setText(String.valueOf(item.rating));

        return convertView;
    }
}
