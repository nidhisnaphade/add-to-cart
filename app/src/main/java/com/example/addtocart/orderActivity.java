package com.example.addtocart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.addtocart.adapters.ordersAdapter;
import com.example.addtocart.databinding.ActivityOrderBinding;
import com.example.addtocart.models.OrdersModel;

import java.util.ArrayList;

public class orderActivity extends AppCompatActivity {

    ActivityOrderBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        ArrayList<OrdersModel> list = new ArrayList<>();

//        list.add(new OrdersModel(R.drawable.download1,"cheese burger", "4","456"));
//        list.add(new OrdersModel(R.drawable.download1,"cheese burger", "4","456"));
//        list.add(new OrdersModel(R.drawable.download1,"cheese burger", "4","456"));
//        list.add(new OrdersModel(R.drawable.download1,"cheese burger", "4","456"));
//        list.add(new OrdersModel(R.drawable.download1,"cheese burger", "4","456"));
        DBHelper helper = new DBHelper(this);
        ArrayList<OrdersModel>
                list = helper.getOrders();




        ordersAdapter adapter = new ordersAdapter(list, this);
        binding.orderRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.orderRecyclerView.setLayoutManager(layoutManager);
    }
}