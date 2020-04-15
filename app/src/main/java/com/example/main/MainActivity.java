package com.example.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.util.Function;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.conf.configuracion;
import com.example.extra.jwtCreador;
import com.example.extra.postRequest;
import com.example.extra.validador;
import com.example.modelo.objetoValidador;
import com.example.modelo.usuario;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.danlew.android.joda.JodaTimeAndroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;


public class MainActivity extends AppCompatActivity {
    validador val;
    jwtCreador jwtcreador;

    protected TextView txtUsuario,txtContrasena;
    protected Button btnLogin;

    private String url = "WebserviceAndroidJWT/public/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        val = new validador();

        JodaTimeAndroid.init(this);

        txtUsuario = (TextView)findViewById(R.id.txtUsuario);
        txtContrasena = (TextView)findViewById(R.id.txtContrasena);

        btnLogin = (Button)findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                objetoValidador[] arrayValidador = new objetoValidador[]{
                    new objetoValidador("user", txtUsuario, String.class),
                    new objetoValidador("pass", txtContrasena, String.class)
                };

                Map mapa= val.validateTextView(arrayValidador);

                if(!mapa.isEmpty())
                {
                    DateTime iat = new DateTime();
                    DateTime ex = iat.plusMinutes(5);
                    DateTime nbf = iat.minusMinutes(1);

                    String jwtString = jwtCreador.crearJwt(configuracion.KEY_DEFAULT,iat,nbf,ex,"Login",mapa);

                    HashMap<String, String> params = new HashMap<>();
                    params.put("Authorization", jwtString);


                    postRequest post = new postRequest(MainActivity.this,url,params);

                    post.consulta(

                        new Function<String,Void>() {
                            @Override
                            public Void apply(String response)
                            {

                                if(!TextUtils.isEmpty(response))
                                {
                                    JSONObject json = null;
                                    try
                                    {
                                        json = new JSONObject(response);

                                        try
                                        {
                                            String jwt = json.getString("Authorization");

                                            String JsonString = jwtCreador.extraerJwt(jwt,configuracion.KEY_DEFAULT);

                                            if(!TextUtils.isEmpty(JsonString))
                                            {
                                                Gson gson = new Gson();
                                                usuario[] usuArray = gson.fromJson(JsonString, usuario[].class);

                                                System.out.println(usuArray[0].getUser());
                                            }

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }




                                }
                                else
                                {

                                }



                                return null;
                            }
                        }

                    );
                }
            }
        });
    }

    /*public void CrearJWT(String user,String pass)
    {*/
        /*byte[] secret = Base64.getDecoder().decode("pG1VvOtDZMtZAylyM+k62Ut1AxQWFUmodq0hj9+TGBs=");*/
        //Key key = Keys.hmacShaKeyFor(secret);

        //Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        /*DateTime at = new DateTime();
        DateTime ex = new DateTime();
        ex = ex.plusMinutes(5);

        String jwt = Jwts.builder()
                .setSubject("Brian Demers")
                .setAudience("video demo")
                .claim("Usuario", user)
                .claim("Contrasena", pass)
                .setIssuedAt(at.toDate())
                .setExpiration(ex.toDate())
                .signWith(Keys.hmacShaKeyFor(secret))
                .compact();*/


        //System.out.println(jwt);


        /*Instant now = Instant.now();

        byte[] secret = Base64.getDecoder().decode("o4OdCNjd8mmDN2+/nfHdIB2ZWta80foXqDx2rouL4nw=");

        String jwt = Jwts.builder()
                .setSubject("Brian Demers")
                .setAudience("video demo")
                .claim("1d20", new Random().nextInt(20) +1)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(100, ChronoUnit.MINUTES)))
                .signWith(Keys.hmacShaKeyFor(secret))
                .compact();

        System.out.println(jwt);

        Jwts<Claims> result = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret))
                .build()
                .parseClaimsJws(jwt);

        System.out.println(result);
        System.out.println("1d20: "+ result.getBody().get("1d20", Integer.class));*/


    //}

}
