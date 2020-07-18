package com.example.dangkymonhoc.GiaoDien;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dangkymonhoc.Adapter.FragmentAdapter;
import com.example.dangkymonhoc.Model.TrangThaiDuyet;
import com.example.dangkymonhoc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FragmentTwo extends Fragment {
    FragmentAdapter fragmentAdapter;
    ArrayList<TrangThaiDuyet> listTrangThaiDuyet;
    ListView lvTwo;
    String idSV;
    public static FragmentTwo newInstance() {
        return new FragmentTwo();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        lvTwo = (ListView) view.findViewById(R.id.list_item_Two);
        TrangThaiActivity activity = (TrangThaiActivity) getActivity();
        idSV = activity.getMyId();
        getMonDaDuyet();
        return view;

    }
    private  void getMonDaDuyet(){
        String url="https://dangkymonhoc.000webhostapp.com/API/getMonHocTheoTrangThaiDaDuyet.php";
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if (jsonObject.getInt("resultCode")==1){
                                listTrangThaiDuyet = new ArrayList<TrangThaiDuyet>();

                                for (int i=0;i<jsonArray.length();i++){
                                    JSONObject data = jsonArray.getJSONObject(i);
                                    TrangThaiDuyet trangThaiDuyet = new TrangThaiDuyet();

                                    trangThaiDuyet.setMaMonHoc(data.getString("MaMonHoc"));
                                    trangThaiDuyet.setTenMonHoc(data.getString("MonHoc"));
                                    trangThaiDuyet.setTenLopHoc(data.getString("LopHoc"));
                                    trangThaiDuyet.setTrangThaiDuyet(data.getString("TrangThaiDuyet"));

                                    listTrangThaiDuyet.add(trangThaiDuyet);

                                }
                                fragmentAdapter = new FragmentAdapter(getContext(),listTrangThaiDuyet);
                                lvTwo.setAdapter(fragmentAdapter);


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

//                        fragmentAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("idSinhVien",idSV);
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }
}

