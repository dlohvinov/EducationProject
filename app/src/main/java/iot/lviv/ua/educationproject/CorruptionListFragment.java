package iot.lviv.ua.educationproject;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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
        ArrayList<CorruptionReport> corruptionReportList = new ArrayList<CorruptionReport>();
        CorruptionListAdapter corruptionListAdapter = new CorruptionListAdapter(getActivity(),
                corruptionReportList);

        ListView corruptionListListView = (ListView) view.findViewById(R.id.fragment_corruption_list);
        corruptionListListView.setAdapter(corruptionListAdapter);




    }

    @Override
    public void onClick(View v) {

    }
}
