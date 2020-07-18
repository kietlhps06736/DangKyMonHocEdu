package com.example.dangkymonhoc.GiaoDien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dangkymonhoc.GiaoDienDangKy.NganhHocActivity;

import com.example.dangkymonhoc.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView img_nganhHoc, img_monHoc, img_lichHoc, img_thongTin;
    TextView tvMssv,tvTen;
    String maSV, TenSinhVien;
    private RequestQueue tQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        AnhXa();
        Click();
        tvTen = findViewById(R.id.tv_name);
        tvMssv = findViewById(R.id.tv_mssv);

//        ClickBottom();
        Intent intent = getIntent();
        maSV = intent.getStringExtra("maSV");
        Log.d("AA",maSV);
        getHome();
    }

    private void getHome() {
        tQueue = Volley.newRequestQueue(this);
        String url = "https://dangkymonhoc.000webhostapp.com/API/getSinhVien.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("user",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getInt("resultCode") == 1){

//                                IdSV = jsonObject.getInt("IdSinhVien");
                                maSV = jsonObject.getString("MaSinhVien");
                                TenSinhVien = jsonObject.getString("TenSinhVien");

                                tvMssv.setText(maSV);
                                tvTen.setText(TenSinhVien);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("maSV",maSV);
                return params;

            }
        };
        tQueue.add(stringRequest);

    }


    private void AnhXa(){
        img_nganhHoc = findViewById(R.id.img_nganhHoc);
        img_monHoc = findViewById(R.id.img_monHoc);
        img_lichHoc = findViewById(R.id.img_lichHoc);
        img_thongTin = findViewById(R.id.img_caiDat);
    }

    private void Click(){

        img_nganhHoc.setOnClickListener(this);
        img_monHoc.setOnClickListener(this);
        img_lichHoc.setOnClickListener(this);
        img_thongTin.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_nganhHoc:
                Intent nganhhoc = new Intent(this, NganhHocActivity.class);
                startActivity(nganhhoc);
                break;
            case R.id.img_monHoc:
                Intent monhoc = new Intent(this, TrangThaiActivity.class);
                startActivity(monhoc);
                break;
            case R.id.img_lichHoc:
                Intent lichhoc = new Intent(this, LichHocActivity.class);
                startActivity(lichhoc);
                break;
            case R.id.img_caiDat:
                Intent thongtin = new Intent(this, SettingActivity.class);
                thongtin.putExtra("maSV",maSV);
                startActivity(thongtin);
                break;

        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
