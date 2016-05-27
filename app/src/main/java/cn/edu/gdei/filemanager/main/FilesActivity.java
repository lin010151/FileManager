package cn.edu.gdei.filemanager.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.gdei.filemanager.R;
import cn.edu.gdei.filemanager.util.DisplayUtil;
import cn.edu.gdei.filemanager.item.FileItem;
import cn.edu.gdei.filemanager.widget.ExpandableListAdapter;

public class FilesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_new);
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

        // TODO: 2016/5/27 分离下面语句
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;

        Map<String, List<FileItem>> files = new HashMap<>();
        FileItem fileItem = new FileItem("Title", "Hint", "Author", "Time");
        List<FileItem> fileItems = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            fileItems.add(fileItem);
            files.put("Category " + i, fileItems);
        }

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.list_view_files);
        ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter(this, files);
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            expandableListView.setIndicatorBounds(width - DisplayUtil.dip2px(this, 50), width);
        } else {
            expandableListView.setIndicatorBoundsRelative(width - DisplayUtil.dip2px(this, 50), width);
        }
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return false;
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.files, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_files_action) {
            return true;
        } else if (id == R.id.action_files_search) {
            // TODO: 2016/5/26 添加搜索活动
            Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
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

}
