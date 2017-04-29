package com.blaizmiko.popcornapp.ui.actors;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.models.actors.popular.PopularActorModel;
import com.blaizmiko.popcornapp.ui.all.adapters.BaseAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

class PopularActorsAdapter extends BaseAdapter<PopularActorsAdapter.ViewHolder> {

    private final Context context;
    private final List<PopularActorModel> items;

    PopularActorsAdapter(final Context context) {
        this.context = context;
        items = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_popular_actor_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Glide.with(context)
                .load(Constants.MovieDbApi.BASE_PROFILE_IMAGE_URL + items.get(position).getProfileImageUrl())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.profileAvatarImageView);

        holder.nameTextView.setText(items.get(position).getName());
        holder.titlesTextView.setText(items.get(position).getKnowMoviesTitlesAsString());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.image_view_adapter_popular_actor_item_profile_avatar)
        CircleImageView profileAvatarImageView;
        @BindView(R.id.text_view_adapter_popular_actor_item_name)
        TextView nameTextView;
        @BindView(R.id.text_view_adapter_popular_actor_item_titles)
        TextView titlesTextView;

        ViewHolder(final View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(final View view) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(view, getAdapterPosition(), PopularActorsAdapter.this);
            }
        }
    }

    //Public methods
    public void update(final Collection<PopularActorModel> langList) {
        items.clear();
        items.addAll(langList);
        notifyDataSetChanged();
    }

    public PopularActorModel getItemByPosition(final int position) {
        return items.get(position);
    }
}
