package com.example.loginnaview_rt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import modelo.Modelo;

public class MainActivity extends AppCompatActivity {

    TextView txtUsuario;
    TextView txtContrasenia;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUsuario = findViewById(R.id.txtuser);
        txtContrasenia = findViewById(R.id.txtpass);
    }

    public void validarLogin(View view) {
        String url = "https://evaladmin.uteq.edu.ec/ws/listadoaevaluar.php?e=";
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, url , null ,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonarr = response.getJSONArray("");
                            int size = jsonarr.length();
                            JSONObject json_transform;
                            if (size > 0) {
                                Modelo mod;
                                for (int i = 0; i < size; i++) {
                                    json_transform = jsonarr.getJSONObject(i);
                                    mod = new Modelo(json_transform.getString("usuario"),
                                                     json_transform.getString("contrasenia"),
                                                     json_transform.getString("tipo_usuario"));
                                    if (mod.getUsuario().equalsIgnoreCase(txtUsuario.getText().toString())){
                                        if (mod.getContrasenia().equals(txtContrasenia.getText().toString())){
                                            Intent intent = new Intent(MainActivity.this, PrincipalActivity.class);
                                            Bundle b = new Bundle();

                                            b.putSerializable("Modelo", mod);

                                            intent.putExtras(b);
                                            startActivity(intent);
                                        }
                                    } else {
                                        Toast.makeText(MainActivity.this,
                                                         "ERROR DE INICIO DE SESIÓN",
                                                              Toast.LENGTH_LONG).show();

                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", String.valueOf(error));
                    }
                }
        );
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(request);
        } else {
            requestQueue.add(request);
        }
    }

}
