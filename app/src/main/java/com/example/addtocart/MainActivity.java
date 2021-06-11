package com.example.addtocart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import com.example.addtocart.adapters.MainAdapter;
import com.example.addtocart.databinding.ActivityMainBinding;
import com.example.addtocart.models.MainModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater()); //getting activity main recycler view
        setContentView(binding.getRoot());

        ArrayList<MainModel> list= new ArrayList<>();
        list.add(new MainModel(R.drawable.download1,"Chocolate Cake","5","Loaded Chocolate"));
        list.add(new MainModel(R.drawable.download1,"Pineapple Cake","5","Loaded Chocolate"));
        list.add(new MainModel(R.drawable.download1,"Chocolate Cake","5","Loaded Chocolate"));
        list.add(new MainModel(R.drawable.download1,"Pineapple Cake","5","Loaded Chocolate"));

        MainAdapter adapter = new MainAdapter(list,this);
        binding.recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.orders:
                startActivity(new Intent(MainActivity.this , orderActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}