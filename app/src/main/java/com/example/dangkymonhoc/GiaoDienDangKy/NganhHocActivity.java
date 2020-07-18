package com.example.dangkymonhoc.GiaoDienDangKy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dangkymonhoc.Adapter.NganhHocAdapter;
import com.example.dangkymonhoc.Model.NganhHoc;
import com.example.dangkymonhoc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NganhHocActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<NganhHoc> list;
    NganhHocAdapter nganhHocAdapter;
//    String idSV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nganhhoc);
        listView = findViewById(R.id.lvNganhHoc);
        Intent intent = getIntent();
//        idSV = intent.getStringExtra("idSV");
//        Log.d("idSVInNganhHoc", idSV);
        getNganhHoc();
    }
    private void getNganhHoc(){
        String url = "https://dangkymonhoc.000webhostapp.com/API/getNganhHoc.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            if (jsonObject.getInt("resultCode") == 1){
                                Toast.makeText(NganhHocActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                list = new ArrayList<NganhHoc>();
                                for (int i=0;i<jsonArray.length();i++){

                                    JSONObject data = jsonArray.getJSONObject(i);
                                    NganhHoc nganhHoc = new NganhHoc();
//                                    nganhHoc.setIdSV(idSV);
                                    nganhHoc.setId(data.getInt("IdNganhHoc"));
                                    nganhHoc.setTenNganh(data.getString("NganhHoc"));
                                    list.add(nganhHoc);
                                }
                                nganhHocAdapter = new NganhHocAdapter(NganhHocActivity.this,R.layout.items_nganhhoc,list);
                                listView.setAdapter(nganhHocAdapter);

                            }else {
                                Toast.makeText(NganhHocActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(NganhHocActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(stringRequest);
    }
}
