package iot.lviv.ua.educationproject;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    private SubjectFragment subjectFragment;
    private FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        fragmentManager = getFragmentManager();

        regFragment = inflater.inflate(R.layout.registration_fragment, container, false);

        nameEditText = regFragment.findViewById(R.id.full_name_registration_edit_text);
        nameEditText.setText(mFirebaseUser.getDisplayName());

        emailEditText = regFragment.findViewById(R.id.email_registration_edit_text);
        emailEditText.setText(mFirebaseUser.getEmail());

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
        subjectFragment = new SubjectFragment();
        fragmentManager.beginTransaction().
                replace(R.id.place_holder, subjectFragment)
                .commit();
    }
}
