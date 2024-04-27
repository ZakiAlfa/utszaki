package com.example.utszaki;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.utszaki.response.SearchGithub;
import com.example.utszaki.response.UserGithub;
import com.example.utszaki.retrofit.ApiConfig;
import com.example.utszaki.retrofit.ApiService;
import com.example.utszaki.ui.GithubAdaptor;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GithubAdaptor adapter;

    private EditText editTextName;
    private Button buttonSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        editTextName = findViewById(R.id.edit_text_name);
        buttonSearch = findViewById(R.id.button_search);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = editTextName.getText().toString();

                ApiService apiService = ApiConfig.getApiService();
                Call<SearchGithub> call = apiService.searchUsers(searchQuery);

                call.enqueue(new Callback<SearchGithub>() {
                    @Override
                    public void onResponse(Call<SearchGithub> call, Response<SearchGithub> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            List<UserGithub> users = response.body().getUsers();
                            adapter = new GithubAdaptor(users);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        } else {
                            Toast.makeText(MainActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SearchGithub> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Network error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
