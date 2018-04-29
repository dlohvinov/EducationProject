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
 * Created by Daniil on 4/16/2018.
 */

public class SubjectFragment extends Fragment implements View.OnClickListener {
    View mSubjectView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState){
        mSubjectView = inflater.inflate(R.layout.fragment_subjects, container, false);
        return mSubjectView;
    }
     public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

         ArrayList<Subject> subjectList = new ArrayList<Subject>();
         SubjectsAdapter subjectsAdapter = new SubjectsAdapter(getActivity(), subjectList);

         ListView subjectListView = (ListView) view.findViewById(R.id.subject_list_view);
         subjectListView.setAdapter(subjectsAdapter);

         //TODO: add subjects from Firebase and delete this template
         Subject subject1 = new Subject();
         Subject subject2 = new Subject();
         Subject subject3 = new Subject();
         Subject subject4 = new Subject();
         Subject subject5 = new Subject();
         Subject subject6 = new Subject();
         Subject subject7 = new Subject();
         Subject subject8 = new Subject();
         subject1.setName("Math Analysis");
         subjectList.add(subject1);
         subject2.setName("Physics");
         subjectList.add(subject2);
         subject3.setName("English");
         subjectList.add(subject3);
         subject4.setName("Electrotechnics and Electronics");
         subjectList.add(subject4);
         subject5.setName("Programming pt. 2");
         subjectList.add(subject5);
         subject6.setName("Ukrainian");
         subjectList.add(subject6);
         subject7.setName("Discret Math");
         subjectList.add(subject7);
         subject8.setName("History");
         subjectList.add(subject8);
     }

     @Override
    public void onClick(View v) {

     }
}
