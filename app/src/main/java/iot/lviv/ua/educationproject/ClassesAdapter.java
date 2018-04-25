package iot.lviv.ua.educationproject;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Daniil on 4/8/2018.
 */


public class ClassesAdapter extends ArrayAdapter<ClassType>{
    private static class ViewHolder{
        TextView typeOfClass;
    }

    public ClassesAdapter(Context context, ArrayList<ClassType> classList){
        super(context, R.layout.item_class, classList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ClassType classType = getItem(position);
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
        viewHolder.typeOfClass.setText(classType.toString().toLowerCase());



        SeekBar seekBar = (SeekBar) convertView.findViewById(R.id.seek_bar_item_class);
        TextView seekBarNum = (TextView) convertView.findViewById(R.id.seek_bar_num);
        {
            seekBarNum.setText(seekBar.getProgress()+"/100");
        }
        TextView sendRate = (TextView) convertView.findViewById(R.id.seek_bar_send);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarNum.setText(String.valueOf(progress)+"/100");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sendRate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                switch (classType) {
                    case LECTURE:
                        Toast.makeText(getContext(), "wow", Toast.LENGTH_SHORT).show();
                    case PRACTICE:
                        Toast.makeText(getContext(), "such", Toast.LENGTH_SHORT).show();
                    case LAB:
                        Toast.makeText(getContext(), "goes", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return convertView;
    }
}
