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
    }

    public void sendEvaluation(Evaluation rate){
        mDatabaseReference.child("Groups").child("Group").child("Evaluation").push().setValue(rate);
    }


    public interface Callback<T>{
        void onSuccess(List<Evaluation> evaluationList, List<CorruptionReport> corruptionReportList);
        void onCancelled(DatabaseError databaseError);
    }

    public void loadDataBase(final Callback<Evaluation> callback){
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<Evaluation> rates = new ArrayList<>();
                List<CorruptionReport> corruptionReports = new ArrayList<>();
                for (DataSnapshot postSnapshot: snapshot.child("Groups").child("Group").
                        child("Evaluation").getChildren()) {
                    Evaluation evaluation = postSnapshot.getValue(Evaluation.class);
                    rates.add(evaluation);
                }
                for (DataSnapshot postSnapshot : snapshot.child("CorruptionReports").getChildren()) {
                    CorruptionReport corruptionReport = postSnapshot.getValue(CorruptionReport.class);
                    corruptionReports.add(corruptionReport);
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