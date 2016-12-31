package fasssoft.eventhallfinder.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import fasssoft.eventhallfinder.R;

public class CreateHall extends AppCompatActivity {
EditText etHalname,etHallloc,etOwname,etOwnum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_hall);
        Intent intent=getIntent();
        etHalname=(EditText)findViewById(R.id.etChHname);
        etOwname=(EditText)findViewById(R.id.etChOwName);
        etOwnum=(EditText)findViewById(R.id.etChMob);
        etHallloc=(EditText) findViewById(R.id.etChLoc);


    }
}
