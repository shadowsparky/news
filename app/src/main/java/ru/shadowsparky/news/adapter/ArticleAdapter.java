package ru.shadowsparky.news.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ru.shadowsparky.news.R;
import ru.shadowsparky.news.pojo.event_view.EventArticle;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.MainViewHolder> {
    List<EventArticle> articles;

    public ArticleAdapter(List<EventArticle> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_article_item, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        EventArticle event = articles.get(position);
        if (event.getHeader().equals(""))  {
            holder.title.setVisibility(View.GONE);
        } else {
            holder.title.setVisibility(View.VISIBLE);
            holder.title.setText(event.getHeader());
        }
        holder.text.setText(event.getText());
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView text;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.article_item_title);
            text = itemView.findViewById(R.id.article_item_text);
        }
    }
}
