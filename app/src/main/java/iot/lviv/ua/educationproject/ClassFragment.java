package iot.lviv.ua.educationproject;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import static iot.lviv.ua.educationproject.ClassType.LAB;
import static iot.lviv.ua.educationproject.ClassType.LECTURE;
import static iot.lviv.ua.educationproject.ClassType.PRACTICE;

/**
 * Created by Daniil on 4/8/2018.
 */



public class ClassFragment extends Fragment implements View.OnClickListener{

    View mClassView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState){

        mClassView = inflater.inflate(R.layout.fragment_classes, container, false);

        return mClassView;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        //attaching CLASSES LIST Adapter to a ListView fragment_classes.xml
        ArrayList<ClassType> classList = new ArrayList<ClassType>();
        ClassesAdapter classesAdapter = new ClassesAdapter(getActivity(), classList);

        ListView classListView = (ListView) view.findViewById(R.id.class_list_view);
        classListView.setAdapter(classesAdapter);

        //TODO: add classes from Firebase and delete this template

        String practices = "practices";
        String labs = "labs";
        classList.add(LECTURE);
        classList.add(PRACTICE);
        classList.add(LAB);
    }

    @Override
    public void onClick(View v) {

    }
}
