package iot.lviv.ua.educationproject;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CorruptionLookFragment extends Fragment{
    View mCorruptionLookView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        mCorruptionLookView = inflater.inflate(R.layout.fragment_corruption_look, container, false);

        return mCorruptionLookView;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

        TextView corruptionLookLectorName = getActivity().findViewById
                (R.id.corruption_look_fragment_lector_name);
        TextView corruptionLookReportDescription = getActivity().findViewById
                (R.id.corruption_look_fragment_report_description);
        TextView corruptionLookContacts = getActivity().findViewById
                (R.id.corruption_look_fragment_contacts);

        corruptionLookLectorName.setText(CorruptionListFragment.corruptionReportList.get
                (CorruptionListAdapter.getPos()).getLecturerName());
        corruptionLookReportDescription.setText(CorruptionListFragment.corruptionReportList.get
                (CorruptionListAdapter.getPos()).getReportText());
        corruptionLookContacts.setText(CorruptionListFragment.corruptionReportList.get
                (CorruptionListAdapter.getPos()).getStudentContacts());
    }
}
