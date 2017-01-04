package fasssoft.eventhallfinder.views.LoginSinup;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.mock.MockPackageManager;
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
import fasssoft.eventhallfinder.Services.GPSTracker;
import fasssoft.eventhallfinder.models.Database.DatabaseHelper;
import fasssoft.eventhallfinder.models.User;
import fasssoft.eventhallfinder.utils.urlClass;
import fasssoft.eventhallfinder.views.Wellcome;
import fasssoft.eventhallfinder.views.main;

import static android.support.v7.mediarouter.R.id.time;

public class signup extends AppCompatActivity {
    ImageView sback, myLoc;
    EditText etEmailSup, etPassSup, etHallNameSup, etFnameSup, etLocationSup;
    TextView sup;
    String pName, pEmail, pLocation, pHall, pPass;
    DatabaseHelper helper = new DatabaseHelper(this);
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;

    GPSTracker gps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        sback = (ImageView) findViewById(R.id.sback);
        myLoc = (ImageView) findViewById(R.id.myLoc);
        etEmailSup = (EditText) findViewById(R.id.etEmailSup);
        etFnameSup = (EditText) findViewById(R.id.etFnameSup);
        etHallNameSup = (EditText) findViewById(R.id.etHallNameSup);
        etPassSup = (EditText) findViewById(R.id.EtPassSup);
        etLocationSup = (EditText) findViewById(R.id.etLocationSup);
        sup = (TextView) findViewById(R.id.sup);

        //permission
        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission)
                    != MockPackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{mPermission},
                        REQUEST_CODE_PERMISSION);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ///permission ends


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
                Toast.makeText(getApplicationContext(), "SignUp Sucessfully", Toast.LENGTH_LONG).show();
                Intent i = new Intent(signup.this, Wellcome.class);
                startActivity(i);

            }
        });///onclik end
        ///location start
        myLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

// create class object
                gps = new GPSTracker(signup.this);

                // check if GPS enabled
                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
etLocationSup.setText("Latitude("+latitude+") Longitude("+longitude+")");
                    // \n is for new line
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }

            }
        });
        ///end location
    }
}
