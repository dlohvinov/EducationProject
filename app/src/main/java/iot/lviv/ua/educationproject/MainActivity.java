package iot.lviv.ua.educationproject;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public final int RC_SIGN_IN = 1;

    private FirebaseUser mUser;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFirebaseAuth = FirebaseAuth.getInstance();


        //starting CORRUPTION activity
        TextView corruptionButton = findViewById(R.id.corruption_button);
        corruptionButton.setOnClickListener(view -> {
            CorruptionFragment corruptionFragment = new CorruptionFragment();
            FragmentManager manager = getFragmentManager();
            manager.beginTransaction().replace(R.id.content_navigation_drawer_view, corruptionFragment)
                    .addToBackStack(null).commit();

            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }
        });

//        attaching SUBJECTS LIST Adapter to a ListView activity_subjects.xml
        ArrayList<Subject> subjectList = new ArrayList<>();
        SubjectsAdapter subjectsAdapter = new SubjectsAdapter(this, subjectList);

        ListView subjectListView = findViewById(R.id.subject_list_view);
        subjectListView.setAdapter(subjectsAdapter);

        //TODO: add subjects from Firebase and delete this template
        Subject subject1 = new Subject();
        Subject subject2 = new Subject();
        subject1.setName("Math Analysis");
        subjectList.add(subject1);
        subject2.setName("Physics");
        subjectList.add(subject2);

        

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        mAuthStateListener = firebaseAuth -> {
            mUser = firebaseAuth.getCurrentUser();
            if (mUser != null) {
                //user is signed in
                Toast.makeText(MainActivity.this, "You're signed in", Toast.LENGTH_SHORT).show();
                setNavHeader();
            } else {
                // user is signed out
                Toast.makeText(MainActivity.this, "You're signed out", Toast.LENGTH_SHORT).show();
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Signed in", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Sign in canceled", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        mUser = mFirebaseAuth.getCurrentUser();
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
     * method to set the appearance of navigation header
     */
    private void setNavHeader() {

        if (mUser == null) {
            Toast.makeText(this, "No User", Toast.LENGTH_SHORT).show();
        } else {
            //Setting user avatar
            if (mUser.getPhotoUrl() != null) {
                ImageView avatar = mNavigationView.getHeaderView(0).findViewById(R.id.user_icon);
                Context context = avatar.getContext();
                Picasso.with(context).load(mUser.getPhotoUrl()).into(avatar);
            }

            //Setting user name
            TextView username = mNavigationView.getHeaderView(0).findViewById(R.id.username_text_view);
            username.setText(mUser.getDisplayName());

            //Setting User email
            TextView email = mNavigationView.getHeaderView(0).findViewById(R.id.email_text_view);
            email.setText(mUser.getEmail());
        }
    }

}
