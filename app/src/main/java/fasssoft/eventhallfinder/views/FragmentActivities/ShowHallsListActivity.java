package fasssoft.eventhallfinder.views.FragmentActivities;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import fasssoft.eventhallfinder.Fragments.ShowHallsDetailFragment;
import fasssoft.eventhallfinder.Fragments.ShowHallsListFragment;
import fasssoft.eventhallfinder.R;
import fasssoft.eventhallfinder.models.DatumHallDetail;

public class ShowHallsListActivity extends AppCompatActivity implements ShowHallsListFragment.ListItemSelectedListener {

    private boolean isRotate = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_halls_list);
        checkRotationLayout();
    }

    private void checkRotationLayout() {
        FrameLayout fragmentEmployeeDetil = (FrameLayout) findViewById(R.id.flDetailContainer);
        // If there is a second pane for details
        if (fragmentEmployeeDetil != null) {
            isRotate = true;
            ShowHallsListFragment fragmentEmployeesList = (ShowHallsListFragment)
                    getSupportFragmentManager().findFragmentById(R.id.fragmenEmployeesList);
            fragmentEmployeesList.setActivateOnItemClick(true);
        }
    }
    @Override
    public void ItemSelected(DatumHallDetail employee) {
        if (isRotate) {
            ShowHallsDetailFragment fragmentEmployee = ShowHallsDetailFragment.newInstance(employee);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flDetailContainer, fragmentEmployee);
            ft.commit();
        } else {
            Intent intent = new Intent(this, ShowHallsDetailActivity.class);
            intent.putExtra("employee", employee);
            startActivity(intent);
        }
    }
}
