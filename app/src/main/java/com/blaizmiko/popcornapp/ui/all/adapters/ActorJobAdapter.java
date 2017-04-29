package com.blaizmiko.popcornapp.ui.all.adapters;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.blaizmiko.popcornapp.R;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ActorJobAdapter extends BaseAdapter<ActorJobAdapter.ViewHolder>{

    private Context context;
    private List<ActorJobAdapter.JobGroupItem> items;

    public ActorJobAdapter(final Context context) {
        this.context = context;
        items = new ArrayList<>();
    }

    @Override
    public ActorJobAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_actor_job_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.jobTitleTextView.setText(items.get(position).getJobGroupName());

        final GridLayoutManager linearLayoutManager = new GridLayoutManager(context, 3);
        final ActorJobCinemasAdapter cinemasAdapter = new ActorJobCinemasAdapter(context);
        cinemasAdapter.setItemClickListener(itemClickListener);
        holder.actorRecyclerView.setLayoutManager(linearLayoutManager);
        holder.actorRecyclerView.setNestedScrollingEnabled(false);
        holder.actorRecyclerView.setAdapter(cinemasAdapter);
        cinemasAdapter.update(items.get(position).getCinemaItems());
    }

    public void add(final ActorJobAdapter.JobGroupItem newItems ) {
        items.add(newItems);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_view_adapter_actor_job_cinemas_item_title)
        protected TextView jobTitleTextView;
        @BindView(R.id.recycler_view_adapter_actor_job_cinemas_item_cinemas)
        protected RecyclerView actorRecyclerView;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class JobGroupItem {
        private String jobGroupName;
        private List<ActorJobCinemasAdapter.CinemaItem> cinemaItems;

        public JobGroupItem(final List<ActorJobCinemasAdapter.CinemaItem> cinemaItems, final String jobGroupName) {
            this.cinemaItems = cinemaItems;
            this.jobGroupName = jobGroupName;
        }

        public List<ActorJobCinemasAdapter.CinemaItem> getCinemaItems() {
            return cinemaItems;
        }

        public String getJobGroupName() {
            return jobGroupName;
        }
    }
}