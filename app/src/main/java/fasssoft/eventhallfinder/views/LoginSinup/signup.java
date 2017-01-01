package fasssoft.eventhallfinder.views.LoginSinup;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import fasssoft.eventhallfinder.R;

import fasssoft.eventhallfinder.models.Database.DatabaseHelper;
import fasssoft.eventhallfinder.models.User;
import fasssoft.eventhallfinder.utils.urlClass;
import fasssoft.eventhallfinder.views.main;

public class signup extends AppCompatActivity {
    ImageView sback;
    EditText etEmailSup, etPassSup, etHallNameSup, etFnameSup, etLocationSup;
    TextView sup;
    String pName, pEmail, pLocation, pHall, pPass;
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        sback = (ImageView) findViewById(R.id.sback);
        etEmailSup = (EditText) findViewById(R.id.etEmailSup);
        etFnameSup = (EditText) findViewById(R.id.etFnameSup);
        etHallNameSup = (EditText) findViewById(R.id.etHallNameSup);
        etPassSup = (EditText) findViewById(R.id.EtPassSup);
        etLocationSup = (EditText) findViewById(R.id.etLocationSup);
        sup = (TextView) findViewById(R.id.sup);


        sback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(signup.this, main.class);
                startActivity(it);
            }
        });///
        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pName = etFnameSup.getText().toString().trim();
                pEmail = etEmailSup.getText().toString().trim();
                pPass = etPassSup.getText().toString().trim();
                pLocation = etLocationSup.getText().toString().trim();
                pHall = etHallNameSup.getText().toString().trim();
                StringRequest stringRequestPost = new StringRequest(Request.Method.POST, urlClass.apilogintests, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("name", pName);
                        params.put("email", pEmail);
                        params.put("password", pPass);
                        params.put("hallname", pHall);
                        params.put("location", pLocation);
                        return params;
                    }
                };//
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequestPost);

                //////offline
                String strname = etFnameSup.getText().toString();
                String stremail = etEmailSup.getText().toString();
                String strlocation = etLocationSup.getText().toString();
                String strhallanme = etHallNameSup.getText().toString();
                String strpass = etPassSup.getText().toString();


                User u = new User();
                u.setName(strname);
                u.setEmail(stremail);
                u.setLocation(strlocation);
                u.setHallname(strhallanme);
                u.setPassword(strpass);

                helper.insertUser(u);
                //////end offline work


            }
        });///onclik end

    }
}
