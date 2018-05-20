package iot.lviv.ua.educationproject;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniil on 4/25/2018.
 */

public class CorruptionListFragment extends Fragment implements View.OnClickListener {

    View mCorruptionListView;
    static ArrayList<CorruptionReport> corruptionReportList = new ArrayList<CorruptionReport>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mCorruptionListView = inflater.inflate(R.layout.fragment_corruption_list, container, false);

        return mCorruptionListView;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        FirebaseManager firebaseManager = FirebaseManager.getInstance();
        firebaseManager.loadDataBase(new FirebaseManager.Callback<Evaluation>() {
            @Override
            public void onSuccess(List<Evaluation> evaluationList,
                                  List<CorruptionReport> corruptionReports) {
                if (corruptionReportList.isEmpty()) {
                    CorruptionListFragment.corruptionReportList.addAll(corruptionReports);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        
        CorruptionListAdapter corruptionListAdapter = new CorruptionListAdapter(getActivity(),
                CorruptionListFragment.corruptionReportList);

        ListView corruptionListListView = (ListView) view.findViewById(R.id.fragment_corruption_list);
        corruptionListListView.setAdapter(corruptionListAdapter);
    }

    @Override
    public void onClick(View v) {

    }


}
