package com.example.sprint1loginkennedy;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest{

    private static final String REGISTER_REQUEST_URL="http://192.168.0.102/Register.php";
    private Map<String,String> params;
    public RegisterRequest(String nombre, int contraseña, int cedula, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener,null);
        params=new HashMap<>();
        params.put("nombre",nombre);
        params.put("contraseña", contraseña+"");
        params.put("cedula",cedula+"");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}


