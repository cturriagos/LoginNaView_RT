package com.example.loginnaview_rt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import modelo.Modelo;

public class MainActivity extends AppCompatActivity {

    EditText txtUsuario;
    EditText txtContrasenia;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUsuario = findViewById(R.id.txtuser);
        txtContrasenia = findViewById(R.id.txtpass);
    }

    public void validarLogin(View view) {
        String url = "https://my-json-server.typicode.com/cturriagos/LoginNaView_RT/users?usuario=" +txtUsuario.getText().toString();
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET, url , null ,
                new com.android.volley.Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            if(response.length() > 0) {
                                JSONObject jsonob = response.getJSONObject(0);
                                Modelo mod;
                                mod = new Modelo(jsonob.getString("usuario"),
                                        jsonob.getString("contrasenia"),
                                        jsonob.getString("tipo_usuario"));
                                if (mod.getUsuario().equalsIgnoreCase(txtUsuario.getText().toString())) {
                                    if (mod.getContrasenia().equals(txtContrasenia.getText().toString())) {
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
