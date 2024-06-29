package com.example.myrecycler;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    ArrayList<Data> items;

    static OnMyItemClickListener listener = null; //리스너 멤버 변수 선언

    public MyAdapter(ArrayList<Data> items, OnMyItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Data item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //내부 클래스로 ViewHolder 생성
    static public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        TextView nameView, ratingView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            nameView = (TextView) itemView.findViewById(R.id.textView3);
            ratingView = (TextView) itemView.findViewById(R.id.textView4);
            //클릭 이벤트 등록
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //클릭 되었을 때 어댑터에 등록되어 있는 리스너를 호출
                    int position = getAdapterPosition();
                    if (listener != null) {
                        listener.OnItemClick(ViewHolder.this, v, position);
                    }
                }
            });
        }

        void setItem(Data item) {
            imageView.setImageResource(item.imageId);
            nameView.setText(item.name);
            ratingView.setText(String.format("%.1f", item.rating));
        }
    }
}
