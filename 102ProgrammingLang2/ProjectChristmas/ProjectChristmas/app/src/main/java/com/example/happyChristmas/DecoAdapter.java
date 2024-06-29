package com.example.happyChristmas;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DecoAdapter extends BaseAdapter {
    ArrayList<ItemDeco> decoList = new ArrayList<>();

    public DecoAdapter(ArrayList<ItemDeco> decoList) {
        this.decoList = decoList;
    }

    @Override
    public int getCount() {
        return decoList.size();
    }

    @Override
    public ItemDeco getItem(int i) {
        return decoList.get(i);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        Context context = parent.getContext();
        View convertView = view;

        //레이아웃 파일을 실제 뷰 객체로 인스턴스화
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.gridview_item, parent, false);

        TextView decoName = (TextView) convertView.findViewById(R.id.nameDeco);
        ImageView decoImg = (ImageView) convertView.findViewById(R.id.imgDeco);

        ItemDeco itemDeco = getItem(i);
        decoName.setText(itemDeco.getName());
        decoImg.setImageResource(itemDeco.getImage());

        return convertView;
    }
}
