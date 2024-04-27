package com.example.utszaki.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.utszaki.R;
import com.example.utszaki.response.UserGithub;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GithubAdaptor extends RecyclerView.Adapter<GithubAdaptor.ViewHolder>{

    private List<UserGithub> users;

    public GithubAdaptor(List<UserGithub> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserGithub user = users.get(position);
        holder.usernameTextView.setText(user.getUsername());
        Picasso.get().load(user.getAvatarUrl()).into(holder.avatarImageView);

        holder.itemView.setOnClickListener(click -> {
            Intent intent = new Intent(click.getContext(), Detail.class);
            intent.putExtra("nama", user.getName());
            intent.putExtra("username", user.getUsername());
            intent.putExtra("bio", user.getBio());
            intent.putExtra("gambar", user.getAvatarUrl());
            intent.putExtra("location", user.getLocation());
            intent.putExtra("email", user.getEmail());
            intent.putExtra("followers", user.getfollowers());
            intent.putExtra("following", user.getfollowing());

            click.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatarImageView;
        TextView usernameTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarImageView = itemView.findViewById(R.id.ivGambar);
            usernameTextView = itemView.findViewById(R.id.nama_pengguna);
        }
    }
}
