package com.example.addtocart;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.addtocart.databinding.ActivityDetailBinding;


public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final DBHelper dbHelper = new DBHelper(this);
        //int img= chec
        if (getIntent().getIntExtra("type", 0)==1) {
            final int image = getIntent().getIntExtra("image", 0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String name = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("description");
            binding.detailImage.setImageResource(image);
            binding.priceLbl.setText(String.format("%d", price));
            binding.textView.setText(name);
            binding.detailDescription.setText(description);
            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isInserted = dbHelper.insertOrder(binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            price,
                            image,
                            Integer.parseInt(binding.quantitiy.getText().toString()),
                            name,
                            description
                    );
                    if (isInserted)
                        Toast.makeText(DetailActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DetailActivity.this, "error", Toast.LENGTH_SHORT).show();
                }
            });
        }

        else{
///        Intent  i = new Intent(id, 0);
            final int id= getIntent().getIntExtra("id",0);
            Cursor cursor = dbHelper.getOrderById(id);
            final int img= cursor.getInt(4);
            Toast.makeText(this, ""+cursor.getString(1), Toast.LENGTH_SHORT).show();
            binding.detailImage.setImageResource(cursor.getInt(4));
            binding.priceLbl.setText(String.format("%d", cursor.getInt(3)));
            binding.nameBox.setText(cursor.getString(7));
            binding.textView.setText(cursor.getString(6));
            binding.nameBox.setText(cursor.getString(1));
            binding.phoneBox.setText(cursor.getString(2));
            binding.insertBtn.setText("Update Now");
            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //(String name, String phone, int price , int image,int quantity,String description, String foodName, int id)
                    boolean isUpdated=   dbHelper.updateOrder(binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            Integer.parseInt(binding.priceLbl.getText().toString()),
                            img,
                            1,
                            binding.detailDescription.getText().toString(),
                            binding.nameBox.getText().toString(),
                            id
                    );
                    if(isUpdated)
                        Toast.makeText(DetailActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DetailActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }}
