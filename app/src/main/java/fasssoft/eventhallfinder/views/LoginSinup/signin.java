package fasssoft.eventhallfinder.views.LoginSinup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fasssoft.eventhallfinder.R;
import fasssoft.eventhallfinder.SharedPreferences.SharedPreference;
import fasssoft.eventhallfinder.models.Database.DatabaseHelper;
import fasssoft.eventhallfinder.models.DatumHallDetail;
import fasssoft.eventhallfinder.models.DatumLogin;
import fasssoft.eventhallfinder.models.HallDetailPojo;
import fasssoft.eventhallfinder.models.LoginPojo;
import fasssoft.eventhallfinder.utils.urlClass;
import fasssoft.eventhallfinder.views.Wellcome;
import fasssoft.eventhallfinder.views.main;

public class signin extends AppCompatActivity {

    ImageView sback;
    TextView sign;
    EditText etEmail, etPass;
    String[] arName, arEmail, arPassword;
    DatumLogin[] datumLogin;
    ArrayList<DatumLogin> datumLoginList;
    LoginPojo loginPojo;
    Boolean TAG = false;
    private DatumLogin login;
    boolean Network = false;
    DatabaseHelper helper = new DatabaseHelper(this);
    CheckBox checkBox;
    SharedPreference sharedPreference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        sback = (ImageView) findViewById(R.id.sinb);
        sign = (TextView) findViewById(R.id.txtViewSignIn);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);
        datumLoginList = new ArrayList<>();
        loginPojo = new LoginPojo();
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        sharedPreference = new SharedPreference(getApplicationContext());


        etEmail.setText(sharedPreference.getEmail());
        etPass.setText(sharedPreference.getPass());
        checkBox.setChecked(sharedPreference.isCheck());


        this.registerReceiver(this.mConnReceiver,
                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        sback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(signin.this, main.class);
                startActivity(it);
            }
        });///
        //start sigin in listner
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if start
                if (Network == true) {

                    if (checkBox.isChecked() == true) {
                        sharedPreference.setEmail(etEmail.getText().toString());
                        sharedPreference.setPass(etPass.getText().toString());
                        sharedPreference.setCheck(true);
                    } else {
                        sharedPreference.destroyPref();
                    }

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, urlClass.apilogintests, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //   Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                arEmail = new String[jsonArray.length()];
                                arName = new String[jsonArray.length()];
                                arPassword = new String[jsonArray.length()];
                                datumLogin = new DatumLogin[jsonArray.length()];
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    datumLogin[i] = new DatumLogin();
                                    datumLogin[i].setName(obj.getString("name"));
                                    datumLogin[i].setEmail(obj.getString("email"));
                                    datumLogin[i].setPassword(obj.getString("password"));
                                    datumLoginList.add(datumLogin[i]);
                                }///// work after
                                loginPojo.setData(datumLoginList);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    if (datumLogin[i].getEmail().equals(etEmail.getText().toString()) && datumLogin[i].getPassword().equals(etPass.getText().toString())) {
                                        TAG = true;
                                        break;
                                    } else {
                                        TAG = false;
                                    }
                                }
                                if (TAG == true) {
                                    Toast.makeText(getApplicationContext(), "Signin Successfully :) ", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(signin.this, Wellcome.class);
                                    startActivity(intent);
                                } else {
                                    TAG = false;
                                    Toast.makeText(getApplicationContext(), "OOPS EMAIL PASSWORD NOT MATCH", Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                        }
                    });//string req
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                    //if ends
                }//iff ends
                // offline start /////
                else {
                    if (checkBox.isChecked() == true) {
                        sharedPreference.setEmail(etEmail.getText().toString());
                        sharedPreference.setPass(etPass.getText().toString());
                        sharedPreference.setCheck(true);
                    } else {
                        sharedPreference.destroyPref();
                    }
                    String strEmail = etEmail.getText().toString();
                    String strPass = etPass.getText().toString();
                    String password = helper.searchPass(strEmail);
                    if (strPass.equals(password)) {
                        Toast.makeText(getApplicationContext(), "signIn successfully --> ", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(signin.this, Wellcome.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Email pass not Match", Toast.LENGTH_LONG).show();
                    }
                }//else end
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        TAG = false;
    }

    private BroadcastReceiver mConnReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            boolean noConnectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
            String reason = intent.getStringExtra(ConnectivityManager.EXTRA_REASON);
            boolean isFailover = intent.getBooleanExtra(ConnectivityManager.EXTRA_IS_FAILOVER, false);
            NetworkInfo currentNetworkInfo = (NetworkInfo) intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            NetworkInfo otherNetworkInfo = (NetworkInfo) intent.getParcelableExtra(ConnectivityManager.EXTRA_OTHER_NETWORK_INFO);
            if (currentNetworkInfo.isConnected()) {
                Network = true;
                Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_LONG).show();
            } else {
                Network = false;
                Toast.makeText(getApplicationContext(), "Not Connected", Toast.LENGTH_LONG).show();
            }
        }
    };////end reciver

}
