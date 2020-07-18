package com.example.dangkymonhoc.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dangkymonhoc.Model.LopHoc;
import com.example.dangkymonhoc.GiaoDienDangKy.FormDangKyActivity;
import com.example.dangkymonhoc.R;


import java.util.ArrayList;

public class LopHocAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<LopHoc> listLopHoc;
    TextView tvLopHoc,tvTenMonHoc,tvGiangVien,tvCaHoc;

    public LopHocAdapter(Context context, int layout, ArrayList<LopHoc> listLopHoc) {
        this.context = context;
        this.layout = layout;
        this.listLopHoc = listLopHoc;
    }

    @Override
    public int getCount() {
        return listLopHoc.size();
    }

    @Override
    public Object getItem(int position) {
        return listLopHoc.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(layout,null);

        tvLopHoc = convertView.findViewById(R.id.tvLopHoc);
        tvTenMonHoc = convertView.findViewById(R.id.tvTenMonHoc);
        tvGiangVien = convertView.findViewById(R.id.tvGiangVien);
        tvCaHoc = convertView.findViewById(R.id.tvCaHoc);

        tvLopHoc.setText(listLopHoc.get(position).getLopHoc());
        tvTenMonHoc.setText(listLopHoc.get(position).getTenMonHoc());
        tvGiangVien.setText(listLopHoc.get(position).getGiangVien());
        tvCaHoc.setText(listLopHoc.get(position).getCaHoc());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FormDangKyActivity.class);
                intent.putExtra("idSV",listLopHoc.get(position).getIdSV());
                intent.putExtra("lopHoc",listLopHoc.get(position).getLopHoc());
                intent.putExtra("monhoc",listLopHoc.get(position).getTenMonHoc());
                intent.putExtra("giangvien",listLopHoc.get(position).getGiangVien());
                intent.putExtra("cahoc",listLopHoc.get(position).getCaHoc());

                v.getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}
