package iot.lviv.ua.educationproject;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Daniil on 4/8/2018.
 */


public class ClassesAdapter extends ArrayAdapter<Class>{
    private static class ViewHolder{
        TextView typeOfClass;
    }

    public ClassesAdapter(Context context, ArrayList<Class> classList){
        super(context, R.layout.item_class, classList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Class eduClass = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_class, parent,
                    false);
            viewHolder.typeOfClass = (TextView) convertView.findViewById(R.id.type_of_class);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.typeOfClass.setText(eduClass.getTypeOfClass());
        return convertView;
    }
}
