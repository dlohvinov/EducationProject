package iot.lviv.ua.educationproject;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationFragment extends Fragment implements View.OnClickListener{

    private FirebaseUser mFirebaseUser;
    private View regFragment;
    private EditText nameEditText;
    private EditText emailEditText;
    private Spinner groupNumberSpinner;
    private TextView signUpButton;
    private CheckBox mEducatorRequest;
    private SubjectFragment subjectFragment;
    private FragmentManager mFragmentManager;
    private UserManager mUserManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d("RegistrationFragment", "onCreateView");
        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mFragmentManager = getFragmentManager();
        mUserManager = UserManager.getInstance();


        regFragment = inflater.inflate(R.layout.registration_fragment, container, false);

        nameEditText = regFragment.findViewById(R.id.full_name_registration_edit_text);
        nameEditText.setText(mFirebaseUser.getDisplayName());

        emailEditText = regFragment.findViewById(R.id.email_registration_edit_text);
        emailEditText.setText(mFirebaseUser.getEmail());

        mEducatorRequest = regFragment.findViewById(R.id.educator_request_check_box);

        groupNumberSpinner = regFragment.findViewById(R.id.group_choose_spinner);

        ArrayAdapter<CharSequence> groupAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.group_list, android.R.layout.simple_spinner_item);
        groupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groupNumberSpinner.setAdapter(groupAdapter);

        signUpButton = regFragment.findViewById(R.id.registration_finish_button);
        signUpButton.setOnClickListener(this);

        return regFragment;
    }

    @Override
    public void onClick(View v) {

        Log.d("RegistrationFragment", "onClick");
        if (mEducatorRequest.isChecked()) {
            Educator educator = new Educator(mFirebaseUser.getDisplayName(), mFirebaseUser.getEmail(), mFirebaseUser.getUid());
            educator.setPhotoUri(mFirebaseUser.getPhotoUrl());
            mUserManager.setCurrentUser(educator);

        } else {
            mUserManager.setCurrentUser(new Student(mFirebaseUser.getDisplayName(), mFirebaseUser.getEmail(),
                    mFirebaseUser.getUid(), groupNumberSpinner.getSelectedItem().toString()));
        }

        mUserManager.pushUserToDatabase();

        subjectFragment = new SubjectFragment();
        mFragmentManager.beginTransaction().
                replace(R.id.place_holder, subjectFragment)
                .commit();
    }
}
