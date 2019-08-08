package com.example.dragview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorInput;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.LoopingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class Slideup extends AppCompatActivity {

    Intent intent;

    ImageView iv_thumbnail;
    TextView tv_singer, tv_song;

    private String Singer,Song;
    private int Thumbnail;

    SimpleExoPlayerView exoPlayerView;
    SimpleExoPlayer exoPlayer;

    String urlVideo = "https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8";
    String videoURL = "http://blueappsoftware.in/layout_design_android_blog.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideup);

        intent  = getIntent();

        iv_thumbnail = findViewById(R.id.iv_thumbnail);
        tv_song      = findViewById(R.id.tv_song);
        tv_singer    = findViewById(R.id.tv_singer);
        exoPlayerView = findViewById(R.id.exo_player);


        Singer    = intent.getStringExtra("title");
        Song      = intent.getStringExtra("genre");
        Thumbnail = intent.getIntExtra("thumbnail",0);

        tv_song.setText(Singer);
        tv_singer.setText(Song);
        iv_thumbnail.setImageResource(Thumbnail);

        try {
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
            Uri videoURI = Uri.parse(videoURL);
            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer-codelab");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(videoURI, dataSourceFactory, extractorsFactory, null, null);
            exoPlayerView.setPlayer(exoPlayer);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);

        }catch (Exception e){
            Log.e("MainAcvtivity"," exoplayer error "+ e.toString());
        }

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
