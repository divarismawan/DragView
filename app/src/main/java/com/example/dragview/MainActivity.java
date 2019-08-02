package com.example.dragview;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FilmAdapter.OnClickListener {

    LinearLayout layout_minimize;
    ImageView iv_close;
    ImageView iv_playpause;

    ImageView iv_thumbnail;
    TextView tv_song, tv_singer;

    RecyclerView recyclerView;
    FilmAdapter filmAdapter;

    List<ModelFilm> arrayList;

    Boolean status_playpause = true;

    Intent intent;

    String getSinger, getSong;
    int getThumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(MainActivity.this, Slideup.class);



        recyclerView    = findViewById(R.id.my_recycler_view);

        layout_minimize = findViewById(R.id.layout_minimize);
        iv_close        = findViewById(R.id.iv_close);
        iv_playpause    = findViewById(R.id.iv_pauseplay);

        tv_song         = findViewById(R.id.tv_song);
        tv_singer       = findViewById(R.id.tv_singer);
        iv_thumbnail    = findViewById(R.id.iv_thumbnail);

        iv_playpause.setBackgroundResource(R.drawable.ic_play);


        layout_minimize.setVisibility(View.GONE);

        arrayList = new ArrayList<>();
        arrayList.add(new ModelFilm("Pride and Prejudice","Fantasy, Horror",R.drawable.satu));
        arrayList.add(new ModelFilm("Runner","Sci-Fi, Drama",R.drawable.dua));
        arrayList.add(new ModelFilm("2012","Fantacy, Drama,Comedy",R.drawable.tiga));
        arrayList.add(new ModelFilm("KidulHood","Historical, Horror",R.drawable.empat));
        arrayList.add(new ModelFilm("Jaw","Harem, Horror",R.drawable.lima));
        arrayList.add(new ModelFilm("The Conjuring","Fantacy, Horror",R.drawable.enam));

        filmAdapter = new FilmAdapter(MainActivity.this,arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(filmAdapter);

        filmAdapter.setOnClickListener(MainActivity.this);



    }

    public void onClickClose(View view){
        layout_minimize.setVisibility(View.GONE);
    }

    public void onClickPausePlay(View view){
        if (status_playpause){
            iv_playpause.setBackgroundResource(R.drawable.ic_play);
            status_playpause = false;
        }else {
            iv_playpause.setBackgroundResource(R.drawable.ic_pause);
            status_playpause = true;
        }
    }

    public void onClickMinimize(View view){
        Intent intent = new Intent(this,Slideup.class);

        intent.putExtra("thumbnail",getThumbnail);
        intent.putExtra("title",getSong);
        intent.putExtra("genre",getSinger);

        startActivityForResult(intent,1);
    }

    @Override
    public void OnItemClick(int positon) {
        Intent intent = new Intent(this,Slideup.class);
        ModelFilm modelFilm = arrayList.get(positon);

        getThumbnail = modelFilm.getThubnail();
        getSinger    = modelFilm.getGenre();
        getSong      = modelFilm.getTitle();

        intent.putExtra("thumbnail",getThumbnail);
        intent.putExtra("title",getSong);
        intent.putExtra("genre",getSinger);

        startActivityForResult(intent,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK){
            Log.d("MINIMIZE","BACKPRESS SUKSES");
            boolean status = data.getBooleanExtra("status", false);
            Log.d("MINIMIZE","Status :"+status);
            String Singer, Song;
            int Thumbnail;
            if (status) {
                Log.d("MINIMIZE","Data terkirim");
                Toast.makeText(this, "Tampil", Toast.LENGTH_SHORT).show();
                layout_minimize.setVisibility(View.VISIBLE);
                Singer = data.getStringExtra("title");
                Song = data.getStringExtra("genre");
                Thumbnail = data.getIntExtra("thumbnail", 0);

                tv_song.setText(Singer);
                tv_singer.setText(Song);
                iv_thumbnail.setImageResource(Thumbnail);
            } else {
                layout_minimize.setVisibility(View.GONE);
                Log.d("MINIMIZE","Data gagal terkirim");
            }
        }else {
            Log.d("MINIMIZE","BACKPRESS GAGAL");
        }
    }
}
