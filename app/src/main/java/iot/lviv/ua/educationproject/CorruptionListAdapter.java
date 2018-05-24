package iot.lviv.ua.educationproject;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by Daniil on 4/25/2018.
 */

public class CorruptionListAdapter extends ArrayAdapter<CorruptionReport> {
    private static int pos;

    private static class ViewHolder {
        TextView lectorName;
        TextView corruptionText;
        TextView corruptionDate;
    }

    public CorruptionListAdapter(Context context, ArrayList<CorruptionReport> corruptionReportList) {
        super(context, R.layout.item_corruption_list, corruptionReportList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        CorruptionReport corruptionReport = getItem(position);
        ViewHolder viewHolder;


        if(convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_corruption_list, parent,
            false);
            viewHolder.corruptionDate = (TextView) convertView.findViewById(R.id.item_corruption_date);
            viewHolder.corruptionDate.setText(CorruptionListFragment
                    .corruptionReportList.get(position).getDateAndTime());
            viewHolder.lectorName = (TextView) convertView.findViewById(R.id.item_corruption_lector);
            viewHolder.lectorName.setText(CorruptionListFragment
                    .corruptionReportList.get(position).getLecturerName());
            viewHolder.corruptionText = (TextView) convertView.findViewById(R.id.item_corruption_text);
            viewHolder.corruptionText.setText(CorruptionListFragment
                    .corruptionReportList.get(position).getReportText());

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        LinearLayout itemCorruption = (LinearLayout) convertView.findViewById(R.id.item_corruption);
        itemCorruption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CorruptionLookFragment corruptionLookFragment = new CorruptionLookFragment();
                FragmentManager fragmentManager = ((Activity)getContext()).getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.place_holder,
                        corruptionLookFragment).addToBackStack(null).commit();
                pos = position;
            }
        });

        return convertView;
    }

    public static int getPos() {
        return pos;
    }

}
