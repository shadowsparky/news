package ru.shadowsparky.news.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.common.base.Strings;

import java.io.Serializable;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import ru.shadowsparky.news.R;
import ru.shadowsparky.news.Validator;
import ru.shadowsparky.news.pojo.event_view.EventArticle;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.MainViewHolder> implements Serializable {
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
        if (Validator.isNullOrBlank(event.getHeader()) && Validator.isNullOrBlank(event.getText())) {
            holder.card.setVisibility(View.GONE);
        } else {
            verifyData(holder, event);
        }
    }

    protected void verifyData(MainViewHolder holder, EventArticle event) {
        if (Validator.isNullOrBlank(event.getHeader())) {
            holder.title.setVisibility(View.GONE);
        } else {
            holder.title.setText(event.getHeader());
        }
        if (Validator.isNullOrBlank(event.getText())) {
//            holder.text.setVisibility(View.GONE);
            holder.text.setText("Данные отсутствуют");
        } else {
            holder.text.setText(event.getText());
        }
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView text;
        private CardView card;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.article_item_title);
            text = itemView.findViewById(R.id.article_item_text);
            card = itemView.findViewById(R.id.article_item_card);
        }
    }
}
