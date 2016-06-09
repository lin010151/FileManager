package cn.edu.gdei.filemanager.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import cn.edu.gdei.filemanager.R;

public class AnnouncementContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_content);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String time = intent.getStringExtra("time");
        String hint = "　　" + intent.getStringExtra("hint");

        TextView titleView = (TextView) findViewById(R.id.content_announcement_title);
        TextView timeView = (TextView) findViewById(R.id.content_announcement_time);
        TextView hintView = (TextView) findViewById(R.id.content_announcement_hint);

        titleView.setText(title);
        timeView.setText(time);
        hintView.setText(hint);
    }
}
