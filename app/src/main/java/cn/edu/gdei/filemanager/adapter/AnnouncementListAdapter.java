package cn.edu.gdei.filemanager.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdei.filemanager.R;
import cn.edu.gdei.filemanager.item.AnnouncementItem;
import cn.edu.gdei.filemanager.main.AnnouncementContentActivity;

public class AnnouncementListAdapter extends RecyclerView.Adapter<AnnouncementListAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<AnnouncementItem> list;

    public AnnouncementListAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_announcement, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(list.get(position));
        final String title = list.get(position).getTitle();
        final String hint = list.get(position).getHint();
        final String time = list.get(position).getTime();
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AnnouncementContentActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("hint", hint);
                intent.putExtra("time", time);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void addItems(List<AnnouncementItem> items) {
        int positionStart = list.size();
        list.addAll(items);
        notifyItemRangeInserted(positionStart, items.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView hint;
        private final TextView time;
        private final RelativeLayout layout;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_announcement);
            hint = (TextView) itemView.findViewById(R.id.hint_announcement);
            time = (TextView) itemView.findViewById(R.id.time_announcement);
            layout = (RelativeLayout) itemView.findViewById(R.id.item_announcement);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    context.startActivity(new Intent(context, AnnouncementContentActivity.class));
//                }
//            });
        }

        public void bind(AnnouncementItem item) {
            title.setText(item.getTitle());
            hint.setText(item.getHint());
            time.setText(item.getTime());
        }
    }
}
