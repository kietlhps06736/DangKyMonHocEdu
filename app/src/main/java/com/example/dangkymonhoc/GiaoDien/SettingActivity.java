package com.example.dangkymonhoc.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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

public class SettingActivity extends AppCompatActivity {
    private RequestQueue tQueue;
    TextView tvMssv,tvTenSV,tvUserSV,tvEditPass,tvLanguage;
    int IdSV;
    String maSV, TenSinhVien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        tvMssv = findViewById(R.id.tvMSSV);
        tvTenSV = findViewById(R.id.tvTen);
        tvUserSV = findViewById(R.id.tvUser);
        tvLanguage= findViewById(R.id.tvLanguage);
        tvEditPass = findViewById(R.id.tvEditPassword);
        Intent intent = getIntent();
        maSV = intent.getStringExtra("maSV");
        Log.d("AAA",maSV);
        getUser();
        tvLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SettingActivity.this, LanguageActivity.class);
                startActivity(i);
            }
        });

        tvEditPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SettingActivity.this, EditPassActivity.class);
                startActivity(i);
            }
        });


        tvUserSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SettingActivity.this, UserActivity.class);
                i.putExtra("maSV",maSV);
                startActivity(i);

            }
        });



    }

    private void getUser() {
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
                                tvTenSV.setText(TenSinhVien);
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
}
