package cn.edu.gdei.filemanager.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdei.filemanager.R;
import cn.edu.gdei.filemanager.adapter.HomeRecyclerViewAdapter;
import cn.edu.gdei.filemanager.item.AnnouncementItem;
import cn.edu.gdei.filemanager.item.FileSummary;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private MenuItem refreshItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_main);
        navigationView.setNavigationItemSelectedListener(this);

        int mDataSetTypes[] = {HomeRecyclerViewAdapter.ANNOUNCEMENT, HomeRecyclerViewAdapter.FILE_SUMMARY};
        FileSummary summary = new FileSummary(5, 6, 7);
        List<AnnouncementItem> list = new ArrayList<>();
        list.add(new AnnouncementItem("关于2016年端午节放假期间调停课的通知", "根据上级通知安排，2016年端午节期间6月9日至6月11日放假，共3天。其中 6月9日（星期四）为国家法定节假日，停课一天（包括公选课）；6月12日（星期日）补6月10日（星期五）的课。", "2016年5月31日", 0));
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_main);
        HomeRecyclerViewAdapter adapter = new HomeRecyclerViewAdapter(mDataSetTypes, list, summary, this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_main);
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_main_search) {
            // TODO: 2016/5/26 搜索活动
            return true;
        } else if (id == R.id.action_main_refresh) {
            refresh(item);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            onBackPressed();
        } else if (id == R.id.nav_announcement) {
            startActivity(new Intent(MainActivity.this, AnnouncementActivity.class));
            overridePendingTransition(0, 0);
        } else if (id == R.id.nav_files) {
            startActivity(new Intent(MainActivity.this, FilesActivity.class));
            overridePendingTransition(0, 0);
        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void refresh(MenuItem refreshItem) {
        this.refreshItem = refreshItem;
        completeRefresh(refreshItem);
        ImageView iv = (ImageView) getLayoutInflater().inflate(R.layout.refresh_action_view, null);
        refreshItem.setActionView(iv);
        Animation rotation = AnimationUtils.loadAnimation(this, R.anim.clockwise_refresh);
        rotation.setRepeatMode(Animation.RESTART);
        rotation.setRepeatCount(Animation.INFINITE);
        iv.startAnimation(rotation);
        refreshItem.setActionView(iv);
        // TODO: 2016/5/26 刷新内容
    }

    public void completeRefresh(MenuItem refreshItem) {
        if (refreshItem != null) {
            View view = refreshItem.getActionView();
            if (view != null) {
                view.clearAnimation();
                refreshItem.setActionView(null);
            }
        }
    }
}
