package com.example.dragview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

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

    public static void putDataSinger(Context context, String song, String singer, int thumbnail){
        Intent intent = new Intent(context,Slideup.class);
        intent.putExtra("title",song);
        intent.putExtra("genre",singer);
        intent.putExtra("thumbnail",thumbnail);
        context.startActivity(intent);
    }

    @Override
    public void onBackPressed() {



        intent = new Intent();
        MainActivity.setUpMinimize(true,Singer,Song,Thumbnail);
        Log.d("MINIMIZE","Singer :"+Singer+" Song :"+Song);

        intent.putExtra("title",Singer);
        intent.putExtra("genre",Song);
        intent.putExtra("thumbnail",Thumbnail);
        intent.putExtra("status", true);

        setResult(RESULT_OK,intent);
        finish();
    }
}
