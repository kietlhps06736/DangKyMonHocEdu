package com.example.dangkymonhoc.GiaoDienDangKy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dangkymonhoc.GiaoDien.HomeActivity;
import com.example.dangkymonhoc.GiaoDien.LoginActivity;
import com.example.dangkymonhoc.GiaoDien.SplashActivity;
import com.example.dangkymonhoc.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FormDangKyActivity extends AppCompatActivity {
    String lophoc,monhoc,giangvien,cahoc,idSV;
    ImageView imgback;
    TextView tvLopHoc,tvMonHoc,tvGiangVien,tvCaHoc;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_dang_ky);
        imgback = findViewById(R.id.img_back_dk);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FormDangKyActivity.this, LopHocActivity.class);
                startActivity(i);
                finish();
            }
        });
        Intent intent = getIntent();
        lophoc = intent.getStringExtra("lopHoc");
        monhoc = intent.getStringExtra("monhoc");
        giangvien = intent.getStringExtra("giangvien");
        cahoc = intent.getStringExtra("cahoc");

//        idSV = intent.getStringExtra("idSV");
//        Log.d("ggg",idSV + ' ' + lophoc + ' ' + monhoc + ' ' + giangvien + ' ' + cahoc);
        anhxa();

        tvLopHoc.setText(lophoc);
        tvMonHoc.setText(monhoc);
        tvGiangVien.setText(giangvien);
        tvCaHoc.setText(cahoc);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertMon();
            }
        });
    }
    private void insertMon() {
        String url = "https://dangkymonhoc.000webhostapp.com/API/insertMonHoc.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getInt("resultCode") == 1){
                                Toast.makeText(FormDangKyActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            }else{

                                Toast.makeText(FormDangKyActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERROR",error.toString());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
//                params.put("id",idSV);
                params.put("lophoc",lophoc);
//                Log.d("idsv",idSV + ' '+ lophoc);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void anhxa() {
        tvLopHoc = findViewById(R.id.tvLopHocInForm);
        tvMonHoc = findViewById(R.id.tvMonHocInForm);
        tvGiangVien = findViewById(R.id.tvGiangVienInForm);
        tvCaHoc = findViewById(R.id.tvCaHocInForm);
        btnSave = findViewById(R.id.btnSaveInForm);

    }
}
