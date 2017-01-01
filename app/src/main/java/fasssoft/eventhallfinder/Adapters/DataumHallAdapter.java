package fasssoft.eventhallfinder.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fasssoft.eventhallfinder.R;
import fasssoft.eventhallfinder.models.DatumHallDetail;

/**
 * Created by sultan on 12/30/2016.
 */

public class DataumHallAdapter extends ArrayAdapter {
    DatumHallDetail datumHallDetail;

    public DataumHallAdapter(Context context, int resource, List<DatumHallDetail> objects) {
        super(context, resource, objects);
    }

    public DataumHallAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == convertView) {
            LayoutInflater vI;
            vI = LayoutInflater.from(getContext());
            v = vI.inflate(R.layout.datum_hall_adapter_layout, null);
        }
        datumHallDetail = (DatumHallDetail) getItem(position);
        TextView tvHallName = (TextView) v.findViewById(R.id.tvDatumAdpHallName);
        TextView tvHallLoc = (TextView) v.findViewById(R.id.tvDatumAdpHallLoc);
        if (tvHallLoc != null) {
            tvHallLoc.setText(datumHallDetail.getHallLocation());
        }
        if (tvHallName != null) {
            tvHallName.setText(datumHallDetail.getHallname());
        }
        return v;
    }

    public DatumHallDetail getDatumHallDetail() {
        return datumHallDetail;
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }
}
