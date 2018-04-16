package iot.lviv.ua.educationproject;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

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
        ArrayList<Class> classList = new ArrayList<Class>();
        ClassesAdapter classesAdapter = new ClassesAdapter(getActivity(), classList);

        ListView classListView = (ListView) view.findViewById(R.id.class_list_view);
        classListView.setAdapter(classesAdapter);

        //TODO: add classes from Firebase and delete this template
        Class eduClass1 = new Class();
        Class eduClass2 = new Class();
        eduClass1.setTypeOfClass("lecture 1");
        classList.add(eduClass1);
        eduClass2.setTypeOfClass("practice 1");
        classList.add(eduClass2);
    }

    @Override
    public void onClick(View v) {



    }
}
