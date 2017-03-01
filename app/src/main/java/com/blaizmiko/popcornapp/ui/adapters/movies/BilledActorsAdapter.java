package com.blaizmiko.popcornapp.ui.adapters.movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.models.movies.Cast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class BilledActorsAdapter extends RecyclerView.Adapter<BilledActorsAdapter.ViewHolder>{

    private List<Cast> mCasts;
    private Context mContext;

    public BilledActorsAdapter(Context context) {
        mContext = context;
        mCasts = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_billed_actors_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(Constants.Api.BaseLowResImageUrl + mCasts.get(position).getProfilePath())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.avatarImageView);

    }

    @Override
    public int getItemCount() {
        return mCasts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.adapter_billed_actors_avatar_image_view)
        CircleImageView avatarImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //Public methods
    public void update(List<Cast> casts) {
        mCasts.clear();
        mCasts.addAll(casts);
        notifyDataSetChanged();
    }
}
