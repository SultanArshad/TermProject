package fasssoft.eventhallfinder.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import fasssoft.eventhallfinder.Fragments.ShowHallsListFragment;
import fasssoft.eventhallfinder.R;
import fasssoft.eventhallfinder.utils.urlClass;
import fasssoft.eventhallfinder.views.FragmentActivities.ShowHallsListActivity;

public class EditHallDetail extends AppCompatActivity {
    EditText etHallName, etHallLoc, etHallOwName, etHallOwnum;
    Button btnUp;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hall_detail);
        etHallName = (EditText) findViewById(R.id.etEdHallName);
        etHallOwName = (EditText) findViewById(R.id.etEdOwNaeme);
        etHallOwnum = (EditText) findViewById(R.id.etEdOwNum);
        etHallLoc = (EditText) findViewById(R.id.etEdLoc);
        btnUp = (Button) findViewById(R.id.btnEdUp);
        Intent intent = getIntent();
        etHallName.setText(intent.getStringExtra("hallname"));
        etHallOwName.setText(intent.getStringExtra("hallowname"));
        etHallOwnum.setText(intent.getStringExtra("hallownumber"));
        etHallLoc.setText(intent.getStringExtra("hallloc"));
        id = intent.getIntExtra("hallid", 0);

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequestPost = new StringRequest(Request.Method.PUT, urlClass.apihalldetails + id, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("hallname", etHallName.getText().toString());
                        params.put("ownername", etHallOwName.getText().toString());
                        params.put("owner_mobile", etHallOwnum.getText().toString());
                        params.put("hall_location", etHallLoc.getText().toString());
                        return params;
                    }
                };//
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequestPost);
                Intent i = new Intent(getApplicationContext(), ShowHallsListActivity.class);
                Toast.makeText(getApplicationContext(), "Information Updated Successfuly", Toast.LENGTH_LONG);
                startActivity(i);
            }
        });

    }
}
