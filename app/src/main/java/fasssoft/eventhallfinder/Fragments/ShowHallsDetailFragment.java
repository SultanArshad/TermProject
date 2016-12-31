package fasssoft.eventhallfinder.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import fasssoft.eventhallfinder.models.DatumHallDetail;
import fasssoft.eventhallfinder.utils.urlClass;
import fasssoft.eventhallfinder.views.EditHallDetail;
import fasssoft.eventhallfinder.views.FragmentActivities.ShowHallsListActivity;

import static fasssoft.eventhallfinder.R.id.btnEdit;


public class ShowHallsDetailFragment extends Fragment {
    Button btnEdit, btnDel;
    int  id;

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
        id=employee.getId();

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditHallDetail.class);
                intent.putExtra("hallname", employee.getHallname());
                intent.putExtra("hallowname", employee.getOwnername());
                intent.putExtra("hallownumber", employee.getOwnerMobile());
                intent.putExtra("hallloc", employee.getHallLocation());
                intent.putExtra("hallid",employee.getId());

                startActivity(intent);
            }
        });/// btnedit one end

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringRequest stringRequestPost = new StringRequest(Request.Method.DELETE, urlClass.apihalldetails + id, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                requestQueue.add(stringRequestPost);
               Intent i = new Intent(getActivity(), ShowHallsListActivity.class);
                Toast.makeText(getActivity(),"Information Deleted Successfully",Toast.LENGTH_LONG);
               startActivity(i);

            }
        });
        return view;

    }

}
