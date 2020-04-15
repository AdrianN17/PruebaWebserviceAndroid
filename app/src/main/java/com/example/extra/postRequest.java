package com.example.extra;

import android.content.Context;
import android.util.Log;

import androidx.arch.core.util.Function;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.conf.configuracion;

import java.util.HashMap;
import java.util.Map;

public class postRequest {

    private Context context;
    private String url;
    private HashMap<String, String> parametros;

    public postRequest(Context context, String url, HashMap<String, String> parametros)
    {
        this.context = context;
        this.url = configuracion.IP + url;
        this.parametros = parametros;
    }

    public void consulta(final Function<String,Void> funcion)
    {
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);

                        if(funcion!=null)
                        {
                            funcion.apply(response);
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                return parametros;
            }
        };
        queue.add(postRequest);
        //System.out.println(postRequest.toString());
    }
}
