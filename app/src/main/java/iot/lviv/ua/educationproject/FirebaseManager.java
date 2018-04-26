package iot.lviv.ua.educationproject;


import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FirebaseManager {

    private static FirebaseManager firebaseManager;

    public static final int RC_SIGN_IN = 1;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    List <Evaluation> evaluations = null;

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
        mDatabaseReference = mFirebaseDatabase.getReference();
        mDatabaseReference.child("CorruptionReports").push().setValue(corruptionReport);
    }

    public void sendEvaluation(Evaluation evaluation){
        mDatabaseReference.child("Groups").child("Group").child("Evaluation").child("Period").push().setValue(evaluation);
    }

    public List<Evaluation> getEvaluations(){

        if (evaluations == null){
            return getEvaluations();
        }else {
            return evaluations;
        }
    }

    public void loadDataBase(){
        mDatabaseReference.child("Groups").child("Group").child("Evaluation").child("Period").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                evaluations = new ArrayList<>();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Evaluation evaluation = postSnapshot.getValue(Evaluation.class);
                    evaluations.add(evaluation);
                }
            }


            public void onCancelled(DatabaseError databaseError) {
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
