package com.example.addtocart.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.addtocart.DBHelper;
import com.example.addtocart.DetailActivity;
import com.example.addtocart.models.OrdersModel;

import com.example.addtocart.R;

import java.util.ArrayList;

public class ordersAdapter extends RecyclerView.Adapter<ordersAdapter.ViewHolder>{
    ArrayList<OrdersModel> list;
    Context context;

    public ordersAdapter(ArrayList<OrdersModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_sample, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final OrdersModel  model = list.get(position);
        holder.imageView.setImageResource(model.getOrderImage());
        holder.soldItemName.setText(model.getSoldItem());
        holder.orderNumber.setText(model.getOrderNumber());
        holder.price.setText(model.getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id", Integer.parseInt(model.getOrderNumber()));
                intent.putExtra("type", 2);
                context.startActivity(intent);

            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                new AlertDialog.Builder(context)
                        .setTitle("Delete item")
                        .setMessage("Are you sure to delete")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DBHelper dbHelper = new DBHelper(context);
                                if(dbHelper.deleteOrder(Integer.parseInt(model.getOrderNumber()))>0){
                                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                                }
                                else
                                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();

                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();


          /*      DBHelper dbHelper = new DBHelper(context);
                if(dbHelper.deleteOrder(Integer.parseInt(model.getOrderNumber()))>0){
                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        */        return false;
            }
        });
    }
    // holder.item

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //for order_sample
        ImageView imageView;
        TextView soldItemName, orderNumber,price; //TextView tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //     tv = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView2);
            soldItemName = itemView.findViewById(R.id.orderItemName);
            orderNumber = itemView.findViewById(R.id.orderNumber);
            price = itemView.findViewById(R.id.textView7);
        }
    }
}
