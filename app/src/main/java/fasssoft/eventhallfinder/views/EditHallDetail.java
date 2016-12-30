package fasssoft.eventhallfinder.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import fasssoft.eventhallfinder.R;

public class EditHallDetail extends AppCompatActivity {
EditText etHallName,etHallLoc,etHallOwName,etHallOwnum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hall_detail);
        etHallName=(EditText) findViewById(R.id.etEdHallName);
        etHallOwName=(EditText) findViewById(R.id.etEdOwNaeme);
        etHallOwnum=(EditText) findViewById(R.id.etEdOwNum);
        etHallLoc=(EditText) findViewById(R.id.etEdLoc);

        Intent intent= getIntent();
       etHallName.setText(intent.getStringExtra("hallname"));
        etHallOwName.setText(intent.getStringExtra("hallowname"));
        etHallOwnum.setText(intent.getStringExtra("hallownumber"));
        etHallLoc.setText(intent.getStringExtra("hallloc"));


    }
}
