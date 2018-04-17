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


public class ClassAdapter extends ArrayAdapter<Class>{
    private static class ViewHolder{
        TextView typeOfClass;
    }

    public ClassAdapter(Context context, ArrayList<Class> classList){
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

        TextView positiveRate = (TextView) convertView.findViewById(R.id.positive_rate);
        TextView neutralRate = (TextView) convertView.findViewById(R.id.neutral_rate);
        TextView negativeRate = (TextView) convertView.findViewById(R.id.negative_rate);
        positiveRate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                eduClass.setPositiveRate(true);
                eduClass.setNeutralRate(false);
                eduClass.setNegativeRate(false);
                positiveRate.setBackgroundColor(Color.GREEN);

                positiveRate.setEnabled(false);
                neutralRate.setEnabled(false);
                negativeRate.setEnabled(false);
            }
        });
        neutralRate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                eduClass.setPositiveRate(false);
                eduClass.setNeutralRate(true);
                eduClass.setNegativeRate(false);
                neutralRate.setBackgroundColor(Color.GRAY);

                positiveRate.setEnabled(false);
                neutralRate.setEnabled(false);
                negativeRate.setEnabled(false);
            }
        });
        negativeRate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                eduClass.setPositiveRate(false);
                eduClass.setNeutralRate(false);
                eduClass.setNegativeRate(true);
                negativeRate.setBackgroundColor(Color.RED);

                positiveRate.setEnabled(false);
                neutralRate.setEnabled(false);
                negativeRate.setEnabled(false);
            }
        });

        viewHolder.typeOfClass.setText(eduClass.getTypeOfClass());
        return convertView;
    }
}
