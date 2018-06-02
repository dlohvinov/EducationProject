package iot.lviv.ua.educationproject;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Daniil on 4/8/2018.
 */



public class ClassFragment extends Fragment implements View.OnClickListener {
//
//    private ArrayList <Evaluation> evaluations = new ArrayList<>();
//    private ArrayList <Evaluation> classListByStudent = new ArrayList<>();
//    private ArrayList <Evaluation> classListByDate = new ArrayList<>();
    private ArrayList <Evaluation> classListBySubject = new ArrayList<>();
    private ArrayList <Evaluation> mClassList = new ArrayList<>();
    private static ArrayList <Evaluation> classListFinal = new ArrayList<>();

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    ClassesAdapter classesAdapter;
    ListView classListView;
    View mClassView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState){
        mClassView = inflater.inflate(R.layout.fragment_classes, container, false);
        mClassList = new ArrayList<>();
        classListFinal.clear();

//        DatabaseReference evalRef = FirebaseManager.getInstance().getRootDatabaseReference().child("Groups")
//                .child("Group").child("Evaluation");
//        Query evalQuery = evalRef.wh
//
//        CollectionReference users = db.collection("users");

//        FirebaseManager firebaseManager = FirebaseManager.getInstance();
//        firebaseManager.loadDataBase(new FirebaseManager.Callback<Evaluation>() {
//            @Override
//            public void onSuccess(List<Evaluation> evaluationList, List<CorruptionReport> corruptionReportList) {
//                evaluations = (ArrayList<Evaluation>) evaluationList;
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


        Bundle bundle = getArguments();
        Subjects subjects = (Subjects) bundle.getSerializable("Subjects");

        String id = UserManager.getInstance().getCurrentUser().getUid();
        CollectionReference evalsRef = db.collection("Evaluations");

        Query evalQuery = evalsRef.orderBy("dateAndTime").whereEqualTo("studentId", id);

//                .whereEqualTo("TypeOfClass", subjects.toString())

        evalQuery.get().addOnCompleteListener(task -> {
            QuerySnapshot snapshot = task.getResult();

            List<Evaluation> list = snapshot.toObjects(Evaluation.class);

            mClassList.addAll(list);


            switch (subjects) {
                case MATH_ANALYSIS:
                    for(Evaluation evaluation : mClassList) {
                        if (evaluation.getSubjectId().equals(Subjects.MATH_ANALYSIS)) {
                            classListBySubject.add(evaluation);
                        }
                    }
                    if (classListBySubject.isEmpty()) {
                        Evaluation mathLectureEvaluation = new Evaluation(TypeOfClass.MATHEMATICAL_ANALYSIS_LECTURE,
                                UserManager.getInstance().getCurrentUser().getUid(), 20,
                                Subjects.MATH_ANALYSIS);
                        Evaluation mathPracticeEvaluation = new Evaluation(TypeOfClass.MATHEMATICAL_ANALYSIS_PRACTICE,
                                UserManager.getInstance().getCurrentUser().getUid(), 30,
                                Subjects.MATH_ANALYSIS);
                        classListFinal.add(mathLectureEvaluation);
                        classListFinal.add(mathPracticeEvaluation);
                    }
                    else if(classListBySubject.size() == 1) {
                        if (classListBySubject.get(0).getTypeOfClass() == TypeOfClass
                                .MATHEMATICAL_ANALYSIS_LECTURE) {
                            Evaluation mathPracticeEvaluation = new Evaluation(TypeOfClass.MATHEMATICAL_ANALYSIS_PRACTICE,
                                    UserManager.getInstance().getCurrentUser().getUid(), 0,
                                    Subjects.MATH_ANALYSIS);
                            classListFinal.add(mathPracticeEvaluation);
                        }
                        else {
                            Evaluation mathLectureEvaluation = new Evaluation(TypeOfClass.MATHEMATICAL_ANALYSIS_LECTURE,
                                    UserManager.getInstance().getCurrentUser().getUid(), 0,
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
            classesAdapter = new ClassesAdapter(getActivity(), classListFinal);

            classListView = getView().findViewById(R.id.class_list_view);
            classListView.setAdapter(classesAdapter);
        });


//        for (int i =0; i < 10000; i++) {
//            Log.d("javascript is for gods", "so is Ukrainian");
//        }


//        for (int i = 0; i < evaluations.size(); i++) {
//            if (UserManager.getInstance().getCurrentUser().equals(evaluations.get(i).getStudentId())){
//                classListByStudent.add(evaluations.get(i));
//            }
//        }
//        classListByDate = (ArrayList<Evaluation>)
//                Util.sortEvaluationsByDate(classListByStudent);



        return mClassView;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {



    }

    @Override
    public void onClick(View v) {

    }

    public static ArrayList<Evaluation> getClassListFinal() {
        return classListFinal;
    }
}
