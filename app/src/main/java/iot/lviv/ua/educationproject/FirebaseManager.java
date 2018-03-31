package iot.lviv.ua.educationproject;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseManager {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    FirebaseManager() {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
    }

    public void sendCorruptionReport(CorruptionReport corruptionReport) {
        mDatabaseReference = mFirebaseDatabase.getReference().child("CorruptionReports");
        mDatabaseReference.push().setValue(corruptionReport);
    }
}
