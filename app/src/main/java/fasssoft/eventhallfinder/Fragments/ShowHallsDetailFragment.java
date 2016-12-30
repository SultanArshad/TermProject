package fasssoft.eventhallfinder.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fasssoft.eventhallfinder.R;
import fasssoft.eventhallfinder.models.DatumHallDetail;


public class ShowHallsDetailFragment extends Fragment {

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
        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        TextView tvBody = (TextView) view.findViewById(R.id.tvBody);
        tvTitle.setText(employee.getHallname());
        tvBody.setText(employee.getHallLocation());
        return view;
    }

}
