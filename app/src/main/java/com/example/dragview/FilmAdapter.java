package com.example.dragview;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder> {

    private List<ModelFilm> mylist;
    private Context context;
    private OnClickListener mlistener;

    public interface OnClickListener{
        void OnItemClick(int positon);
    }

    public void setOnClickListener(OnClickListener listener){
        mlistener = listener;
    }


    public FilmAdapter(Context context, List<ModelFilm> mylist) {
        this.mylist = mylist;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.card_view_item, viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        viewHolder.tv_title.setText(mylist.get(i).getTitle());
        viewHolder.tv_genre.setText(mylist.get(i).getGenre());
        viewHolder.iv_thubnail.setImageResource(mylist.get(i).getThubnail());

//        final ModelFilm modelFilm = mylist.get(i);

//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(viewHolder.itemView.getContext(), Slideup.class);
//                intent.putExtra("title",mylist.get(i).getTitle());
//                intent.putExtra("genre",mylist.get(i).getGenre());
//                intent.putExtra("thumbnail",mylist.get(i).getThubnail());
//                ((MainActivity)context).startActivityForResult(intent,1);
////                viewHolder.itemView.getContext().startActivity(intent);
//
//
//                Log.d("PASSING",mylist.get(i).getGenre());
//                Log.d("PASSING",mylist.get(i).getTitle());
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title, tv_genre;
        private ImageView iv_thubnail;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_thubnail = itemView.findViewById(R.id.iv_thumbnail);
            tv_title    = itemView.findViewById(R.id.tv_title);
            tv_genre    = itemView.findViewById(R.id.tv_genre);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mlistener != null){
                        int position = getAdapterPosition();
                        if (position!= RecyclerView.NO_POSITION){
                            mlistener.OnItemClick(position);
                        }
                    }
                }
            });

        }

    }
}
