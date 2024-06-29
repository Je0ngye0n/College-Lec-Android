package com.example.myrecycler;

import android.view.View;

public interface OnMyItemClickListener {
    public void OnItemClick(MyAdapter.ViewHolder holder, View view, int position);
}
