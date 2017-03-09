package com.blaizmiko.popcornapp.ui.adapters.moviedetails;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.utils.SymbolUtils;
import com.blaizmiko.popcornapp.models.movies.Cast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHolder>{

    private List<Cast> mCast;
    private Context mContext;

    public CastAdapter(Context context) {
        mContext = context;
        mCast = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movie_details_cast_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(Constants.TheMovieDbApi.BaseLowResImageUrl + mCast.get(position).getProfilePath())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.avatarImageView);

        holder.nameTextView.setText(mCast.get(position).getName().replaceAll(SymbolUtils.SPACE, SymbolUtils.NEXT_LINE));
    }

    @Override
    public int getItemCount() {
        return mCast.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.adapter_movie_details_cast_avatar_image_view)
        CircleImageView avatarImageView;
        @BindView(R.id.adapter_movie_details_cast_name_text_view)
        TextView nameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //Public methods
    public void update(List<Cast> casts) {
        mCast.clear();
        mCast.addAll(casts);
        notifyDataSetChanged();
    }
}
