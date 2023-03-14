package com.example.cafemanagerapp.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cafemanagerapp.Entity.Food;
import com.example.cafemanagerapp.R;

import java.util.List;

public class AdapterDisplayMenu extends BaseAdapter {
    Context context;
    int layout;
    List<Food> foodList;
    Viewholder viewholder;

    //constructor
    public AdapterDisplayMenu(Context context, int layout, List<Food> foodList){
        this.context = context;
        this.layout = layout;
        this.foodList = foodList;
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return foodList.get(position).getFood_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            viewholder = new Viewholder();
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,parent,false);

            viewholder.img_custommenu_FoodImage = (ImageView)view.findViewById(R.id.img_custommenu_menuimage);
            viewholder.txt_custommenu_FoodName = (TextView) view.findViewById(R.id.txt_custommenu_menuname);
            viewholder.txt_custommenu_Status = (TextView)view.findViewById(R.id.txt_custommenu_status);
            viewholder.txt_custommenu_Price = (TextView)view.findViewById(R.id.txt_custommenu_price);
            view.setTag(viewholder);
        }else {
            viewholder = (Viewholder) view.getTag();
        }
        Food food = foodList.get(position);
        viewholder.txt_custommenu_FoodName.setText(food.getFood_name());
        viewholder.txt_custommenu_Price.setText(food.getPrice()+" VNĐ");

        //hiển thị tình trạng của món
        if(food.getStatus().equals("true")){
            viewholder.txt_custommenu_Status.setText("Enable");
        }else{
            viewholder.txt_custommenu_Status.setText("Disable");
        }

        //lấy hình ảnh
        if(food.getImage() != null){
            byte[] menuimage = food.getImage();
            Bitmap bitmap = BitmapFactory.decodeByteArray(menuimage,0,menuimage.length);
            viewholder.img_custommenu_FoodImage.setImageBitmap(bitmap);
        }else {
            viewholder.img_custommenu_FoodImage.setImageResource(R.drawable.cafe_americano);
        }

        return view;
    }

    //tạo viewholer lưu trữ component
    public class Viewholder{
        ImageView img_custommenu_FoodImage;
        TextView txt_custommenu_FoodName, txt_custommenu_Price,txt_custommenu_Status;

    }
}
