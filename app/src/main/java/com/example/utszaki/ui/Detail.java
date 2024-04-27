package com.example.utszaki.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.utszaki.R;
import com.example.utszaki.response.UserGithub;
import com.example.utszaki.retrofit.ApiConfig;
import com.example.utszaki.retrofit.ApiService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail extends AppCompatActivity {

    private ProgressBar progressBar;
    private String name, usernames, bio, gambar, location, email, followers, following;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        progressBar = findViewById(R.id.progressBar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String username = extras.getString("username");
            ApiService apiService = ApiConfig.getApiService();
            Call<UserGithub> userCall = apiService.getUser(username);

            TextView textView = findViewById(R.id.nama);
            TextView textView2 = findViewById(R.id.username);
            TextView textView3 = findViewById(R.id.bio);
            TextView textView4 = findViewById(R.id.email);
            TextView textView5 = findViewById(R.id.lokasi);
            TextView textView6 = findViewById(R.id.followers);
            TextView textView7  = findViewById(R.id.following);
            ImageView imageView = findViewById(R.id.gambar);

            showLoading(true);
            userCall.enqueue(new Callback<UserGithub>() {
                @Override
                public void onResponse(Call<UserGithub> call, Response<UserGithub> response) {
                    if (response.isSuccessful()) {
                        showLoading(false);
                        UserGithub user = response.body();
                        if (user != null) {
                            name = "Name: " + user.getName();
                            usernames = "Username: " + user.getUsername();
                            bio = "Bio: " + user.getBio();
                            gambar = user.getAvatarUrl();
                            location = "Lokasi: " + user.getLocation();
                            email = "EMAIL: " + user.getEmail();
                            followers = "Followers: " + user.getfollowers();
                            following = "Following: " + user.getfollowing();

                            textView.setText(name);
                            textView2.setText(usernames);
                            textView3.setText(bio);
                            textView4.setText(email);
                            textView5.setText(location);
                            textView6.setText(followers);
                            textView7.setText(following);
                            Picasso.get().load(gambar).into(imageView);
                        } else {
                            Toast.makeText(Detail.this, "Failed to get user data", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserGithub> call, Throwable t) {
                    Toast.makeText(Detail.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void showLoading(Boolean isLoading) {
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    public void onClick(View v) {
        Intent share = new Intent();
        share.setAction(Intent.ACTION_SEND);
        share.putExtra(Intent.EXTRA_TEXT, name + "\n" + usernames + "\n" + bio + "\n" + location + "\n" + email + "\n" +  gambar);
        share.setType("text/plain");

        Intent shareIntent = Intent.createChooser(share, null);
        startActivity(shareIntent);
    }
}
