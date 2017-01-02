package fasssoft.eventhallfinder.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

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
    Button btnShowWel, btnWellAdd;
    ArrayList<DatumHallDetail> datumHallDetailsList;
    HallDetailPojo hallDetailPojo;
    //ads

    private InterstitialAd mInterstitialAd;
    private TextView mLevelTextView;
    private AdView mBannerAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);
        btnShowWel = (Button) findViewById(R.id.btnShowWel);
        btnWellAdd = (Button) findViewById(R.id.btnWelAdd);
        datumHallDetailsList = new ArrayList<>();
        hallDetailPojo = new HallDetailPojo();
        ////ads
        // Create the next level button, which tries to show an interstitial when clicked.
        //   mLevelTwoButton = ((Button) findViewById(R.id.level_two_button));
        // Load the add into Admob banner view.
        mBannerAd = (AdView) findViewById(R.id.banner_AdView);
        // Text view to show the level number.
        mLevelTextView = (TextView) findViewById(R.id.level_text_view);
        // Creating and load a  new InterstitialAd .
       // mInterstitialAd = createNewIntAd();
       // loadIntAdd();
        //Load BannerAd
        showBannerAd();
        btnWellAdd.setEnabled(true);


        //ADS end

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
                Intent in = new Intent(getApplicationContext(), CreateHall.class);
                startActivity(in);
            }
        });
    }

    //adds
    private void showBannerAd() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("75C0210003DEC7504E626A3A2CCB2A67")
                .build();
        mBannerAd.loadAd(adRequest);
    }
    //ends
}
