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

        CorruptionReport corruptionReport = new CorruptionReport();

        EditText editText = mCorruptionView.findViewById(R.id.corruption_student);
        corruptionReport.setStudentName(editText.getText().toString());
        editText.setText("");

        editText = mCorruptionView.findViewById(R.id.corruption_lector);
        corruptionReport.setLecturerName(editText.getText().toString());
        editText.setText("");

        editText = mCorruptionView.findViewById(R.id.corruption_text);
        corruptionReport.setReportText(editText.getText().toString());
        editText.setText("");

        Toast.makeText(getActivity(), "Thank you for your report! <3",
                Toast.LENGTH_LONG).show();

    }

}
