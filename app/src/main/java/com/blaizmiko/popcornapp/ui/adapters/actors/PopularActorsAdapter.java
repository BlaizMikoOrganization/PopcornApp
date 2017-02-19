package com.blaizmiko.popcornapp.ui.adapters.actors;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.models.actors.BaseActor;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class PopularActorsAdapter extends RecyclerView.Adapter<PopularActorsAdapter.ViewHolder> {

    private final Context mContext;
    private final List<BaseActor> mItems;

    public PopularActorsAdapter(final Context context) {
        mContext = context;
        mItems = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_popular_actor_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Glide.with(mContext)
                .load(Constants.Api.BaseProfileImageUrl + mItems.get(position).getProfileImageUrl())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.profileImageView);

        holder.nameTextView.setText(mItems.get(position).getName());
        holder.titlesTextView.setText(mItems.get(position).getKnowMoviesTitlesAsString());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.adapter_popular_actor_item_root_view)
        ViewGroup rootView;

        @BindView(R.id.adapter_popular_actor_item_avatar_image_view)
        CircleImageView profileImageView;

        @BindView(R.id.adapter_popular_actor_item_name_text_view)
        TextView nameTextView;

        @BindView(R.id.adapter_popular_actor_item_titles_text_view)
        TextView titlesTextView;

        ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(final View view) {

        }
    }

    //Public methods
    public void update(final Collection<BaseActor> langList) {
        mItems.clear();
        mItems.addAll(langList);
        notifyDataSetChanged();
    }
}
