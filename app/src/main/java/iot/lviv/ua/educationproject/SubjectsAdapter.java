package iot.lviv.ua.educationproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Daniil on 4/7/2018.
 */

public class SubjectsAdapter extends ArrayAdapter<Subject>{
    public SubjectsAdapter(Context context, ArrayList<Subject> subjectsList) {
        super(context, 0, subjectsList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Subject subject = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_subject, parent,
                    false);
        }

        TextView subject_name = (TextView) convertView.findViewById(R.id.subject_name);

        subject_name.setText(subject.getName());

        return convertView;
    }
}
