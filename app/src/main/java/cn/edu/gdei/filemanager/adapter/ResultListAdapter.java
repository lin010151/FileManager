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
import cn.edu.gdei.filemanager.item.FileItem;
import cn.edu.gdei.filemanager.main.AnnouncementContentActivity;
import cn.edu.gdei.filemanager.main.FileContentActivity;

public class ResultListAdapter extends RecyclerView.Adapter<ResultListAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<FileItem> list;

    public ResultListAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_files, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void addItems(List<FileItem> items) {
        int positionStart = list.size();
        list.addAll(items);
        notifyItemRangeInserted(positionStart, items.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView hint;
        private TextView author;
        private TextView time;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_file);
            hint = (TextView) itemView.findViewById(R.id.hint_file);
            author = (TextView) itemView.findViewById(R.id.author_file);
            time = (TextView) itemView.findViewById(R.id.time_file);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, FileContentActivity.class));
                }
            });
        }

        public void bind(FileItem item) {
            title.setText(item.getTitle());
            hint.setText(item.getHint());
            author.setText(item.getAuthor());
            time.setText(item.getTime());
        }
    }
}
