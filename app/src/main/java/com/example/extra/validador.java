package com.example.extra;

import android.text.TextUtils;
import android.widget.TextView;
import com.example.modelo.objetoValidador;
import java.util.HashMap;
import java.util.Map;

public class validador {
    public validador()
    {

    }

    public Map<String,Object> validateTextView(objetoValidador[] arrayValidador)
    {
        Map<String,Object> mapa = new HashMap<>();

        for (objetoValidador obj: arrayValidador) {

            if(obj.getCaja() instanceof TextView )
            {
                TextView objTextView =  (TextView)obj.getCaja();

                String dataString = objTextView.getText().toString();

                if (TextUtils.isEmpty(dataString)) {
                    objTextView.setError("Parametro Vacio");
                    return null;
                }
                else
                {
                    mapa.put(obj.getCampo(),toObjectData(dataString,obj.getClase()));
                }
            }
            else
            {
                return null;
            }
        }

        return mapa;
    }

    public Object toObjectData(String data,Class type)
    {
        Object obj = null;

        switch(type.getSimpleName())
        {
            case "String":
            {
                obj = data;
                break;
            }
            case "Integer":
            {
                obj = Integer.parseInt(data);
                break;
            }

        }

        return obj;

    }

}
