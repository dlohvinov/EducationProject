package iot.lviv.ua.educationproject;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

import iot.lviv.ua.educationproject.databinding.ActivityNavigationDrawerBinding;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public final int RC_SIGN_IN = 1;
    public final String CURRENT_USER_KEY = "CURRENT USER";

    private FirebaseUser mFirebaseUser;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FragmentManager mFragmentManager;
    private SubjectFragment mSubjectFragment;
    private UserManager mUserManager;
    private FirebaseManager mFirebaseManager;
    private SharedPreferencesManager sharedPreferencesManager;
    private ActivityNavigationDrawerBinding mBinding;
    private Query mStudentRegistrationCheck;
    private ValueEventListener mRegistrationEventListener;
    private ValueEventListener mCurrentUserUpdateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainActivity", "onCreate");

        setContentView(R.layout.activity_navigation_drawer);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_navigation_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mUserManager = UserManager.getInstance();
        mFragmentManager = getFragmentManager();
        mFirebaseManager = FirebaseManager.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mSubjectFragment = new SubjectFragment();


//        FirebaseManager.getInstance().sendEvaluation(new Evaluation(TypeOfClass.ENGLISH_PRACTICE, 2, 15));
        sharedPreferencesManager = new SharedPreferencesManager(this);

//        FirebaseManager.getInstance().sendCorruptionReport(new CorruptionReport("Volodymyr", "English", "Good")); // Для тестування

        mFirebaseManager.loadDataBase(new FirebaseManager.Callback<Evaluation>() {
            @Override
            public void onSuccess(List<Evaluation> evaluationList, List<CorruptionReport> corruptionReportList) {
               // Log.d("my_log", evaluationList.get(evaluationList.size()-1).getStudentId() + " "
                    //    + corruptionReportList.get(corruptionReportList.size()-1).getDateAndTime());

                //sharedPreferencesManager.setAverageMark(TypeOfClass.ENGLISH_PRACTICE,
                  //      Util.getAverageEvaluation(TypeOfClass.ENGLISH_PRACTICE, evaluationList));

              //  Log.d("my_log", sharedPreferencesManager.getAverageMark(TypeOfClass.ENGLISH_PRACTICE) + "");
                // Log.d("my_log",  "67");
                // for (Evaluation evaluation : Util.sortEvaluationsByDate(evaluationList)) {
                 //   Log.d("my_log", evaluation.getDateAndTime() + ", ");
              //  }
             }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        mRegistrationEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    mFragmentManager.beginTransaction().replace(R.id.place_holder, mSubjectFragment)
                            .addToBackStack(null).commit();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        };

        mCurrentUserUpdateListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                mUserManager.setCurrentUser(user);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        if (mUserManager.getCurrentUser() != null) {
            Toast.makeText(this, "user not null in userman", Toast.LENGTH_SHORT).show();

            mBinding.setUser(mUserManager.getCurrentUser());
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //selecting navigation view and attaching listeners
        NavigationView navigationView = findViewById(R.id.nav_view_student);
        navigationView.setNavigationItemSelectedListener(this);

        //opening list of subjects
//        mFragmentManager.beginTransaction().replace(R.id.place_holder, mSubjectFragment)
//                .addToBackStack(null).commit();

        Log.d("MainActivity", "before FirebaseAuth");
        mAuthStateListener = firebaseAuth -> {
            Log.d("MainActivity", "firebaseAuthInvoked");
            mFirebaseUser = firebaseAuth.getCurrentUser();
            if (mFirebaseUser != null) {
                //user is signed in
                Log.d("MainActivity", "user is signed in");
            } else {
                // user is signed out
                Log.d("MainActivity", " user is signed out");
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

        //Setting the user avatar and email
        setNavHeader();
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
        Log.d("MainActivity", "onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.options_items, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "onOptionsItemSelected", Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", "onOptionsItemSelected");

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
        Log.d("MainActivity", "onActivityResult");


        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                Log.d("MainActivity", "RC_SIGN_IN");
                mStudentRegistrationCheck = mFirebaseManager.getRootDatabaseReference().child("Users")
                        .orderByKey().equalTo(mFirebaseUser.getUid());
                mStudentRegistrationCheck.addListenerForSingleValueEvent(mRegistrationEventListener);

                RegistrationFragment registrationFragment = new RegistrationFragment();
                mFragmentManager.beginTransaction().replace(R.id.place_holder, registrationFragment).commit();
                mBinding.setUser(UserManager.getInstance().getCurrentUser());
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
        Log.d("MainActivity", "onResume");
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        Log.d("MainActivity", "onPause");
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }

    /**
     * method to set the appearence of navigation header
     */
    private void setNavHeader() {
        Log.d("MainActivity", "setNavHeader");
//        View navHeader = getLayoutInflater().inflate(R.layout.nav_header_navigation_drawer, thi);
        if (mFirebaseUser == null) {
            Toast.makeText(this, "No User", Toast.LENGTH_SHORT).show();
        } else {
            //Setting user avatar
            if (UserManager.getInstance().getCurrentUser().getPhotoUri() != null) {
                ImageView avatar = this.findViewById(R.id.user_icon);
                Context context = avatar.getContext();
                Picasso.with(context).load(UserManager.getInstance().getCurrentUser().getPhotoUri()).into(avatar);
            }

            //Setting user name
            TextView username = findViewById(R.id.username_text_view);
            username.setText(mFirebaseUser.getDisplayName());

            //Setting User email
            TextView email = findViewById(R.id.email_text_view);
            email.setText(mFirebaseUser.getEmail());
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d("MainActivity", "onPostResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MainActivity", "onRestart");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.d("MainActivity", "onSaveInstanceState");
        outState.putParcelable(CURRENT_USER_KEY, mUserManager.getCurrentUser());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("MainActivity", "onRestoreInstanceState");
        User newUser = savedInstanceState.getParcelable(CURRENT_USER_KEY);
        mUserManager.setCurrentUser(newUser);
    }
}
