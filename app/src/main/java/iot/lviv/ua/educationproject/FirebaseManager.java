package iot.lviv.ua.educationproject;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;

public class FirebaseManager {

    public static final int RC_SIGN_IN = 1;
    private Activity activity;
    private String mUsername;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    FirebaseManager(Activity activity) {
        this.activity = activity;
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    public void sendCorruptionReport(CorruptionReport corruptionReport) {
        mDatabaseReference = mFirebaseDatabase.getReference().child("CorruptionReports");
        mDatabaseReference.push().setValue(corruptionReport);
    }

    public FirebaseAuth.AuthStateListener createAuthStateListener() {
        mAuthStateListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                //user is signed in
                onSignedInInitialize(user.getDisplayName());
                Toast.makeText(activity, "You're now signed in", Toast.LENGTH_SHORT).show();
            } else {
                // user is signed out
                Toast.makeText(activity, "You're now signed out", Toast.LENGTH_SHORT).show();
                activity.startActivityForResult(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setIsSmartLockEnabled(false)
                                .setAvailableProviders(Arrays.asList(
                                        new AuthUI.IdpConfig.EmailBuilder().build()))
                                .setTosUrl("https://superapp.example.com/terms-of-service.html")
                                .setPrivacyPolicyUrl("https://superapp.example.com/privacy-policy.html")
                                .build(),
                        RC_SIGN_IN);
            }
        };
        return mAuthStateListener;
    }

    public void attachAuthStateListener() {
        mFirebaseAuth.addAuthStateListener(this.createAuthStateListener());
    }

    private void onSignedInInitialize(String username) {
        mUsername = username;
    }


}
