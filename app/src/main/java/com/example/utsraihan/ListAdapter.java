package com.example.utsraihan;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.utsraihan.models.ItemsItem;
import com.example.utsraihan.ui.DetailActivity;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private final List<ItemsItem> itemList;

    public ListAdapter(List<ItemsItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.lists, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        ItemsItem items = itemList.get(position);

        holder.tvUsername.setText(items.getLogin());
        Glide.with(holder.itemView.getContext())
                .load(items.getAvatarUrl())
                .into(holder.ivFoto);

        holder.itemView.setOnClickListener( view -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra("username", items.getLogin());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvUsername;
        ImageView ivFoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivFoto = itemView.findViewById(R.id.ivFoto);
        }
    }
}
