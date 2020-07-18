package com.example.dangkymonhoc.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dangkymonhoc.Model.NganhHoc;
import com.example.dangkymonhoc.GiaoDienDangKy.MonHocActivity;
import com.example.dangkymonhoc.R;

import java.util.ArrayList;

public class NganhHocAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<NganhHoc> list;
    TextView tvNganh;

    public NganhHocAdapter(Context context, int layout, ArrayList<NganhHoc> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(layout, null);

        tvNganh = convertView.findViewById(R.id.tv_tenNganh);
        tvNganh.setText(list.get(position).getTenNganh());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("aaa", String.valueOf(list.get(position).getId()));
                Log.d("aaa", list.get(position).getTenNganh());
                Intent intent = new Intent(context, MonHocActivity.class);
                intent.putExtra("idSV", list.get(position).getIdSV());
                intent.putExtra("idNganh", String.valueOf(list.get(position).getId()));
                v.getContext().startActivity(intent);
            }
        });
        return convertView;
    }
}
