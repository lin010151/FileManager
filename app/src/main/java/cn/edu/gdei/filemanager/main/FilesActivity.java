package cn.edu.gdei.filemanager.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdei.filemanager.R;
import cn.edu.gdei.filemanager.adapter.FileListAdapter;
import cn.edu.gdei.filemanager.item.FileCategory;
import cn.edu.gdei.filemanager.item.FileItem;

public class FilesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ExpandableRecyclerAdapter.ExpandCollapseListener {

    private FileListAdapter adapter;
    private LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab_new);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FilesActivity.this, NewFileActivity.class));
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_files);
        navigationView.getMenu().getItem(2).setChecked(true);
        navigationView.setNavigationItemSelectedListener(this);

        // TODO: 2016/6/2 获取档案列表
        String[] fileTitle = getResources().getStringArray(R.array.file_title);
        String[] fileHint = getResources().getStringArray(R.array.file_hint);
        String[] fileAuthor = getResources().getStringArray(R.array.file_author);
        String[] fileTime = getResources().getStringArray(R.array.file_time);
        List<FileItem> items = new ArrayList<>();
        for (int i = 0; i < fileTitle.length; i++) {
            items.add(new FileItem(fileTitle[i], fileHint[i], fileAuthor[i], fileTime[i]));
        }
        List<FileCategory> categories = new ArrayList<>();
        categories.add(new FileCategory(items, getString(R.string.example_category_pass)));
        categories.add(new FileCategory(items, getString(R.string.example_category_auditing)));
        categories.add(new FileCategory(items, getString(R.string.example_category_pending)));

        // TODO: 2016/6/2 初次显示数据
        adapter = new FileListAdapter(this, categories);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_files);
        recyclerView.setLayoutManager(layoutManager);
        adapter.setExpandCollapseListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0)
                    fab.hide();
                else if (dy < 0)
                    fab.show();
            }
        });
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
        getMenuInflater().inflate(R.menu.files, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_files_refresh) {
            // TODO: 2016/6/2 刷新
            return true;
        } else if (id == R.id.action_files_search) {
            // TODO: 2016/5/26 搜索
            return true;
        } else if (id == R.id.action_files_sort) {
            // TODO: 2016/6/2 排序
            String[] filterType = getResources().getStringArray(R.array.file_sort_type);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.alert_dialog_file_sort_title));
            builder.setSingleChoiceItems(filterType, -1, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                }
            });
            builder.setPositiveButton(getString(R.string.alert_dialog_file_sort_positive), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                }
            });
            builder.setNegativeButton(getString(R.string.alert_dialog_file_sort_negative), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
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
            startActivity(new Intent(FilesActivity.this, AnnouncementActivity.class));
            finish();
            overridePendingTransition(0, 0);
        } else if (id == R.id.nav_files) {
            onBackPressed();
        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(FilesActivity.this, SettingsActivity.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListItemExpanded(int position) {
        fab.show();
    }

    @Override
    public void onListItemCollapsed(int position) {
        fab.show();
    }
}
