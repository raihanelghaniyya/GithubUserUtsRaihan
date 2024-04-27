package com.example.utsraihan.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.utsraihan.R;
import com.example.utsraihan.api.Api;
import com.example.utsraihan.models.ItemsItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private ImageView ivFoto;
    private TextView tvUsername, tvBio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ivFoto = findViewById(R.id.ivFoto);
        tvUsername = findViewById(R.id.tvUsername);
        tvBio = findViewById(R.id.tvBio);

        String username = getIntent().getStringExtra("username");
        Call<ItemsItem> itemsItemCall = Api.getApi().getDetailUser(username);
        itemsItemCall.enqueue(new Callback<ItemsItem>() {
            @Override
            public void onResponse(Call<ItemsItem> call, Response<ItemsItem> response) {
                if (response.isSuccessful()){
                    setDetailData(response.body());
                }
            }

            @Override
            public void onFailure(Call<ItemsItem> call, Throwable t) {

            }
        });


    }

    private void setDetailData(ItemsItem item) {
        tvUsername.setText(item.getLogin());
        tvBio.setText(item.getBio());
        Glide.with(DetailActivity.this).load(item.getAvatarUrl()).into(ivFoto);
    }
}