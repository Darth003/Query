package com.devdefiance.android.query.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devdefiance.android.query.Objects.SourceModel;
import com.devdefiance.android.query.R;

/**
 * Created by darth on 3/15/18.
 */

public class SourcesAdapter extends RecyclerView.Adapter<SourcesAdapter.ViewHolder> {

    // Object Array to hold all models;
    public SourceModel[] models;

    public SourcesAdapter(Context context, SourceModel[] models) {
        this.models = models;
    }

    @Override
    public void onBindViewHolder(final SourcesAdapter.ViewHolder holder, int position) {

        // Get the model by position
        SourceModel model = models[position];

        // Push the model values to the views
        holder.sourceName.setText(model.getSourceName());
        holder.sourceImage.setImageResource(model.getSourceImage());
        holder.sourceSwitch.setChecked(model.getSelected());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // card has been clicked, toggle switch
                holder.sourceSwitch.setChecked(!holder.sourceSwitch.isChecked());
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.frag_scs_recycler_model, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return models.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View view;
        TextView sourceName;
        ImageView sourceImage;
        SwitchCompat sourceSwitch;
        CardView cardView;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            this.cardView = view.findViewById(R.id.frag_scs_recycler_model_card);
            this.sourceImage = view.findViewById(R.id.frag_scs_recycler_model_image);
            this.sourceName = view.findViewById(R.id.frag_scs_recycler_model_text);
            this.sourceSwitch = view.findViewById(R.id.frag_scs_recycler_model_switch);
        }
    }
}