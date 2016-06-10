package cn.edu.gdei.filemanager.main;

import android.app.SearchManager;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import cn.edu.gdei.filemanager.R;

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
        int quantity = 0;
        showHint(query, quantity);
    }

    private void showHint(String query, int quantity) {
        String message;
        if (quantity == 0) {
            message = getString(R.string.no_results, new Object[] {query});
        } else {
            message = getResources().getQuantityString(R.plurals.search_results, quantity, new Object[] {quantity, query});
        }
        final Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinatorLayout), message, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(getString(R.string.search_hint_dismiss), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }
}
