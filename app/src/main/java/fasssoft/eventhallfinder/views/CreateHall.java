package fasssoft.eventhallfinder.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import fasssoft.eventhallfinder.utils.urlClass;
import fasssoft.eventhallfinder.views.FragmentActivities.ShowHallsListActivity;

public class CreateHall extends AppCompatActivity {
    EditText etHalname, etHallloc, etOwname, etOwnum;
Button Crhal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_hall);
        Intent intent = getIntent();
        etHalname = (EditText) findViewById(R.id.etChHname);
        etOwname = (EditText) findViewById(R.id.etChOwName);
        etOwnum = (EditText) findViewById(R.id.etChMob);
        etHallloc = (EditText) findViewById(R.id.etChLoc);
        Crhal=(Button) findViewById(R.id.btnChCreat);
//        etHalname.setText(intent.getStringExtra("hallnae"))
//        etOwname.setText(intent.getStringExtra("owname"));
//        etOwnum.setText(intent.getStringExtra("ownumber"));
//        etHallloc.setText(intent.getStringExtra("hallloc"));

        ////////
        Crhal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequestPost = new StringRequest(Request.Method.POST, urlClass.apihalldetails, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                   //     Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("hallname", etHalname.getText().toString());
                        params.put("ownername", etOwname.getText().toString());
                        params.put("owner_mobile", etOwnum.getText().toString());
                        params.put("hall_location", etHallloc.getText().toString());
                        return params;
                    }
                };//
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequestPost);
                Intent intent1=new Intent(getApplicationContext(),ShowHallsListActivity.class);
                startActivity(intent1);
            }
        });

        ///////


    }
}
