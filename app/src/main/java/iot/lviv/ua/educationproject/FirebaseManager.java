package iot.lviv.ua.educationproject;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FirebaseManager {

    private static FirebaseManager firebaseManager;

    public static final int RC_SIGN_IN = 1;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mFirebaseAuth;

    private Map <String, Object> sendMap = new HashMap<>();

    private FirebaseManager() {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();
        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    public static FirebaseManager getInstance() {
        if (firebaseManager == null) {
            firebaseManager = new FirebaseManager();
            return firebaseManager;
        } else {
            return firebaseManager;
        }
    }


    public void sendCorruptionReport(CorruptionReport corruptionReport) {
        mDatabaseReference.child("CorruptionReports").push().setValue(corruptionReport);

        String studentContacts = corruptionReport.getStudentContacts();
        String reportText = corruptionReport.getReportText();
        String lecturerName = corruptionReport.getLecturerName();
        String dateAndTime = corruptionReport.getDateAndTime();
        Boolean visibility = corruptionReport.isVisibility();

        sendMap.put("studentContacts", studentContacts);
        sendMap.put("reportText", reportText);
        sendMap.put("lecturerName", lecturerName);
        sendMap.put("dateAndTime", dateAndTime);
        sendMap.put("visibility", visibility);
        db.collection("CorruptionReports").add(sendMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d("onsuccess", "success");

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("onsuccess", "fail");
                    }
                });

        sendMap.clear();
    }

    public void sendEvaluation(Evaluation evaluation) {
        mDatabaseReference.child("Groups").child("Group").child("Evaluation")
                .push().setValue(evaluation);

        sendMap.put("TypeOfClass", evaluation.getTypeOfClass().toString());
//        sendMap.put("studentId", evaluation.getStudentId());
        sendMap.put("evaluation", evaluation.getEvaluation());
        sendMap.put("dateAndTime", evaluation.getDateAndTime());
        sendMap.put("subjectId", evaluation.getSubjectId().toString());
        sendMap.put("studentId", UserManager.getInstance().getCurrentUser().getUid());

        db.collection("Evaluations").add(sendMap);
        sendMap.clear();
    }


    public interface Callback<T> {
        void onSuccess(List<Evaluation> evaluationList, List<CorruptionReport> corruptionReportList);

        void onCancelled(DatabaseError databaseError);
    }

    public void loadDataBase(final Callback<Evaluation> callback) {

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<Evaluation> rates = new ArrayList<>();
                List<CorruptionReport> corruptionReports = new ArrayList<>();
                for (DataSnapshot postSnapshot : snapshot.child("Groups").child("Group").
                        child("Evaluation").getChildren()) {
                    Evaluation evaluation = postSnapshot.getValue(Evaluation.class);
                    rates.add(evaluation);
                }
                for (DataSnapshot postSnapshot : snapshot.child("CorruptionReports").getChildren()) {
                    CorruptionReport corruptionReport = postSnapshot.getValue(CorruptionReport.class);
                    if (corruptionReport.isVisibility()) {
                        corruptionReports.add(corruptionReport);
                    }

                }
                if (callback != null) {
                    callback.onSuccess(rates, corruptionReports);
                }
            }


            public void onCancelled(DatabaseError databaseError) {
                callback.onCancelled(databaseError);
                Log.w("TAGGG", "Failed to read value.", databaseError.toException());
            }

        });
    }

    public FirebaseDatabase getFirebaseDatabase() {
        return mFirebaseDatabase;
    }

    public DatabaseReference getRootDatabaseReference() {
        return mDatabaseReference;
    }
}