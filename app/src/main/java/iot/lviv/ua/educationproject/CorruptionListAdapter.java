package iot.lviv.ua.educationproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Daniil on 4/25/2018.
 */

public class CorruptionListAdapter extends ArrayAdapter<CorruptionReport> {
    private static class ViewHolder {
        TextView lectorName;
        TextView corruptionText;
    }

    public CorruptionListAdapter(Context context, ArrayList<CorruptionReport> corruptionReportList) {
        super(context, R.layout.item_corruption, corruptionReportList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CorruptionReport corruptionReport = getItem(position);
        ViewHolder viewHolder;

        if(convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_corruption, parent,
            false);
            viewHolder.lectorName = (TextView) convertView.findViewById(R.id.item_corruption_lector);
            viewHolder.lectorName.setText(CorruptionFragment.corruptionList.get(position).getLecturerName());
            viewHolder.corruptionText = (TextView) convertView.findViewById(R.id.item_corruption_text);
            viewHolder.corruptionText.setText(CorruptionFragment.corruptionList.get(position).getReportText());
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        LinearLayout itemCorruption = (LinearLayout) convertView.findViewById(R.id.item_corruption);
        itemCorruption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }


}
