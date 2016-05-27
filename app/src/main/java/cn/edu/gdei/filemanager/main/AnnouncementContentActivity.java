package cn.edu.gdei.filemanager.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.edu.gdei.filemanager.R;

public class AnnouncementContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_content);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
