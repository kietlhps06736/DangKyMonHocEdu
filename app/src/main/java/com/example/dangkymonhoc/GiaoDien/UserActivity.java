package com.example.dangkymonhoc.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dangkymonhoc.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserActivity extends AppCompatActivity {
    String maSV;
    TextView TenSV,MaSV,Email,GioiTinh,Phone,DiaChi,Nganh,TrangThai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user2);
        Intent intent = getIntent();
        maSV = intent.getStringExtra("maSV");
        Log.d("maSvUser",maSV);
        anhxa();
        getUserInfo();
    }

    private void anhxa() {
        TenSV = findViewById(R.id.tvTenSV);
        MaSV = findViewById(R.id.tvMSSV);
        Email = findViewById(R.id.tvEmail);
        GioiTinh = findViewById(R.id.tvGioiTinh);
        Phone = findViewById(R.id.tvSdt);
        DiaChi = findViewById(R.id.tvDiachi);
        Nganh = findViewById(R.id.tvNganh);
        TrangThai = findViewById(R.id.tvTrangThai);
    }

    private void getUserInfo() {
        String url = "https://dangkymonhoc.000webhostapp.com/API/getSinhVien.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG",response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            if (jsonObject.getInt("resultCode") == 1){
                                Toast.makeText(UserActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                TenSV.setText(jsonObject.getString("TenSinhVien"));
                                MaSV.setText(jsonObject.getString("MaSinhVien"));
                                Email.setText(jsonObject.getString("Email"));
                                GioiTinh.setText(jsonObject.getString("GioiTinh"));
                                Phone.setText(jsonObject.getString("Phone_Number"));
                                DiaChi.setText(jsonObject.getString("DiaChi"));
                                Nganh.setText(jsonObject.getString("TenNganh"));
                                TrangThai.setText(jsonObject.getString("HocKy"));

//
                            }else {
                                Toast.makeText(UserActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("L",error.toString());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("maSV",maSV);
                return params;
            }

        };
        requestQueue.add(stringRequest);
    }
}