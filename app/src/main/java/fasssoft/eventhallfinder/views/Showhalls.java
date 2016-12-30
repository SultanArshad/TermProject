package fasssoft.eventhallfinder.views;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fasssoft.eventhallfinder.Fragments.ShowHallsListFragment;
import fasssoft.eventhallfinder.R;

public class Showhalls extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showhalls);
//        ShowHallsListFragment showHallsListFragment=new ShowHallsListFragment();
//        FragmentManager fragmentManager=getFragmentManager();
//        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.activity_showhalls,showHallsListFragment);
//        fragmentTransaction.commit();
    }
}
