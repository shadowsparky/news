package ru.shadowsparky.news.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import ru.shadowsparky.news.R;
import ru.shadowsparky.news.callbacks.OnCardClicked;
import ru.shadowsparky.news.pojo.category.CategoryEvents;
import ru.shadowsparky.news.pojo.category.CategoryResponse;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MainViewHolder> {
    List<CategoryResponse> events;
    OnCardClicked callback;

    public NewsAdapter(CategoryEvents events, OnCardClicked callback) {
        this.events = events.getEvents();
        this.callback = callback;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        CategoryResponse item = events.get(position);
        holder.card.setOnClickListener(view -> callback.cardClicked(item));
        holder.title.setText(item.getTitle());
        holder.preview.setText(item.getPreview());
        holder.coefficient.setText(item.getCoefficient());
        holder.place.setText(item.getPlace());
        holder.time.setText(item.getTime());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView preview;
        private TextView coefficient;
        private TextView time;
        private TextView place;
        private CardView card;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            preview = itemView.findViewById(R.id.item_preview);
            coefficient = itemView.findViewById(R.id.item_coefficient);
            time = itemView.findViewById(R.id.item_time);
            place = itemView.findViewById(R.id.item_place);
            card = itemView.findViewById(R.id.item_card);
        }
    }
}
