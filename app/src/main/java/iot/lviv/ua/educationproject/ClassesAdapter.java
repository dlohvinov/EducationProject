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


public class ClassesAdapter extends ArrayAdapter<Evaluation>{
    private static class ViewHolder{
        TextView typeOfClass;
    }

    public ClassesAdapter(Context context, ArrayList<Evaluation> classList){
        super(context, R.layout.item_class, classList);

    }

@Override
    public View getView(int position, View convertView, ViewGroup parent){
        Evaluation evaluation = getItem(position);
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
        viewHolder.typeOfClass.setText(evaluation.getTypeOfClass().toString().toLowerCase());



        SeekBar seekBar = (SeekBar) convertView.findViewById(R.id.seek_bar_item_class);
        TextView seekBarNum = (TextView) convertView.findViewById(R.id.seek_bar_num);
        {
            seekBarNum.setText(seekBar.getProgress()+"/100");
        }
        TextView sendEvaluation = (TextView) convertView.findViewById(R.id.seek_bar_send);
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

        sendEvaluation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ClassFragment.getClassListFinal().get(position).setEvaluation(seekBar.getProgress());
//                FirebaseManager firebaseManager = FirebaseManager.getInstance();
//                firebaseManager.sendEvaluation(classList.get(position));



//                switch (classList.get(position).getSubjectId()) {
//                    case MATH_ANALYSIS:
//                        if (classList.get(position).getTypeOfClass()
//                                .equals(TypeOfClass.MATHEMATICAL_ANALYSIS_LECTURE)) {
//                            Toast.makeText(getContext(), "wow", Toast.LENGTH_SHORT).show();
//
//                        }
//                        else {
//                            Toast.makeText(getContext(), "such code", Toast.LENGTH_SHORT).show();
//                        }
//
//                }
            }
        });

        return convertView;
    }
}
