package fasssoft.eventhallfinder.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import fasssoft.eventhallfinder.R;
import fasssoft.eventhallfinder.models.DatumHallDetail;
import fasssoft.eventhallfinder.views.EditHallDetail;

import static fasssoft.eventhallfinder.R.id.btnEdit;


public class ShowHallsDetailFragment extends Fragment {
    Button btnEdit, btnDel;

    private DatumHallDetail employee;

    public static ShowHallsDetailFragment newInstance(DatumHallDetail employee) {
        ShowHallsDetailFragment fragmentDemo = new ShowHallsDetailFragment();
        Bundle bd = new Bundle();
        bd.putSerializable("employee", employee);
        fragmentDemo.setArguments(bd);
        return fragmentDemo;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        employee = (DatumHallDetail) getArguments().getSerializable("employee");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_halls_detail, container, false);
        TextView tvHallName = (TextView) view.findViewById(R.id.tvDetailHallName);
        TextView tvLoc = (TextView) view.findViewById(R.id.tvDetailLoc);
        TextView tvOname = (TextView) view.findViewById(R.id.tvDetailOwName);
        TextView tvOnum = (TextView) view.findViewById(R.id.tvDetailOwNumber);
        btnDel = (Button) view.findViewById(R.id.btnDel);
        btnEdit = (Button) view.findViewById(R.id.btnEdit);


        tvHallName.setText(employee.getHallname());
        tvOname.setText(employee.getOwnername());
        tvOnum.setText(employee.getOwnerMobile());
        tvLoc.setText(employee.getHallLocation());
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditHallDetail.class);
                intent.putExtra("hallname", employee.getHallname());
                intent.putExtra("hallowname", employee.getOwnername());
                intent.putExtra("hallownumber", employee.getOwnerMobile());
                intent.putExtra("hallloc", employee.getHallLocation());
                startActivity(intent);
            }
        });/// btnedit one end

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditHallDetail.class);
                intent.putExtra("hallnaem", employee.getHallname());
                intent.putExtra("hallowname", employee.getOwnername());
                intent.putExtra("hallownumber", employee.getOwnerMobile());
                intent.putExtra("hallloc", employee.getHallLocation());
                startActivity(intent);
            }
        });
        return view;

    }

}
