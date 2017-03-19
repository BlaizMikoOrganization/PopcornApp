package com.blaizmiko.popcornapp.ui.all.presentation.details.cast;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.utils.SymbolUtil;
import com.blaizmiko.popcornapp.data.models.cast.Cast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHolder>{

    private List<Cast> cast;
    private Context context;

    public CastAdapter(Context context) {
        this.context = context;
        cast = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movie_details_cast_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context)
                .load(Constants.MovieDbApi.BASE_LOW_RES_IMAGE_URL + cast.get(position).getProfilePath())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.avatarImageView);

        holder.nameTextView.setText(cast.get(position).getName().replaceAll(SymbolUtil.SPACE, SymbolUtil.NEXT_LINE));
    }

    @Override
    public int getItemCount() {
        return cast.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.image_view_adapter_movie_details_cast_item_avatar)
        CircleImageView avatarImageView;
        @BindView(R.id.text_view_adapter_movie_details_cast_item_name)
        TextView nameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //Public methods
    public void update(List<Cast> casts) {
        cast.clear();
        cast.addAll(casts);
        notifyDataSetChanged();
    }
}
