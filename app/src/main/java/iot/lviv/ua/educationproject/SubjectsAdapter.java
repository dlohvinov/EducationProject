package iot.lviv.ua.educationproject;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Daniil on 4/7/2018.
 */

public class SubjectsAdapter extends ArrayAdapter<Subject>{

    private static class ViewHolder{
        TextView subjectName;
    }

    public SubjectsAdapter(Context context, ArrayList<Subject> subjectsList) {
        super(context, R.layout.item_subject, subjectsList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Subject subject = getItem(position);
        ViewHolder viewHolder;

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_subject, parent,
                    false);
            viewHolder.subjectName = (TextView) convertView.findViewById(R.id.subject_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //making layout a button
        LinearLayout itemSubject = (LinearLayout) convertView.findViewById(R.id.item_subject);
        itemSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClassFragment classFragment = new ClassFragment();
                FragmentManager fragmentManager = ((Activity)getContext()).getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.place_holder,
                        classFragment).addToBackStack(null).commit();

            }
        });

        //adding fields to layout
        viewHolder.subjectName.setText(subject.getName());

        return convertView;
    }
}
