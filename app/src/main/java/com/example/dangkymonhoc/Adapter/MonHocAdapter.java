package com.example.dangkymonhoc.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dangkymonhoc.Model.MonHoc;
import com.example.dangkymonhoc.GiaoDienDangKy.LopHocActivity;
import com.example.dangkymonhoc.R;

import java.util.ArrayList;

public class MonHocAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<MonHoc> listMonHoc;
    TextView tvMonHoc,tvMaMon;
    public MonHocAdapter(Context context, int layout, ArrayList<MonHoc> listMonHoc) {
        this.context = context;
        this.layout = layout;
        this.listMonHoc = listMonHoc;
    }

    @Override
    public int getCount() {
        return listMonHoc.size();
    }

    @Override
    public Object getItem(int position) {
        return listMonHoc.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(layout,null);

        tvMaMon = convertView.findViewById(R.id.tvMaMon);
        tvMonHoc = convertView.findViewById(R.id.tvMonHoc);
        tvMaMon.setText(listMonHoc.get(position).getMaMon());
        tvMonHoc.setText(listMonHoc.get(position).getMonHoc());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LopHocActivity.class);
                intent.putExtra("idMonHoc",String.valueOf(listMonHoc.get(position).getIdMonHoc()));
                intent.putExtra("idSV",listMonHoc.get(position).getIdSV());
                v.getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}
