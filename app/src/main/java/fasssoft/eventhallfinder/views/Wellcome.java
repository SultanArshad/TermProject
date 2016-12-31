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
import fasssoft.eventhallfinder.views.FragmentActivities.ShowHallsListActivity;
import fasssoft.eventhallfinder.views.LoginSinup.signin;

public class Wellcome extends AppCompatActivity {
    Button btnShowWel,btnWellAdd;
    DatumHallDetail[] datumHallDetails;
   ArrayList<DatumHallDetail> datumHallDetailsList;
   HallDetailPojo hallDetailPojo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);
        btnShowWel = (Button) findViewById(R.id.btnShowWel);
        btnWellAdd=(Button)findViewById(R.id.btnWelAdd) ;
       datumHallDetailsList = new ArrayList<>();
        hallDetailPojo = new HallDetailPojo();
        final Intent ii=getIntent();
        btnShowWel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShowHallsListActivity.class);
                startActivity(intent);
            }
        });


        btnWellAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateHall.class);
                intent.putExtra("hallnme",ii.getStringExtra("hallname"));
                intent.putExtra("owname",ii.getStringExtra("owname"));
                intent.putExtra("hallloc",ii.getStringExtra("hallloc"));
                startActivity(intent);
            }
        });

    }
}
