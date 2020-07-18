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
import com.example.dangkymonhoc.Adapter.MonHocAdapter;
import com.example.dangkymonhoc.Model.MonHoc;
import com.example.dangkymonhoc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MonHocActivity extends AppCompatActivity {
    String idNganh,idSV;
    ListView lvMonHoc;
    ArrayList<MonHoc> listMonHoc;
    MonHocAdapter monHocAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monhoc);
        Intent intent = getIntent();
        idNganh = intent.getStringExtra("idNganh");
//        idSV = intent.getStringExtra("idSV");
//        Log.d("idSVInMonHoc",idSV);
        lvMonHoc = findViewById(R.id.lvMonHoc);
        getMonHoc();
    }
    private void getMonHoc(){
        String url = "https://dangkymonhoc.000webhostapp.com/API/getMonHocTheoNganh.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("gggg",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if (jsonObject.getInt("resultCode") == 1){
                                Toast.makeText(MonHocActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                listMonHoc = new ArrayList<MonHoc>();
                                for (int i=0;i<jsonArray.length();i++){
                                    JSONObject data = jsonArray.getJSONObject(i);

                                    MonHoc monHoc = new MonHoc();
//                                    monHoc.setIdSV(idSV);
                                    monHoc.setIdMonHoc(data.getInt("IdMonHoc"));
                                    monHoc.setMaMon(data.getString("MaMonHoc"));
                                    monHoc.setMonHoc(data.getString("MonHoc"));
                                    listMonHoc.add(monHoc);
                                }
                                monHocAdapter = new MonHocAdapter(MonHocActivity.this,R.layout.items_monhoc,listMonHoc);
                                lvMonHoc.setAdapter(monHocAdapter);
                            }else{
                                Toast.makeText(MonHocActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
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
                Log.d("AAAA111",idNganh);
                params.put("idNganh",idNganh);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
