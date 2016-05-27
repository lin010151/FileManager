package cn.edu.gdei.filemanager.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.edu.gdei.filemanager.Dummy.AnnouncementItem;
import cn.edu.gdei.filemanager.R;

public class ListAdapter extends BaseAdapter {
    private Context context;
    private List<AnnouncementItem> items;

    public ListAdapter(Context context, List<AnnouncementItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return R.layout.item_announcement;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        View view = View.inflate(context, R.layout.item_announcement, null);
        AnnouncementItem item = items.get(position);
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView hint = (TextView) view.findViewById(R.id.hint);
        TextView time = (TextView) view.findViewById(R.id.time);
        title.setText(item.getTitle());
        hint.setText(item.getHint());
        time.setText(item.getTime());
        return view;
    }
}
