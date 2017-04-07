package com.blaizmiko.popcornapp.ui.all.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.models.cast.CastModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class BaseCastAdapter extends BaseAdapter<BaseCastAdapter.ViewHolder> {

    private final Context context;
    private final List<CastModel> items;

    public BaseCastAdapter(final Context context) {
        this.context = context;
        items = new ArrayList<>();
    }

    @Override
    public BaseCastAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_cast_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BaseCastAdapter.ViewHolder holder, final int position) {
        Glide.with(context)
                .load(Constants.MovieDbApi.BASE_PROFILE_IMAGE_URL + items.get(position).getProfilePath())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.avatarImageView);
        holder.nameTextView.setText(items.get(position).getName());
        holder.titlesTextView.setText(items.get(position).getCharacter());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.image_view_base_cast_avatar)
        protected CircleImageView avatarImageView;
        @BindView(R.id.text_view_base_cast_name)
        protected TextView nameTextView;
        @BindView(R.id.text_view_base_cast_titles)
        protected TextView titlesTextView;

        ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(final View view) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(view, getAdapterPosition(), BaseCastAdapter.this);
            }
        }
    }

    //Public methods
    public void update(final List<CastModel> casts) {
        items.clear();
        items.addAll(casts);
        notifyDataSetChanged();
    }
}
