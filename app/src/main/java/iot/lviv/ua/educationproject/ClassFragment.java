package iot.lviv.ua.educationproject;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;

import static iot.lviv.ua.educationproject.ClassType.LAB;
import static iot.lviv.ua.educationproject.ClassType.LECTURE;
import static iot.lviv.ua.educationproject.ClassType.PRACTICE;

/**
 * Created by Daniil on 4/8/2018.
 */



public class ClassFragment extends Fragment implements View.OnClickListener{

    private ArrayList <Evaluation> evaluations = new ArrayList<>();
    private ArrayList <Evaluation> classListByStudent = new ArrayList<>();
    private ArrayList <Evaluation> classListByDate = new ArrayList<>();
    private ArrayList <Evaluation> classListBySubject = new ArrayList<>();
    private static ArrayList <Evaluation> classListFinal = new ArrayList<>();

    ClassesAdapter classesAdapter;
    ListView classListView;
    View mClassView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState){
        mClassView = inflater.inflate(R.layout.fragment_classes, container, false);

        classListFinal.clear();
        
        FirebaseManager firebaseManager = FirebaseManager.getInstance();
        firebaseManager.loadDataBase(new FirebaseManager.Callback<Evaluation>() {
            @Override
            public void onSuccess(List<Evaluation> evaluationList, List<CorruptionReport> corruptionReportList) {
                evaluations = (ArrayList<Evaluation>) evaluationList;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        for (int i = 0; i < evaluations.size(); i++) {
            if (UserManager.getInstance().getCurrentUser().equals(evaluations.get(i).getStudentId())){
                classListByStudent.add(evaluations.get(i));
            }
        }
        classListByDate = (ArrayList<Evaluation>)
                Util.sortEvaluationsByDate(classListByStudent);

        Bundle bundle = getArguments();
        Subjects subjects = (Subjects) bundle.getSerializable("Subjects");
        switch (subjects) {
            case MATH_ANALYSIS:
                for(Evaluation evaluation : classListByDate) {
                    if (evaluation.getTypeOfClass().equals(Subjects.MATH_ANALYSIS)) {
                        classListBySubject.add(evaluation);
                    }
                }
                if (classListBySubject.isEmpty()) {
                    Evaluation mathLectureEvaluation = new Evaluation(TypeOfClass.MATHEMATICAL_ANALYSIS_LECTURE,
                            UserManager.getInstance().getCurrentUser(), 0,
                            Subjects.MATH_ANALYSIS);
                    Evaluation mathPracticeEvaluation = new Evaluation(TypeOfClass.MATHEMATICAL_ANALYSIS_PRACTICE,
                            UserManager.getInstance().getCurrentUser(), 0,
                            Subjects.MATH_ANALYSIS);
                    classListFinal.add(mathLectureEvaluation);
                    classListFinal.add(mathPracticeEvaluation);
                }
                else if(classListBySubject.size() == 1) {
                    if (classListBySubject.get(0).getTypeOfClass() == TypeOfClass
                            .MATHEMATICAL_ANALYSIS_LECTURE) {
                        Evaluation mathPracticeEvaluation = new Evaluation(TypeOfClass.MATHEMATICAL_ANALYSIS_PRACTICE,
                                UserManager.getInstance().getCurrentUser(), 0,
                                Subjects.MATH_ANALYSIS);
                        classListFinal.add(mathPracticeEvaluation);
                    }
                    else {
                        Evaluation mathLectureEvaluation = new Evaluation(TypeOfClass.MATHEMATICAL_ANALYSIS_LECTURE,
                                UserManager.getInstance().getCurrentUser(), 0,
                                Subjects.MATH_ANALYSIS);
                        classListFinal.add(mathLectureEvaluation);
                    }
                }
                else{
                    classListFinal.addAll(classListBySubject);
                }
            case ELECTROTECHNICS_AND_ELECTRONICS:

            case ALGO_AND_PROGRAMMING:

            case DISCRET_MATH:

            case UKRAINIAN:

            case PHYSICS:

            case ENGLISH:

        }
        return mClassView;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        classesAdapter = new ClassesAdapter(getActivity(), classListFinal);

        classListView = (ListView) view.findViewById(R.id.class_list_view);
        classListView.setAdapter(classesAdapter);


    }

    @Override
    public void onClick(View v) {

    }

    public static ArrayList<Evaluation> getClassListFinal() {
        return classListFinal;
    }
}
