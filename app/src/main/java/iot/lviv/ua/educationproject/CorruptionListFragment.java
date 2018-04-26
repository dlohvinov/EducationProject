package iot.lviv.ua.educationproject;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Daniil on 4/25/2018.
 */

public class CorruptionListFragment extends Fragment implements View.OnClickListener {

    View mCorruptionListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mCorruptionListView = inflater.inflate(R.layout.fragment_corruption_list, container, false);

        return mCorruptionListView;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        ArrayList<CorruptionReport> corruptionReportList = new ArrayList<CorruptionReport>();
        CorruptionListAdapter corruptionListAdapter = new CorruptionListAdapter(getActivity(),
                CorruptionFragment.corruptionList);

        ListView corruptionListListView = (ListView) view.findViewById(R.id.fragment_corruption_list);
        corruptionListListView.setAdapter(corruptionListAdapter);

//        for (int i = 0; i < CorruptionFragment.corruptionList.size(); i++) {
//            TextView lectorName = (TextView) view.findViewById(R.id.item_corruption_lector);
//            lectorName.setText(CorruptionFragment.corruptionList.get(i).getLecturerName());
//            TextView textCorruption = (TextView) view.findViewById(R.id.item_corruption_text);
//            textCorruption.setText(CorruptionFragment.corruptionList.get(i).getReportText());
//        }
    }

    @Override
    public void onClick(View v) {

    }
}
