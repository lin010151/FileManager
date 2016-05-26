package cn.edu.gdei.filemanager.util;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by lin01 on 2016/5/25.
 */
public class FileListAdapter implements ExpandableListAdapter {
    private int padding;
    private Context context;
    private String[] armTypes = new String[]{"Catagory", "Catagory", "Catagory", "Catagory", "Catagory", "Catagory", "Catagory", "Catagory"};
    private String[][] arms = new String[][]{
            {"File", "File", "File", "File"},
            {"File", "File", "File", "File"},
            {"File", "File", "File", "File"},
            {"File", "File", "File", "File"},
            {"File", "File", "File", "File"},
            {"File", "File", "File", "File"},
            {"File", "File", "File", "File"},
            {"File", "File", "File", "File"}
    };

    public FileListAdapter(Context context) {
        this.context = context;
        this.padding = DisplayUtil.dip2px(context, 16);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return armTypes.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return arms[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return armTypes[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return arms[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        TextView textView = getTextView();
        textView.setText(getGroup(groupPosition).toString());
        textView.setPadding(padding, 0, padding, 0);
        ll.addView(textView);
        return ll;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        TextView textView = getTextView();
        textView.setText(getChild(groupPosition, childPosition).toString());
        return textView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }

    private TextView getTextView() {
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 64);
        TextView textView = new TextView(context);
        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        textView.setPadding(padding, 0, padding, 0);
        textView.setTextSize(16);
        return textView;
    }

}