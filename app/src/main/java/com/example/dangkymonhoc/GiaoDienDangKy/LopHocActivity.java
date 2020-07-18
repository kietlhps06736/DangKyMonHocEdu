package com.example.dangkymonhoc.GiaoDienDangKy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dangkymonhoc.Adapter.LopHocAdapter;
import com.example.dangkymonhoc.Model.LopHoc;
import com.example.dangkymonhoc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LopHocActivity extends AppCompatActivity {
    ListView lvLopHoc;
    LopHocAdapter lopHocAdapter;
    ArrayList<LopHoc> listLopHoc;
    String idMonHoc,idSV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lop_hoc);
        Intent intent = getIntent();
        idMonHoc = intent.getStringExtra("idMonHoc");
//        idSV = intent.getStringExtra("idSV");
//        Log.d("idSVInLopHoc",idSV);
        lvLopHoc = findViewById(R.id.lvLopHoc);
        getLopHoc();
    }
    private void getLopHoc() {
        String url = "https://dangkymonhoc.000webhostapp.com/API/getLopHocTheoMon.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            if (jsonObject.getInt("resultCode") == 1){
                                Toast.makeText(LopHocActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                listLopHoc = new ArrayList<LopHoc>();
                                for (int i=0;i<jsonArray.length();i++){
                                    JSONObject data = jsonArray.getJSONObject(i);
                                    LopHoc lopHoc = new LopHoc();
//                                    lopHoc.setIdSV(idSV);
                                    lopHoc.setIdLopHoc(data.getInt("Id_LopHoc"));
                                    lopHoc.setLopHoc(data.getString("LopHoc"));
                                    lopHoc.setTenMonHoc(data.getString("MonHoc"));
                                    lopHoc.setGiangVien(data.getString("GiangVien"));
                                    lopHoc.setCaHoc(data.getString("CaHoc"));
                                    listLopHoc.add(lopHoc);

                                }
                                Log.d("vvv",listLopHoc.get(0).getTenMonHoc());
                                lopHocAdapter = new LopHocAdapter(LopHocActivity.this,R.layout.items_lophoc,listLopHoc);
                                lvLopHoc.setAdapter(lopHocAdapter);

                            }else{
                                Toast.makeText(LopHocActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
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
                params.put("idMonHoc",idMonHoc);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
