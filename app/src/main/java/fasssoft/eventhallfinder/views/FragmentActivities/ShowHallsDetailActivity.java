package fasssoft.eventhallfinder.views.FragmentActivities;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fasssoft.eventhallfinder.Fragments.ShowHallsDetailFragment;
import fasssoft.eventhallfinder.R;
import fasssoft.eventhallfinder.models.DatumHallDetail;
import fasssoft.eventhallfinder.views.EditHallDetail;

public class ShowHallsDetailActivity extends AppCompatActivity {

    ShowHallsDetailFragment fragmentEmployeeDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_halls_detail);
        DatumHallDetail employee = (DatumHallDetail) getIntent().getSerializableExtra("employee");
        if (savedInstanceState == null) {
            fragmentEmployeeDetail = ShowHallsDetailFragment.newInstance(employee);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flDetailContainer, fragmentEmployeeDetail);
            ft.commit();
        }

    }
}
