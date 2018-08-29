package ru.shadowsparky.news.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ru.shadowsparky.news.R;
import ru.shadowsparky.news.pojo.category.CategoryEvents;
import ru.shadowsparky.news.pojo.category.CategoryResponse;

public class NewsList extends RecyclerView.Adapter<NewsList.MainViewHolder> {

    List<CategoryResponse> events;
    String event_type;

    public NewsList(CategoryEvents events, String event_type) {
        this.events = events.getEvents();
        this.event_type = event_type;
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

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            preview = itemView.findViewById(R.id.item_preview);
            coefficient = itemView.findViewById(R.id.item_coefficient);
            time = itemView.findViewById(R.id.item_time);
            place = itemView.findViewById(R.id.item_place);
        }
    }
}
