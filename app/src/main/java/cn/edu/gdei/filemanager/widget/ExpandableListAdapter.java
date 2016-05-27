package cn.edu.gdei.filemanager.widget;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import cn.edu.gdei.filemanager.R;
import cn.edu.gdei.filemanager.item.FileItem;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context = null;
    private Map<String, List<FileItem>> files;
    private List<String> groups = new ArrayList<>();

    public ExpandableListAdapter(Context context, Map<String, List<FileItem>> files) {
        this.context = context;
        this.files = files;
        for (Map.Entry<String, List<FileItem>> entry : files.entrySet()) {
            groups.add(entry.getKey());
        }
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String parent = groups.get(groupPosition);
        return files.get(parent).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_file, parent, false);
            holder = new ChildViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.hint = (TextView) convertView.findViewById(R.id.hint);
            holder.author = (TextView) convertView.findViewById(R.id.author);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        }
        holder = (ChildViewHolder) convertView.getTag();
        FileItem currentItem = (FileItem) getChild(groupPosition, childPosition);
        holder.title.setText(currentItem.getTitle());
        holder.hint.setText(currentItem.getHint());
        holder.author.setText(currentItem.getAuthor());
        holder.time.setText(currentItem.getTime());
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String parent = groups.get(groupPosition);
        return files.get(parent).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ParentViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.group_files, parent, false);
            holder = new ParentViewHolder();
            holder.category = (TextView) convertView.findViewById(R.id.category);
            convertView.setTag(holder);
        }
        holder = (ParentViewHolder) convertView.getTag();
        holder.category.setText((String) getGroup(groupPosition));
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ChildViewHolder {
        TextView title;
        TextView hint;
        TextView author;
        TextView time;
    }

    class ParentViewHolder {
        TextView category;
    }

}