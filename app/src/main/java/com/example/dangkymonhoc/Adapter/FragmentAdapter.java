package com.example.dangkymonhoc.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dangkymonhoc.GiaoDien.FragmentThree;
import com.example.dangkymonhoc.GiaoDien.TrangThaiActivity;
import com.example.dangkymonhoc.Model.TrangThaiDuyet;
import com.example.dangkymonhoc.R;
import java.util.ArrayList;

public class FragmentAdapter extends BaseAdapter {
    Context context;
    ArrayList<TrangThaiDuyet> list;
    String idsv;
    TextView tvMaMonHoc,tvMonHoc,tvLopHoc,tvGiangVien,tvCaHoc,tvTrangThai;
    public FragmentAdapter(Context context, ArrayList<TrangThaiDuyet> list) {
        this.context = context;

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
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        convertView = inflater.inflate(R.layout.one_item_fragment,null);

        tvMaMonHoc = convertView.findViewById(R.id.tvMaMonHoc);
        tvMonHoc = convertView.findViewById(R.id.tvMonHoc);
        tvLopHoc = convertView.findViewById(R.id.tvLopHoc);

        tvTrangThai = convertView.findViewById(R.id.tvXacNhan);
        final LinearLayout layout =convertView.findViewById(R.id.layout);

        tvMaMonHoc.setText(list.get(position).getMaMonHoc());
        tvMonHoc.setText(list.get(position).getTenMonHoc());
        tvLopHoc.setText(list.get(position).getTenLopHoc());
        tvTrangThai.setText(list.get(position).getTrangThaiDuyet());
//        Log.d("SIZE",String.valueOf(list.size()));
        if (Integer.parseInt(list.get(position).getIdTrangThai()) == 1) {
            layout.setBackgroundColor(Color.parseColor("#336633"));
        }


        return convertView;
    }
}
