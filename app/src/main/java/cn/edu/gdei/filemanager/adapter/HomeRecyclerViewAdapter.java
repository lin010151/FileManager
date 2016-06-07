package cn.edu.gdei.filemanager.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.edu.gdei.filemanager.R;
import cn.edu.gdei.filemanager.item.HomeAnnouncementItem;
import cn.edu.gdei.filemanager.main.AnnouncementActivity;
import cn.edu.gdei.filemanager.main.AnnouncementContentActivity;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.NewsViewHolder> {
    private List<HomeAnnouncementItem> list;
    private Context context;

    public HomeRecyclerViewAdapter(List<HomeAnnouncementItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView image;
        TextView title;
        TextView hint;
        Button btnMore;

        public NewsViewHolder(final View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view_announcement_normal);
            image = (ImageView) itemView.findViewById(R.id.card_announcement_img_img);
            title = (TextView) itemView.findViewById(R.id.card_announcement_img_title);
            hint = (TextView) itemView.findViewById(R.id.card_announcement_img_hint);
            btnMore = (Button) itemView.findViewById(R.id.card_announcement_btn_more);
        }
    }

    @Override
    public HomeRecyclerViewAdapter.NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_announcement_image, viewGroup, false);
        NewsViewHolder nvh = new NewsViewHolder(v);
        return nvh;
    }

    @Override
    public void onBindViewHolder(HomeRecyclerViewAdapter.NewsViewHolder personViewHolder, int i) {
        personViewHolder.image.setImageResource(list.get(i).getImageId());
        personViewHolder.title.setText(list.get(i).getTitle());
        personViewHolder.hint.setText(list.get(i).getHint());
        personViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, AnnouncementContentActivity.class));
            }
        });

        personViewHolder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, AnnouncementActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
