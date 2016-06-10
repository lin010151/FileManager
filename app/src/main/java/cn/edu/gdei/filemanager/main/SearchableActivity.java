package cn.edu.gdei.filemanager.main;

import android.app.SearchManager;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdei.filemanager.R;
import cn.edu.gdei.filemanager.adapter.AnnouncementListAdapter;
import cn.edu.gdei.filemanager.adapter.ResultListAdapter;
import cn.edu.gdei.filemanager.item.AnnouncementItem;
import cn.edu.gdei.filemanager.item.FileItem;

public class SearchableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doSearch(query);

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void doSearch(String query) {
        // TODO: 2016/6/10 根据query搜索
        String[] fileTitle = getResources().getStringArray(R.array.file_title);
        String[] fileHint = getResources().getStringArray(R.array.file_hint);
        String[] fileAuthor = getResources().getStringArray(R.array.file_author);
        String[] fileTime = getResources().getStringArray(R.array.file_time);
        List<FileItem> data = new ArrayList<>();
        for (int i = 0; i < fileTitle.length; i++) {
            data.add(new FileItem(fileTitle[i], fileHint[i], fileAuthor[i], fileTime[i]));
        }
        List<FileItem> items = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getTitle().contains(query) || data.get(i).getHint().contains(query)
                    || data.get(i).getTime().contains(query) || data.get(i).getAuthor().contains
                    (query)) {
                items.add(data.get(i));
            }
        }
        ResultListAdapter adapter = new ResultListAdapter(this);
        adapter.addItems(items);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_result);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));
        recyclerView.setAdapter(adapter);

        int quantity = items.size();
        showHint(query, quantity);
    }

    private void showHint(String query, int quantity) {
        String message;
        if (quantity == 0) {
            message = getString(R.string.no_results, new Object[]{query});
        } else {
            message = getResources().getQuantityString(R.plurals.search_results, quantity, new
                    Object[]{quantity, query});
        }
        final Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinatorLayout), message,
                Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(getString(R.string.search_hint_dismiss), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }
}
