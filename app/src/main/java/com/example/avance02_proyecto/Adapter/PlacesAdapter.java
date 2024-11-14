package com.example.avance02_proyecto.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.avance02_proyecto.fragments.MapFragment;

import java.util.List;


public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.ViewHolder> {

    private final List<MapFragment.Place> places;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(MapFragment.Place place);
    }

    public PlacesAdapter(List<MapFragment.Place> places, OnItemClickListener listener) {
        this.places = places;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(places.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(android.R.id.text1);
        }

        public void bind(final MapFragment.Place place, final OnItemClickListener listener) {
            nameTextView.setText(place.name);
            itemView.setOnClickListener(v -> listener.onItemClick(place));
        }
    }
}
