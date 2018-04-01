package iot.lviv.ua.educationproject;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class CorruptionFragment extends Fragment {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    private FirebaseManager mFirebaseManager;
    private List<CorruptionReport> corruptionReportList = new LinkedList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mFirebaseManager = new FirebaseManager(getActivity());

        View corruptionView = inflater.inflate(R.layout.fragment_corruption, container,false);

        //Processing CORRUPTION reports
        TextView corruptionSend = corruptionView.findViewById(R.id.corruption_send);
        corruptionSend.setOnClickListener(view -> {
            view.startAnimation(buttonClick);
            CorruptionReport corruptionReport = new CorruptionReport();

            EditText editText = corruptionView.findViewById(R.id.corruption_student);
            corruptionReport.setStudentName(editText.getText().toString());
            editText.setText("");

            editText = corruptionView.findViewById(R.id.corruption_lector);
            corruptionReport.setLecturerName(editText.getText().toString());
            editText.setText("");

            editText = corruptionView.findViewById(R.id.corruption_text);
            corruptionReport.setReportText(editText.getText().toString());
            editText.setText("");

            mFirebaseManager.sendCorruptionReport(corruptionReport);

            corruptionReportList.add(corruptionReport);
            Toast.makeText(getActivity(), "Thank you for your report! <3",
                    Toast.LENGTH_LONG).show();
        });

        return corruptionView;
    }
}
