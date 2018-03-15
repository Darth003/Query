package com.devdefiance.android.query.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.devdefiance.android.query.Adapters.SourcesAdapter;
import com.devdefiance.android.query.Objects.SourceModel;
import com.devdefiance.android.query.R;
import com.devdefiance.android.query.utils.AppConstants;
import com.devdefiance.android.query.utils.BasicUtils;

/**
 * Created by darth on 1/9/18.
 * <p>
 * This fragment handles setting the sources to search
 */

public class SourcesFragment extends Fragment implements
        SwitchCompat.OnCheckedChangeListener {

    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    SourcesAdapter sourcesAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.frag_scs_main, container, false);

        // Initialize the views
        recyclerView = mainView.findViewById(R.id.frag_scs_recycler_view);
        SwitchCompat switchAll = mainView.findViewById(R.id.frag_scs_switch_all);
        switchAll.setOnCheckedChangeListener(this);
        // Small screens use a span count of 2, others use 3
        int span = BasicUtils.isLargeScreen(getActivity()) ? 3 : 2;

        // Set up the recycler view
        gridLayoutManager = new GridLayoutManager(container.getContext(), span);
        sourcesAdapter = new SourcesAdapter(container.getContext(), AppConstants.sourceModels);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(sourcesAdapter);
        recyclerView.setHasFixedSize(true);

        return mainView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.frag_scs_menu, menu);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        switch (compoundButton.getId()) {

            case R.id.frag_scs_switch_all:
                // Switch all models in the recycler view
                for (SourceModel sm : sourcesAdapter.models) {
                    sm.setSelected(b);
                }
                sourcesAdapter.notifyDataSetChanged();
                break;
        }
    }
}
