package cn.edu.gdei.filemanager.main;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdei.filemanager.R;
import cn.edu.gdei.filemanager.adapter.AnnouncementListAdapter;
import cn.edu.gdei.filemanager.item.AnnouncementItem;

public class AnnouncementActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private AnnouncementListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_announcement);
        navigationView.getMenu().getItem(1).setChecked(true);
        navigationView.setNavigationItemSelectedListener(this);

        String[] announcementTitle = getResources().getStringArray(R.array.announcement_title);
        String[] announcementHint = getResources().getStringArray(R.array.announcement_hint);
        String[] announcementTime = getResources().getStringArray(R.array.announcement_time);
        List<AnnouncementItem> items = new ArrayList<>();
        for (int i = 0; i < announcementTitle.length; i++) {
            items.add(new AnnouncementItem(announcementTitle[i], announcementHint[i], announcementTime[i], 0));
        }
        for (int i = 0; i < announcementTitle.length; i++) {
            items.add(new AnnouncementItem(announcementTitle[i], announcementHint[i], announcementTime[i], 0));
        }
        for (int i = 0; i < announcementTitle.length; i++) {
            items.add(new AnnouncementItem(announcementTitle[i], announcementHint[i], announcementTime[i], 0));
        }

        adapter = new AnnouncementListAdapter(this);
        adapter.addItems(items);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_announcement);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            overridePendingTransition(0, 0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.announcement, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_announcement_search).getActionView();
        SearchableInfo info = searchManager.getSearchableInfo(getComponentName());
        searchView.setSearchableInfo(info);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_announcement_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            finish();
            overridePendingTransition(0, 0);
        } else if (id == R.id.nav_announcement) {
            onBackPressed();
        } else if (id == R.id.nav_files) {
            startActivity(new Intent(AnnouncementActivity.this, FilesActivity.class));
            finish();
            overridePendingTransition(0, 0);
        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(AnnouncementActivity.this, SettingsActivity.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
