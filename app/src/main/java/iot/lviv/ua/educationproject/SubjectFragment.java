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
import java.util.List;

/**
 * Created by Daniil on 4/16/2018.
 */

public class SubjectFragment extends Fragment implements View.OnClickListener {
    View mSubjectView;
    private SubjectRecyclerAdapter subjectRecyclerAdapter;
    ArrayList<Subject> subjectList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        mSubjectView = inflater.inflate(R.layout.fragment_subjects, container, false);
        return mSubjectView;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        subjectList = new ArrayList<Subject>();;
        createSubjectRecyclerAdapter(subjectList);

        RecyclerView subjectRecyclerView = (RecyclerView) view.findViewById(R.id.subject_recycler_view);
        subjectRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        subjectRecyclerView.setAdapter(subjectRecyclerAdapter);
    }

    public void createSubjectRecyclerAdapter(ArrayList<Subject> subjects){
        Subject subject1 = new Subject("Математичний аналіз"
                ,"Пахолок Б.Б.", 0, Subjects.MATH_ANALYSIS);
        Subject subject2 = new Subject("Фізика","Коломієць О.В.",0,
                Subjects.PHYSICS);
        Subject subject3 = new Subject("Іноземна мова за проф. спрям.",
                "Залуцька Г.І.",0,
                Subjects.ENGLISH);
        Subject subject4 = new Subject("Електротехніка та електроніка","Мадай В.С.",
                0, Subjects.ELECTROTECHNICS_AND_ELECTRONICS);
        Subject subject5 = new Subject("Алгоритмізація та програмування",
                "Верес З.Є.",0,
                Subjects.ALGO_AND_PROGRAMMING);
        Subject subject6 = new Subject("Українська мова за проф. спрям.",
                "Василишин І.П.",0,
                Subjects.UKRAINIAN);
        Subject subject7 = new Subject("Дискретна математика","Дзелендзяк У.Ю.",78,
                Subjects.DISCRET_MATH);

        subjects.add(subject1);
        subjects.add(subject2);
        subjects.add(subject3);
        subjects.add(subject4);
        subjects.add(subject5);
        subjects.add(subject6);
        subjects.add(subject7);

        subjectRecyclerAdapter = new SubjectRecyclerAdapter(subjects,
                new SubjectRecyclerAdapter.MyRecyclerListener() {
                    @Override
                    public void onItemClick(int position) {
                        ClassFragment classFragment = new ClassFragment();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("Subjects", subjects.get(position).getId());
                        classFragment.setArguments(bundle);
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.place_holder,
                                classFragment).addToBackStack(null).commit();

                    }
                });

        updateProgressBars(MainActivity.evaluations);
    }

    public void updateProgressBars(List<Evaluation> evaluations){
        for(int i = 0; i < subjectList.size(); i++){
            subjectList.get(i).setProgress(Util.getAverageEvaluation(evaluations, subjectList.get(i).getId()));
        }

        subjectRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {

    }
}
