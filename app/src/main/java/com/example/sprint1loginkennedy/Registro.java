package com.example.sprint1loginkennedy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Registro extends AppCompatActivity implements View.OnClickListener {
    EditText etnombre, etcontraeña, etcedula;
    Button btn_registro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etnombre= findViewById(R.id.EditT_nombre);
        etcontraeña= findViewById(R.id.EditT_contraseña);
        etcontraeña= findViewById(R.id.EditT_cedula);

        btn_registro = findViewById(R.id.BTN_RegisCompleto);

        btn_registro.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final String nombre=etnombre.getText().toString();
        final int contraseña= Integer.parseInt(etcontraeña.getText().toString());
        final int cedula= Integer.parseInt(etcedula.getText().toString());

        Response.Listener<String> respoListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonReponse = new JSONObject(response);
                    boolean success= jsonReponse.getBoolean("success");

                    if (success){
                        Intent intent = new Intent(Registro.this, MainActivity.class);
                        Registro.this.startActivity(intent);
                    }else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                        builder.setMessage("error registro")
                                .setNegativeButton("Retry",null)
                                .create().show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        RegisterRequest registerRequest= new RegisterRequest( nombre, contraseña, cedula,respoListener);
            RequestQueue queue = Volley.newRequestQueue(Registro.this);
            queue.add(registerRequest);
    }
}
