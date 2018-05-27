package iot.lviv.ua.educationproject;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class CorruptionFragment extends Fragment implements View.OnClickListener {

    View mCorruptionView;
    View mCorruptionSendButton;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mCorruptionView = inflater.inflate(R.layout.fragment_corruption, container, false);
        mCorruptionSendButton = mCorruptionView.findViewById(R.id.corruption_send_button);

        mCorruptionSendButton.setOnClickListener(this);


        return mCorruptionView;
    }

    @Override
    public void onClick(View v) {
        FirebaseManager firebaseManager = FirebaseManager.getInstance();

        EditText studentName = mCorruptionView.findViewById(R.id.corruption_student);
        EditText lectorName = mCorruptionView.findViewById(R.id.corruption_lector);
        EditText corruptionText = mCorruptionView.findViewById(R.id.corruption_text);

        CorruptionReport corruptionReport = new CorruptionReport();

        if (studentName.getText().toString().length() > 0) {
            if (lectorName.getText().toString().length() > 0) {
                if (corruptionText.getText().toString().length() > 0) {

                    corruptionReport.setStudentContacts(studentName.getText().toString());
                    studentName.setText("");
                    corruptionReport.setLecturerName(lectorName.getText().toString());
                    lectorName.setText("");
                    corruptionReport.setReportText(corruptionText.getText().toString());
                    corruptionText.setText("");
                    corruptionReport.setDateAndTime(Util.getDate());
                    corruptionReport.setVisibility(true);
                    firebaseManager.sendCorruptionReport(corruptionReport);
                } else {
                    corruptionText.requestFocus();
                    Toast.makeText(getActivity(), "Corruption text field is too short!",
                            Toast.LENGTH_LONG).show();
                    return;
                }
            } else {
                lectorName.requestFocus();
                Toast.makeText(getActivity(), "Lecturer's name field is too short!",
                        Toast.LENGTH_LONG).show();
                return;
            }
        } else {
            studentName.requestFocus();
            Toast.makeText(getActivity(), "Your name field is too short!",
                    Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(getActivity(), "Thank you for your report! <3",
                Toast.LENGTH_LONG).show();

    }
}