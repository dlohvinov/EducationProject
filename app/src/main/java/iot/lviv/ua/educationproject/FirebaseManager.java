package iot.lviv.ua.educationproject;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FirebaseManager {

    private static FirebaseManager firebaseManager;

    public static final int RC_SIGN_IN = 1;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    List <Mark> marks = null;

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

    public void sendMark(Mark mark){
        mDatabaseReference.child("Groups").child("Group").child("Evaluation").child("Period").push().setValue(mark);
    }

    public List<Mark> getMarks(){

        if (marks == null){
            return getMarks();
        }else {
            return marks;
        }
    }

    public void loadDataBase(){
        mDatabaseReference.child("Groups").child("Group").child("Evaluation").child("Period").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                marks = new ArrayList<>();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Mark mark = postSnapshot.getValue(Mark.class);
                    marks.add(mark);
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
