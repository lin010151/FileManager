package cn.edu.gdei.filemanager.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

import java.util.List;

import cn.edu.gdei.filemanager.R;
import cn.edu.gdei.filemanager.item.FileCategory;
import cn.edu.gdei.filemanager.item.FileItem;
import cn.edu.gdei.filemanager.main.AnnouncementContentActivity;
import cn.edu.gdei.filemanager.main.FileContentActivity;

public class FileListAdapter extends ExpandableRecyclerAdapter<FileListAdapter.FileCategoryViewHolder, FileListAdapter.FileViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;

    public FileListAdapter(Context context, @NonNull List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public FileCategoryViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View fileCategoryView = layoutInflater.inflate(R.layout.group_files, parentViewGroup, false);
        return new FileCategoryViewHolder(fileCategoryView);
    }

    @Override
    public FileViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View fileView = layoutInflater.inflate(R.layout.item_files, childViewGroup, false);
        return new FileViewHolder(fileView);
    }

    @Override
    public void onBindParentViewHolder(FileCategoryViewHolder parentViewHolder, int position, ParentListItem parentListItem) {
        FileCategory fileCategory = (FileCategory) parentListItem;
        parentViewHolder.bind(fileCategory);
    }

    @Override
    public void onBindChildViewHolder(FileViewHolder childViewHolder, int position, Object childListItem) {
        FileItem fileItem = (FileItem) childListItem;
        childViewHolder.bind(fileItem);
    }

    public class FileCategoryViewHolder extends ParentViewHolder {

        private static final float INITIAL_POSITION = 0.0f;
        private static final float ROTATED_POSITION = 180f;

        private TextView category;
        private final ImageView indicator;

        public FileCategoryViewHolder(View itemView) {
            super(itemView);
            category = (TextView) itemView.findViewById(R.id.category_file);
            indicator = (ImageView) itemView.findViewById(R.id.indicator);
        }

        public void bind(FileCategory category) {
            this.category.setText(category.getCategory());
        }

        @SuppressLint("NewApi")
        @Override
        public void setExpanded(boolean expanded) {
            super.setExpanded(expanded);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                if (expanded) {
                    indicator.setRotation(ROTATED_POSITION);
                } else {
                    indicator.setRotation(INITIAL_POSITION);
                }
            }
        }

        @Override
        public void onExpansionToggled(boolean expanded) {
            super.onExpansionToggled(expanded);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                RotateAnimation rotateAnimation;
                if (expanded) {
                    rotateAnimation = new RotateAnimation(ROTATED_POSITION,
                            INITIAL_POSITION,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                } else {
                    rotateAnimation = new RotateAnimation(-1 * ROTATED_POSITION,
                            INITIAL_POSITION,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                }

                rotateAnimation.setDuration(200);
                rotateAnimation.setFillAfter(true);
                indicator.startAnimation(rotateAnimation);
            }
        }
    }

    public class FileViewHolder extends ChildViewHolder {

        private TextView title;
        private TextView hint;
        private TextView author;
        private TextView time;

        public FileViewHolder(View itemView) {
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
