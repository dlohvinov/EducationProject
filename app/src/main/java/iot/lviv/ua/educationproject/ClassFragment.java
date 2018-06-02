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
    private ArrayList<Evaluation> classListBySubject = new ArrayList<>();
    private ArrayList<Evaluation> mClassList = new ArrayList<>();
    private static ArrayList<Evaluation> classListFinal = new ArrayList<>();

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    ClassesAdapter classesAdapter;
    ListView classListView;
    View mClassView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
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
                    for (Evaluation evaluation : mClassList) {
                        if (evaluation.getSubjectId().equals(Subjects.MATH_ANALYSIS)) {
                            classListBySubject.add(evaluation);
                        }
                    }
                    if (classListBySubject.isEmpty()) {
                        Evaluation eval1 = new Evaluation(TypeOfClass.MATHEMATICAL_ANALYSIS_LECTURE,
                                UserManager.getInstance().getCurrentUser().getUid(), 20,
                                Subjects.MATH_ANALYSIS);
                        Evaluation eval2 = new Evaluation(TypeOfClass.MATHEMATICAL_ANALYSIS_PRACTICE,
                                UserManager.getInstance().getCurrentUser().getUid(), 30,
                                Subjects.MATH_ANALYSIS);
                        classListFinal.add(eval1);
                        classListFinal.add(eval2);
                    } else if (classListBySubject.size() == 1) {
                        if (classListBySubject.get(0).getTypeOfClass() == TypeOfClass
                                .MATHEMATICAL_ANALYSIS_LECTURE) {
                            Evaluation eval = new Evaluation(TypeOfClass.MATHEMATICAL_ANALYSIS_PRACTICE,
                                    UserManager.getInstance().getCurrentUser().getUid(), 0,
                                    Subjects.MATH_ANALYSIS);
                            classListFinal.add(eval);
                            classListFinal.addAll(classListBySubject);
                        } else {
                            Evaluation eval = new Evaluation(TypeOfClass.MATHEMATICAL_ANALYSIS_LECTURE,
                                    UserManager.getInstance().getCurrentUser().getUid(), 0,
                                    Subjects.MATH_ANALYSIS);
                            classListFinal.add(eval);
                            classListFinal.addAll(classListBySubject);
                        }
                    } else {
                        classListFinal.addAll(classListBySubject);
                    }
                    break;
                case ELECTROTECHNICS_AND_ELECTRONICS:
                    for (Evaluation evaluation : mClassList) {
                        if (evaluation.getSubjectId().equals(Subjects.ELECTROTECHNICS_AND_ELECTRONICS)) {
                            classListBySubject.add(evaluation);
                        }
                    }
                    if (classListBySubject.isEmpty()) {
                        Evaluation eval1 = new Evaluation(TypeOfClass.ELECTROTECHNICS_AND_ELECTRONICS_LECTURE,
                                UserManager.getInstance().getCurrentUser().getUid(), 20,
                                Subjects.ELECTROTECHNICS_AND_ELECTRONICS);
                        Evaluation eval2 = new Evaluation(TypeOfClass.ELECTROTECHNICS_AND_ELECTRONICS_LABORATORY,
                                UserManager.getInstance().getCurrentUser().getUid(), 30,
                                Subjects.ELECTROTECHNICS_AND_ELECTRONICS);
                        classListFinal.add(eval1);
                        classListFinal.add(eval2);
                    } else if (classListBySubject.size() == 1) {
                        if (classListBySubject.get(0).getTypeOfClass() == TypeOfClass
                                .ELECTROTECHNICS_AND_ELECTRONICS_LECTURE) {
                            Evaluation eval = new Evaluation(TypeOfClass.ELECTROTECHNICS_AND_ELECTRONICS_LABORATORY,
                                    UserManager.getInstance().getCurrentUser().getUid(), 0,
                                    Subjects.ELECTROTECHNICS_AND_ELECTRONICS);
                            classListFinal.add(eval);
                            classListFinal.addAll(classListBySubject);
                        } else {
                            Evaluation eval = new Evaluation(TypeOfClass.ELECTROTECHNICS_AND_ELECTRONICS_LECTURE,
                                    UserManager.getInstance().getCurrentUser().getUid(), 0,
                                    Subjects.ELECTROTECHNICS_AND_ELECTRONICS);
                            classListFinal.add(eval);
                            classListFinal.addAll(classListBySubject);
                        }
                    } else {
                        classListFinal.addAll(classListBySubject);
                    }

                    break;
                case ALGO_AND_PROGRAMMING:
                    for (Evaluation evaluation : mClassList) {
                        if (evaluation.getSubjectId().equals(Subjects.ALGO_AND_PROGRAMMING)) {
                            classListBySubject.add(evaluation);
                        }
                    }
                    if (classListBySubject.isEmpty()) {
                        Evaluation eval1 = new Evaluation(TypeOfClass.PROGRAMMING_PART_2_LECTURE,
                                UserManager.getInstance().getCurrentUser().getUid(), 20,
                                Subjects.ALGO_AND_PROGRAMMING);
                        Evaluation eval2 = new Evaluation(TypeOfClass.PROGRAMMING_PART_2_LABORATORY,
                                UserManager.getInstance().getCurrentUser().getUid(), 30,
                                Subjects.ALGO_AND_PROGRAMMING);
                        classListFinal.add(eval1);
                        classListFinal.add(eval2);
                    } else if (classListBySubject.size() == 1) {
                        if (classListBySubject.get(0).getTypeOfClass() == TypeOfClass
                                .PROGRAMMING_PART_2_LECTURE) {
                            Evaluation eval = new Evaluation(TypeOfClass.PROGRAMMING_PART_2_LABORATORY,
                                    UserManager.getInstance().getCurrentUser().getUid(), 0,
                                    Subjects.ALGO_AND_PROGRAMMING);
                            classListFinal.add(eval);
                            classListFinal.addAll(classListBySubject);
                        } else {
                            Evaluation eval = new Evaluation(TypeOfClass.PROGRAMMING_PART_2_LECTURE,
                                    UserManager.getInstance().getCurrentUser().getUid(), 0,
                                    Subjects.ALGO_AND_PROGRAMMING);
                            classListFinal.add(eval);
                            classListFinal.addAll(classListBySubject);
                        }
                    } else {
                        classListFinal.addAll(classListBySubject);
                    }


                    break;
                case DISCRET_MATH:
                    for (Evaluation evaluation : mClassList) {
                        if (evaluation.getSubjectId().equals(Subjects.DISCRET_MATH)) {
                            classListBySubject.add(evaluation);
                        }
                    }
                    if (classListBySubject.isEmpty()) {
                        Evaluation eval1 = new Evaluation(TypeOfClass.DISCRET_MATH_LECTURE,
                                UserManager.getInstance().getCurrentUser().getUid(), 20,
                                Subjects.DISCRET_MATH);
                        Evaluation eval2 = new Evaluation(TypeOfClass.DISCRET_MATH_PRACTICE,
                                UserManager.getInstance().getCurrentUser().getUid(), 30,
                                Subjects.DISCRET_MATH);
                        classListFinal.add(eval1);
                        classListFinal.add(eval2);
                    } else if (classListBySubject.size() == 1) {
                        if (classListBySubject.get(0).getTypeOfClass() == TypeOfClass
                                .DISCRET_MATH_LECTURE) {
                            Evaluation eval = new Evaluation(TypeOfClass.DISCRET_MATH_PRACTICE,
                                    UserManager.getInstance().getCurrentUser().getUid(), 0,
                                    Subjects.DISCRET_MATH);
                            classListFinal.add(eval);
                            classListFinal.addAll(classListBySubject);
                        } else {
                            Evaluation eval = new Evaluation(TypeOfClass.DISCRET_MATH_LECTURE,
                                    UserManager.getInstance().getCurrentUser().getUid(), 0,
                                    Subjects.DISCRET_MATH);
                            classListFinal.add(eval);
                            classListFinal.addAll(classListBySubject);
                        }
                    } else {
                        classListFinal.addAll(classListBySubject);
                    }

                    break;
                case UKRAINIAN:
                    for (Evaluation evaluation : mClassList) {
                        if (evaluation.getSubjectId().equals(Subjects.UKRAINIAN)) {
                            classListBySubject.add(evaluation);
                        }
                    }
                    if (classListBySubject.isEmpty()) {
                        Evaluation eval = new Evaluation(TypeOfClass.UKRAINIAN_PRACTICE,
                                UserManager.getInstance().getCurrentUser().getUid(), 20,
                                Subjects.UKRAINIAN);

                        classListFinal.add(eval);
                    } else {
                        classListFinal.addAll(classListBySubject);
                    }

                    break;
                case PHYSICS:
                    for (Evaluation evaluation : mClassList) {
                        if (evaluation.getSubjectId().equals(Subjects.PHYSICS)) {
                            classListBySubject.add(evaluation);
                        }
                    }
                    if (classListBySubject.isEmpty()) {
                        Evaluation eval1 = new Evaluation(TypeOfClass.PHYSICS_LECTURE,
                                UserManager.getInstance().getCurrentUser().getUid(), 20,
                                Subjects.PHYSICS);
                        Evaluation eval2 = new Evaluation(TypeOfClass.PHYSICS_PRACTICE,
                                UserManager.getInstance().getCurrentUser().getUid(), 20,
                                Subjects.PHYSICS);
                        Evaluation eval3 = new Evaluation(TypeOfClass.PHYSICS_LABORATORY,
                                UserManager.getInstance().getCurrentUser().getUid(), 30,
                                Subjects.PHYSICS);
                        classListFinal.add(eval1);
                        classListFinal.add(eval2);
                        classListFinal.add(eval3);
                    } else if (classListBySubject.size() == 1) {
                        if (classListBySubject.get(0).getTypeOfClass() == TypeOfClass
                                .PHYSICS_LECTURE) {
                            Evaluation eval1 = new Evaluation(TypeOfClass.PHYSICS_PRACTICE,
                                    UserManager.getInstance().getCurrentUser().getUid(), 20,
                                    Subjects.PHYSICS);
                            Evaluation eval2 = new Evaluation(TypeOfClass.PHYSICS_LABORATORY,
                                    UserManager.getInstance().getCurrentUser().getUid(), 30,
                                    Subjects.PHYSICS);
                            classListFinal.add(eval1);
                            classListFinal.add(eval2);
                            classListFinal.addAll(classListBySubject);
                        } else if (classListBySubject.get(0).getTypeOfClass() == TypeOfClass
                                .PHYSICS_PRACTICE) {
                            Evaluation eval1 = new Evaluation(TypeOfClass.PHYSICS_LECTURE,
                                    UserManager.getInstance().getCurrentUser().getUid(), 20,
                                    Subjects.PHYSICS);
                            Evaluation eval2 = new Evaluation(TypeOfClass.PHYSICS_LABORATORY,
                                    UserManager.getInstance().getCurrentUser().getUid(), 30,
                                    Subjects.PHYSICS);
                            classListFinal.add(eval1);
                            classListFinal.add(eval2);
                            classListFinal.addAll(classListBySubject);
                        }
                        else {

                            Evaluation eval1 = new Evaluation(TypeOfClass.PHYSICS_LECTURE,
                                    UserManager.getInstance().getCurrentUser().getUid(), 20,
                                    Subjects.PHYSICS);
                            Evaluation eval2 = new Evaluation(TypeOfClass.PHYSICS_PRACTICE,
                                    UserManager.getInstance().getCurrentUser().getUid(), 20,
                                    Subjects.PHYSICS);
                            classListFinal.add(eval1);
                            classListFinal.add(eval2);
                            classListFinal.addAll(classListBySubject);
                        }
                    } else if(classListBySubject.size() == 2) {
                        if ((classListBySubject.get(0).getTypeOfClass() == TypeOfClass
                                .PHYSICS_LECTURE & classListBySubject.get(1).getTypeOfClass() == TypeOfClass
                                .PHYSICS_PRACTICE) | (classListBySubject.get(1).getTypeOfClass() == TypeOfClass
                                .PHYSICS_LECTURE & classListBySubject.get(0).getTypeOfClass() == TypeOfClass
                                .PHYSICS_PRACTICE)){

                            Evaluation eval = new Evaluation(TypeOfClass.PHYSICS_LABORATORY,
                                    UserManager.getInstance().getCurrentUser().getUid(), 30,
                                    Subjects.PHYSICS);
                            classListFinal.add(eval);
                            classListFinal.addAll(classListBySubject);

                        }
                        else if((classListBySubject.get(0).getTypeOfClass() == TypeOfClass
                                .PHYSICS_LECTURE & classListBySubject.get(1).getTypeOfClass() == TypeOfClass
                                .PHYSICS_LABORATORY) | (classListBySubject.get(1).getTypeOfClass() == TypeOfClass
                                .PHYSICS_LECTURE & classListBySubject.get(0).getTypeOfClass() == TypeOfClass
                                .PHYSICS_LABORATORY)){

                            Evaluation eval = new Evaluation(TypeOfClass.PHYSICS_PRACTICE,
                                    UserManager.getInstance().getCurrentUser().getUid(), 20,
                                    Subjects.PHYSICS);
                            classListFinal.add(eval);
                            classListFinal.addAll(classListBySubject);

                        }
                        else{
                            Evaluation eval = new Evaluation(TypeOfClass.PHYSICS_LECTURE,
                                    UserManager.getInstance().getCurrentUser().getUid(), 20,
                                    Subjects.PHYSICS);
                            classListFinal.add(eval);
                            classListFinal.addAll(classListBySubject);

                        }
                    }
                    else {
                        classListFinal.addAll(classListBySubject);
                    }

                    break;
                case ENGLISH:
                    for (Evaluation evaluation : mClassList) {
                        if (evaluation.getSubjectId().equals(Subjects.ENGLISH)) {
                            classListBySubject.add(evaluation);
                        }
                    }
                    if (classListBySubject.isEmpty()) {
                        Evaluation eval = new Evaluation(TypeOfClass.ENGLISH_PRACTICE,
                                UserManager.getInstance().getCurrentUser().getUid(), 20,
                                Subjects.ENGLISH);

                        classListFinal.add(eval);
                    } else {
                        classListFinal.addAll(classListBySubject);
                    }

            }
            classesAdapter = new ClassesAdapter(getActivity(), classListFinal);

            classListView = getView().findViewById(R.id.class_list_view);
            classListView.setAdapter(classesAdapter);
        });


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
