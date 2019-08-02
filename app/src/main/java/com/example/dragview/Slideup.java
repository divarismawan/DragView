package com.example.dragview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Slideup extends AppCompatActivity {

    Intent intent;

    ImageView iv_thumbnail;
    TextView tv_singer, tv_song;

    private String Singer,Song;
    private int Thumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideup);

        intent  = getIntent();

        iv_thumbnail = findViewById(R.id.iv_thumbnail);
        tv_song      = findViewById(R.id.tv_song);
        tv_singer    = findViewById(R.id.tv_singer);


        Singer    = intent.getStringExtra("title");
        Song      = intent.getStringExtra("genre");
        Thumbnail = intent.getIntExtra("thumbnail",0);

        tv_song.setText(Singer);
        tv_singer.setText(Song);
        iv_thumbnail.setImageResource(Thumbnail);

    }

    @Override
    public void onBackPressed() {
        intent = new Intent();
        Log.d("MINIMIZE","Singer :"+Singer+" Song :"+Song);
        intent.putExtra("title",Singer);
        intent.putExtra("genre",Song);
        intent.putExtra("thumbnail",Thumbnail);
        intent.putExtra("status", true);
        setResult(RESULT_OK,intent);
        Log.d("MINIMIZE","Terkirim");
        finish();
    }
}
