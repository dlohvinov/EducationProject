package iot.lviv.ua.educationproject;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
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

    SwipeRefreshLayout mSwipeRefreshLayout;
    CorruptionListAdapter corruptionListAdapter;
    ListView corruptionListListView;
    View mCorruptionListView;
    static ArrayList<CorruptionReport> corruptionReportList = new ArrayList<CorruptionReport>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mCorruptionListView = inflater.inflate(R.layout.fragment_corruption_list, container, false);
        return mCorruptionListView;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        corruptionListAdapter = new CorruptionListAdapter(getActivity(),
                CorruptionListFragment.corruptionReportList);

        corruptionListListView = (ListView) view.findViewById(R.id.fragment_corruption_list);
        corruptionListListView.setAdapter(corruptionListAdapter);


        // SwipeRefreshLayout
        mSwipeRefreshLayout = (SwipeRefreshLayout) getActivity()
                .findViewById(R.id.fragment_corruption_list_swipe);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Fetching data from server
                loadRecyclerViewData();
            }
        });
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        mSwipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {

                mSwipeRefreshLayout.setRefreshing(true);

                // Fetching data from server
                loadRecyclerViewData();
            }
        });

        loadRecyclerViewData();
    }

    @Override
    public void onClick(View v) {

    }


    private void loadRecyclerViewData() {

        FirebaseManager firebaseManager = FirebaseManager.getInstance();
        firebaseManager.loadDataBase(new FirebaseManager.Callback<Evaluation>() {
            @Override
            public void onSuccess(List<Evaluation> evaluationList,
                                  List<CorruptionReport> corruptionReports) {
                CorruptionListFragment.corruptionReportList.clear();
                CorruptionListFragment.corruptionReportList.addAll(corruptionReports);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        corruptionListAdapter = new CorruptionListAdapter(getActivity(),
                CorruptionListFragment.corruptionReportList);

        corruptionListListView = (ListView) getView().findViewById(R.id.fragment_corruption_list);
        corruptionListListView.setAdapter(corruptionListAdapter);
        // Showing refresh animation before making call
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
