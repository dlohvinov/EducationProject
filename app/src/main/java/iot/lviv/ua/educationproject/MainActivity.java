package iot.lviv.ua.educationproject;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public final int RC_SIGN_IN = 1;

    private FirebaseUser mFirebaseUser;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FragmentManager mFragmentManager;
    private SubjectFragment mSubjectFragment;
    private UserManager mUserManager;
    private FirebaseManager mFirebaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mUserManager = UserManager.getInstance();
        mFragmentManager = getFragmentManager();
        mFirebaseManager = FirebaseManager.getInstance();
        mSubjectFragment = new SubjectFragment();


        FirebaseManager.getInstance().sendEvaluation(new Evaluation(1, 1, 80, 80, 80));
        FirebaseManager.getInstance().sendCorruptionReport(new CorruptionReport("Volodymyr", "English", "Good"));

        mFirebaseManager.loadDataBase(new FirebaseManager.Callback<Evaluation>() {
            @Override
            public void onSuccess(List<Evaluation> evaluationList, List<CorruptionReport> corruptionReportList) {
                Log.d("my_log", evaluationList.get(0).getStudentId() + " " + corruptionReportList.get(0).getDataAndTime());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        //TODO: remove floating action button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //selecting navigation view and attaching listeners
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //opening list of subjects
        mFragmentManager.beginTransaction().replace(R.id.place_holder, mSubjectFragment)
                .addToBackStack(null).commit();

        mAuthStateListener = firebaseAuth -> {
            mFirebaseUser = firebaseAuth.getCurrentUser();
            if (mFirebaseUser != null) {
                //user is signed in
                Toast.makeText(this, "You're signed in", Toast.LENGTH_SHORT).show();
            } else {
                // user is signed out
                Toast.makeText(this, "You're signed out", Toast.LENGTH_SHORT).show();
                MainActivity.this.startActivityForResult(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setIsSmartLockEnabled(false)
                                .setAvailableProviders(Arrays.asList(
                                        new AuthUI.IdpConfig.EmailBuilder().build(),
                                        new AuthUI.IdpConfig.GoogleBuilder().build()))
                                .setTosUrl("https://superapp.example.com/terms-of-service.html")
                                .setPrivacyPolicyUrl("https://superapp.example.com/privacy-policy.html")
                                .build(),
                        RC_SIGN_IN);
            }
        };

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.options_items, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sign_out:
                AuthUI.getInstance().signOut(this);
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_corruption) {
            //starting CORRUPTION fragment
            CorruptionFragment corruptionFragment = new CorruptionFragment();
            mFragmentManager.beginTransaction().replace(R.id.place_holder, corruptionFragment)
                    .addToBackStack(null).commit();
        } else if (id == R.id.nav_corruption_list){
            CorruptionListFragment corruptionListFragment = new CorruptionListFragment();
            mFragmentManager.beginTransaction().replace(R.id.place_holder, corruptionListFragment)
                    .addToBackStack(null).commit();
        } else if (id == R.id.nav_elections) {
        } else if (id == R.id.nav_subjects) {
            mFragmentManager.beginTransaction().replace(R.id.place_holder, mSubjectFragment)
                    .addToBackStack(null).commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Signed in", Toast.LENGTH_SHORT).show();

                Query studentRegistrationCheck = mFirebaseManager.getRootDatabaseReference().child("Users")
                        .orderByKey().equalTo(mFirebaseUser.getUid());
                studentRegistrationCheck.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            mFragmentManager.beginTransaction().replace(R.id.place_holder, mSubjectFragment)
                                    .addToBackStack(null).commit();

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) { }
                });

                RegistrationFragment registrationFragment = new RegistrationFragment();
                mFragmentManager.beginTransaction().replace(R.id.place_holder, registrationFragment).commit();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Sign in canceled", Toast.LENGTH_SHORT).show();
                finish();
            }
        }


        setNavHeader();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }

    /**
     * method to set the appearence of navigation header
     */
    private void setNavHeader() {

        if (mFirebaseUser == null) {
            Toast.makeText(this, "No User", Toast.LENGTH_SHORT).show();
        } else {
            //Setting user avatar
            if (mFirebaseUser.getPhotoUrl() != null) {
                ImageView avatar = findViewById(R.id.user_icon);
                Context context = avatar.getContext();
                Picasso.with(context).load(mFirebaseUser.getPhotoUrl()).into(avatar);
            }

            //Setting user name
            TextView username = findViewById(R.id.username_text_view);
            username.setText(mFirebaseUser.getDisplayName());

            //Setting User email
            TextView email = findViewById(R.id.email_text_view);
            email.setText(mFirebaseUser.getEmail());
        }
    }



}
