package ru.tander.parsejson.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.tander.parsejson.R;
import ru.tander.parsejson.entities.News;
import ru.tander.parsejson.interfaces.OnItemClickListener;
import ru.tander.parsejson.view.CircleTransform;

/**
 * Created by savchenko on 31.08.17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {
    private List<News> newsList;
    private OnItemClickListener clickListener;

    public NewsAdapter(OnItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    public void setData(List<News>newsList){
        this.newsList = newsList;
    }

    public void addNewItems(List<News> items) {
        newsList.addAll(items);
    }


    @Override
    public NewsHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {
        News news = newsList.get(position);
        holder.title.setText(news.getTitle());
        holder.tvDate.setText(news.getDate());
        Picasso.with(clickListener
                .getContext())
                .load(news.getImageUrl())
                .transform(new CircleTransform())
                .into(holder.ivPic);
        if(position == getItemCount()-1){
            clickListener.updateData();
        }
    }


    @Override
    public int getItemCount() {
        if(newsList==null)return 0;
        return newsList.size();
    }

    class NewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        TextView tvDate;
        ImageView ivPic;

        NewsHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            tvDate = itemView.findViewById(R.id.tvDate);
            ivPic = itemView.findViewById(R.id.ivIconNews);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(getAdapterPosition());
        }
    }
}