package com.devdefiance.android.query.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.devdefiance.android.query.Adapters.SourcesAdapter;
import com.devdefiance.android.query.Objects.SourceModel;
import com.devdefiance.android.query.R;
import com.devdefiance.android.query.utils.AppConstants;
import com.devdefiance.android.query.utils.BasicUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by darth on 1/9/18.
 * <p>
 * This fragment handles setting the sources to search
 */

public class SourcesFragment extends Fragment implements
        SwitchCompat.OnCheckedChangeListener,
        View.OnClickListener {

    View mainView;

    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    SourcesAdapter sourcesAdapter;
    ArrayList<SourceModel> sourceModels;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.frag_scs_main, container, false);

        sourceModels = new ArrayList<>();
        // Load the sources
        initSources();

        // Initialize the views
        recyclerView = mainView.findViewById(R.id.frag_scs_recycler_view);
        SwitchCompat switchAll = mainView.findViewById(R.id.frag_scs_switch_all);
        switchAll.setOnCheckedChangeListener(this);

        RelativeLayout dismisser1 = mainView.findViewById(R.id.frag_scs_topcard_dismisser);
        RelativeLayout dismisser2 = mainView.findViewById(R.id.frag_scs_topcard2_dismisser);

        dismisser1.setOnClickListener(this);
        dismisser2.setOnClickListener(this);

        // Small screens use a span count of 2, others use 3
        int span = BasicUtils.isLargeScreen(getActivity()) ? 3 : 2;

        // Set up the recycler view
        gridLayoutManager = new GridLayoutManager(container.getContext(), span);
        sourcesAdapter = new SourcesAdapter(container.getContext(), sourceModels);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(sourcesAdapter);
        recyclerView.setHasFixedSize(true);

        // Set up drag and drop for the recycler view
        setUpDragNDrop();

        return mainView;
    }

    private void initSources() {
        sourceModels.addAll(Arrays.asList(AppConstants.sourceModels));
    }

    public void setUpDragNDrop() {
        ItemTouchHelper.Callback ithcallback = new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return makeFlag(ItemTouchHelper.ACTION_STATE_DRAG,
                        ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.START | ItemTouchHelper.END);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                // Swap positions in source list
                Collections.swap(sourceModels, viewHolder.getAdapterPosition(), target.getAdapterPosition());

                // Swap positions in adapter
                sourcesAdapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(ithcallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
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

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.frag_scs_topcard_dismisser:
                // Animate card dismissal
                BasicUtils.dismissView(mainView.findViewById(R.id.cardView2), 300);
                break;

            case R.id.frag_scs_topcard2_dismisser:
                // Animate card dismissal
                BasicUtils.dismissView(mainView.findViewById(R.id.cardView3), 300);
                break;
        }
    }
}
