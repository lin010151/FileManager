package cn.edu.gdei.filemanager.adapter;

import android.content.Context;
import android.content.Intent;
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
import cn.edu.gdei.filemanager.item.AnnouncementItem;
import cn.edu.gdei.filemanager.item.FileSummary;
import cn.edu.gdei.filemanager.main.AnnouncementActivity;
import cn.edu.gdei.filemanager.main.AnnouncementContentActivity;
import cn.edu.gdei.filemanager.main.FilesActivity;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> {
    public static final int ANNOUNCEMENT_WITH_IMG = 0;
    public static final int ANNOUNCEMENT = 1;
    public static final int FILE_NOTIFICATION = 2;
    public static final int FILE_SUMMARY = 3;
    public static final int DEFAULT = 4;

    private int[] mDataSetTypes;
    private List<AnnouncementItem> announcements;
    private FileSummary summary;
    private Context context;

    public HomeRecyclerViewAdapter(int[] mDataSetTypes, List<AnnouncementItem> announcements, FileSummary summary, Context context) {
        this.mDataSetTypes = mDataSetTypes;
        this.announcements = announcements;
        this.summary = summary;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v;
        if (viewType == ANNOUNCEMENT_WITH_IMG) {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_announcement_image, viewGroup, false);
            return new AnnouncementWithImgViewHolder(v);
        } else if (viewType == ANNOUNCEMENT) {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_announcement, viewGroup, false);
            return new AnnouncementViewHolder(v);
        }
//        else if (viewType == FILE_NOTIFICATION) {
//            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_file_notification, viewGroup, false);
//            return new FileNotificationViewHolder(v);
//        }
        else if (viewType == FILE_SUMMARY) {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_file_summary, viewGroup, false);
            return new FileSummaryViewHolder(v);
        } else {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_default, viewGroup, false);
            return new DefaultViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        if (viewHolder.getItemViewType() == ANNOUNCEMENT_WITH_IMG) {
            AnnouncementWithImgViewHolder holder = (AnnouncementWithImgViewHolder) viewHolder;
            holder.image.setImageResource(announcements.get(position).getImageId());
            holder.title.setText(announcements.get(position).getTitle());
            holder.hint.setText(announcements.get(position).getHint());
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, AnnouncementContentActivity.class));
                }
            });
            holder.btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, AnnouncementActivity.class));
                }
            });
        } else if (viewHolder.getItemViewType() == ANNOUNCEMENT) {
            AnnouncementViewHolder holder = (AnnouncementViewHolder) viewHolder;
            holder.title.setText(announcements.get(position).getTitle());
            holder.hint.setText(announcements.get(position).getHint());
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, AnnouncementContentActivity.class));
                }
            });
            holder.btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, AnnouncementActivity.class));
                }
            });
        }
//        else if (viewHolder.getItemViewType() == FILE_NOTIFICATION) {
//
//        }
        else if (viewHolder.getItemViewType() == FILE_SUMMARY) {
            FileSummaryViewHolder holder = (FileSummaryViewHolder) viewHolder;
            holder.pass.setText(summary.getPass());
            holder.pending.setText(summary.getPending());
            holder.auditing.setText(summary.getAuditing());
            holder.btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, FilesActivity.class));
                }
            });
        } else {
            DefaultViewHolder holder = (DefaultViewHolder) viewHolder;
            holder.btnAnnouncements.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, AnnouncementActivity.class));
                }
            });
            holder.btnFiles.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, FilesActivity.class));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return mDataSetTypes[position];
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }

    public static class AnnouncementWithImgViewHolder extends ViewHolder {
        CardView cardView;
        ImageView image;
        TextView title;
        TextView hint;
        Button btnMore;

        public AnnouncementWithImgViewHolder(final View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_announcement_img);
            image = (ImageView) itemView.findViewById(R.id.card_announcement_img_img);
            title = (TextView) itemView.findViewById(R.id.card_announcement_img_title);
            hint = (TextView) itemView.findViewById(R.id.card_announcement_img_hint);
            btnMore = (Button) itemView.findViewById(R.id.card_announcement_img_btn_more);
        }
    }

    public static class AnnouncementViewHolder extends ViewHolder {
        CardView cardView;
        TextView title;
        TextView hint;
        Button btnMore;

        public AnnouncementViewHolder(final View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_announcement);
            title = (TextView) itemView.findViewById(R.id.card_announcement_title);
            hint = (TextView) itemView.findViewById(R.id.card_announcement_hint);
            btnMore = (Button) itemView.findViewById(R.id.card_announcement_btn_more);
        }
    }

    public static class FileSummaryViewHolder extends ViewHolder {
        CardView cardView;
        TextView pass;
        TextView pending;
        TextView auditing;
        Button btnMore;

        public FileSummaryViewHolder(final View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_file_summary);
            pass = (TextView) itemView.findViewById(R.id.count_state_pass);
            pending = (TextView) itemView.findViewById(R.id.count_state_pending);
            auditing = (TextView) itemView.findViewById(R.id.count_state_auditing);
            btnMore = (Button) itemView.findViewById(R.id.card_file_summary_btn_more);
        }
    }

    public static class DefaultViewHolder extends ViewHolder {
        CardView cardView;
        Button btnAnnouncements;
        Button btnFiles;

        public DefaultViewHolder(final View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_default);
            btnAnnouncements = (Button) itemView.findViewById(R.id.card_default_btn_announcements);
            btnFiles = (Button) itemView.findViewById(R.id.card_default_btn_files);
        }
    }
}
