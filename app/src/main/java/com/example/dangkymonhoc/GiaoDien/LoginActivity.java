package com.example.dangkymonhoc.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dangkymonhoc.MainActivity;
import com.example.dangkymonhoc.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText edtUser,edtPass;
    Button btnLogin;
    TextView tvResetPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anhxa();



    }

    private void anhxa() {
        tvResetPass = findViewById(R.id.tvResetPass);
        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);
        btnLogin = findViewById(R.id.btnLogin);

        tvResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, ResetPassActivity.class);
                startActivity(i);
                finish();

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FunctionLogin();
            }
        });

    }

    private void FunctionLogin(){

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://dangkymonhoc.000webhostapp.com/API/loginUser.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG",response);
                        String message = "";
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            if (jsonObject.getInt("resultCode") == 1){
                                message = jsonObject.getString("message");
                                Log.d("bbb",jsonObject.getString("MaSinhVien"));
                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                                intent.putExtra("maSV",jsonObject.getString("MaSinhVien"));
                                startActivity(intent);

                            }else {
                                message = jsonObject.getString("message");
                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
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
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username",edtUser.getText().toString().trim());
                params.put("password",edtPass.getText().toString().trim());
                return params;

            }
        };
        requestQueue.add(stringRequest);
//



    }
}