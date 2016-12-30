package fasssoft.eventhallfinder.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fasssoft.eventhallfinder.R;
import fasssoft.eventhallfinder.models.DatumHallDetail;
import fasssoft.eventhallfinder.models.DatumLogin;
import fasssoft.eventhallfinder.models.HallDetailPojo;
import fasssoft.eventhallfinder.models.LoginPojo;
import fasssoft.eventhallfinder.utils.urlClass;

public class Wellcome extends AppCompatActivity {
    Button btnShowWel;
    DatumHallDetail[] datumHallDetails;
    ArrayList<DatumHallDetail> datumHallDetailsList;
    HallDetailPojo hallDetailPojo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);
        btnShowWel = (Button) findViewById(R.id.btnShowWel);
        datumHallDetailsList = new ArrayList<>();
        hallDetailPojo = new HallDetailPojo();
        btnShowWel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.GET, urlClass.apihalldetails, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            datumHallDetails = new DatumHallDetail[jsonArray.length()];
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);
                                datumHallDetails[i] = new DatumHallDetail();
                                datumHallDetails[i].setHallname(obj.getString("hallname"));
                                datumHallDetails[i].setOwnername(obj.getString("ownername"));
                                datumHallDetails[i].setOwnerMobile(obj.getString("owner_mobile"));
                                datumHallDetails[i].setHallLocation(obj.getString("hall_location"));
                                datumHallDetailsList.add(datumHallDetails[i]);
                            }///// work after
                            hallDetailPojo.setData(datumHallDetailsList);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
                Intent intent = new Intent(getApplicationContext(), Showhalls.class);
                startActivity(intent);
            }
        });
    }
}
