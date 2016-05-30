package cn.edu.gdei.filemanager.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        private TextView category;

        public FileCategoryViewHolder(View itemView) {
            super(itemView);
            category = (TextView) itemView.findViewById(R.id.category_file);
        }

        public void bind(FileCategory category) {
            this.category.setText(category.getCategory());
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
