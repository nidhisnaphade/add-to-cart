package com.example.addtocart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import com.example.addtocart.models.OrdersModel;
import com.example.addtocart.models.OrdersModel;
import java.util.ArrayList;
public class DBHelper extends SQLiteOpenHelper {
    final static String DBName = "MyDatabase.db";
    final  static  int DBVersion =1;

    public DBHelper(@Nullable Context context) {
        super(context, DBName, null, DBVersion);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table orders" +
                        "(id integer primary key autoincrement," +
                        "name text,"+
                        "phone text,"+
                        "price int,"+
                        "image int,"+
                        "quantity int,"+
                        "description text,"+
                        "foodname text)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE if exists orders");
        onCreate(sqLiteDatabase);
    }
    public boolean insertOrder(String name, String phone, int price , int image,int quantity,String description, String foodName)
    {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values= new ContentValues();

        /*
         *name =1
         * phone =2
         * price =3
         * image = 4
         * quantity = 5
         * desc = 6
         * foodname= 7
         * */
        values.put("name", name);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image", image);
        values.put("quantity", quantity);
        values.put("description",description);
        values.put("foodname", foodName);
        long id =database.insert("orders",null, values);
        if(id<=0)
            return false;
        return true;
    }
    public ArrayList<OrdersModel> getOrders(){
        ArrayList<OrdersModel> ordersModels =new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor= database.rawQuery("Select id,foodname, image,price  from orders",null);
        if(cursor.moveToFirst())
        {
            while(cursor.moveToNext()){
                OrdersModel model = new OrdersModel();
                model.setOrderImage(cursor.getInt(2));
                model.setOrderNumber(cursor.getInt(0)+"");
                model.setSoldItem(cursor.getString(1));
                model.setPrice(cursor.getInt(3)+"");
                ordersModels.add(model);
            }
        }
        cursor.close();
        database.close();
        return ordersModels;
    }
    public  Cursor getOrderById(int id){
        //    ArrayList<OrdersModel> ordersModels =new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor= database.rawQuery("Select * from orders where id="+id, null);
        if(cursor!= null)
            cursor.moveToFirst();

        //    cursor.close();
        ///   database.close();
        return cursor;
    }

    public boolean updateOrder(String name, String phone, int price , int image,int quantity,String description, String foodName, int id)
    {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values= new ContentValues();

        /*
         *name =1
         * phone =2
         * price =3
         * image = 4
         * quantity = 5
         * desc = 6
         * foodname= 7
         * */
        values.put("name", name);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image", image);
        values.put("quantity", quantity);
        values.put("description",description);
        values.put("foodname", foodName);
        long row =database.update("orders", values, "id= "+id, null);
        if(row<=0)
            return false;
        return true;
    }
    public int deleteOrder(int id){
        SQLiteDatabase database = this.getWritableDatabase();//Database()
        return database.delete("orders", "id= "+id, null);
    }
}
