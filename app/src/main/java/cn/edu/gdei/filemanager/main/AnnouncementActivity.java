package cn.edu.gdei.filemanager.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import cn.edu.gdei.filemanager.R;

public class AnnouncementActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listView;

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

        setList();
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
        getMenuInflater().inflate(R.menu.announcement, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_announcement_search) {
            // TODO: 2016/5/26 添加搜索活动
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

    private void setList() {
        listView = (ListView) findViewById(R.id.listView_announcement);
        ArrayList<HashMap<String, String>> dataMessage = new ArrayList<HashMap<String, String>>();
        String[] titleArray = new String[]{"Announcement", "Announcement", "Announcement", "Announcement", "Announcement", "Announcement", "Announcement", "Announcement"};
        String[] subItemArray = new String[]{"1970-01-01", "1970-01-01", "1970-01-01", "1970-01-01", "1970-01-01", "1970-01-01", "1970-01-01", "1970-01-01"};
        for (int i = 0; i < titleArray.length; i++) {
            HashMap<String, String> datum = new HashMap<String, String>();
            datum.put("fileName", titleArray[i]);
            datum.put("fileTime", subItemArray[i]);
            dataMessage.add(datum);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, dataMessage, android.R.layout.simple_list_item_2, new String[]{"fileName", "fileTime"}, new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(adapter);
        // 添加ListView监听事件
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
    }
}
