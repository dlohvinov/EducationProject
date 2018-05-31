package iot.lviv.ua.educationproject;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Daniil on 4/16/2018.
 */

public class SubjectFragment extends Fragment implements View.OnClickListener {
    View mSubjectView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        mSubjectView = inflater.inflate(R.layout.fragment_subjects, container, false);
        return mSubjectView;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        ArrayList<Subject> subjectList = new ArrayList<Subject>();

        RecyclerView subjectRecyclerView = (RecyclerView) view.findViewById(R.id.subject_recycler_view);
        subjectRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //TODO: add subjects from Firebase and delete this template
        Subject subject1 = new Subject(getString(R.string.math_analysis),"Mr.X",45, Subjects.MATH_ANALYSIS);
        Subject subject2 = new Subject(getString(R.string.physics),"Mr.X",100, Subjects.PHYSICS);
        Subject subject3 = new Subject("English","Mr.X",0, Subjects.ENGLISH);
        Subject subject4 = new Subject("Electrotechnics and Electronics","Mr.X",20, Subjects.ELECTROTECHNICS_AND_ELECTRONICS);
        Subject subject5 = new Subject("Programming pt. 2","Mr.X",45, Subjects.ALGO_AND_PROGRAMMING);
        Subject subject6 = new Subject("Ukrainian","Mr.X",63, Subjects.UKRAINIAN);
        Subject subject7 = new Subject("Discret Math","Mr.X",78, Subjects.DISCRET_MATH);

        subjectList.add(subject1);
        subjectList.add(subject2);
        subjectList.add(subject3);
        subjectList.add(subject4);
        subjectList.add(subject5);
        subjectList.add(subject6);
        subjectList.add(subject7);

        subjectRecyclerView.setAdapter(new SubjectRecyclerAdapter(subjectList,
                new SubjectRecyclerAdapter.MyRecyclerListener() {
            @Override
            public void onItemClick(int position) {
                ClassFragment classFragment = new ClassFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("Subjects", subjectList.get(position).getId());
                classFragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.place_holder,
                        classFragment).addToBackStack(null).commit();

            }
        }));
    }

    @Override
    public void onClick(View v) {

    }
}
