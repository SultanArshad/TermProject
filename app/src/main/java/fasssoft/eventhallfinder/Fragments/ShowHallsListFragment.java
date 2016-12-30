package fasssoft.eventhallfinder.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
import fasssoft.eventhallfinder.Adapters.DataumHallAdapter;
import fasssoft.eventhallfinder.R;
import fasssoft.eventhallfinder.models.DatumHallDetail;
import fasssoft.eventhallfinder.models.HallDetailPojo;
import fasssoft.eventhallfinder.utils.urlClass;


public class ShowHallsListFragment extends Fragment {

    DatumHallDetail[] datumHallDetails;
    ArrayList<DatumHallDetail> datumHallDetailsList;
    HallDetailPojo hallDetailPojo;
    private ListView lvEmployees;
    private DataumHallAdapter adapterEmployees;
    //ArrayAdapter<DatumHallDetail>
    private ListItemSelectedListener listener;
    View view;


    @Override
    public void onCreate(Bundle savedInstantState) {
        super.onCreate(savedInstantState);
        ////////
        datumHallDetailsList = new ArrayList<>();
        hallDetailPojo = new HallDetailPojo();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlClass.apihalldetails, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    datumHallDetails = new DatumHallDetail[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        datumHallDetails[i] = new DatumHallDetail();
                        datumHallDetails[i].setHallname(obj.getString("hallname"));
                        datumHallDetails[i].setOwnername(obj.getString("ownername"));
                        datumHallDetails[i].setOwnerMobile(obj.getString("owner_mobile"));
                        datumHallDetails[i].setHallLocation(obj.getString("hall_location"));
                        datumHallDetailsList.add(datumHallDetails[i]);
                    }///// work after
                    hallDetailPojo.setData(datumHallDetailsList);
                //    adapterEmployees = new ArrayAdapter<DatumHallDetail>(getActivity(), R.layout.datum_hall_adapter_layout, datumHallDetailsList);
                    adapterEmployees = new DataumHallAdapter(getActivity(), R.layout.datum_hall_adapter_layout, datumHallDetailsList);
                    lvEmployees.setAdapter(adapterEmployees);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
        ///////

    }///on creat ends

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_halls_list, container, false);
        lvEmployees = (ListView) view.findViewById(R.id.lvEmployees);
        /////////

        /////////

        lvEmployees.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View employee, int position, long rowId) {
                DatumHallDetail emp = (DatumHallDetail) adapterEmployees.getItem(position);
                listener.ItemSelected(emp);
            }
        });
        return view;
    }///on creat view ended

    public interface ListItemSelectedListener {
        public void ItemSelected(DatumHallDetail employee);
    }

    public void setActivateOnItemClick(boolean activateOnItemClick) {
        lvEmployees.setChoiceMode(activateOnItemClick ? ListView.CHOICE_MODE_SINGLE : ListView.CHOICE_MODE_NONE);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ListItemSelectedListener) {
            listener = (ListItemSelectedListener) activity;
        } else {
            throw new ClassCastException(activity.toString() + "muimplement EmployeeListFragment.ListItemSelectedListener");
        }
    }
}
