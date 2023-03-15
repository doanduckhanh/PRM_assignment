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

import com.example.cafemanagerapp.Entity.Category;
import com.example.cafemanagerapp.R;

import java.util.List;

public class AdapterDisplayCategory extends BaseAdapter {
    Context context;
    int layout;
    List<Category> categoryList ;
    ViewHolder viewHolder;

    //constructor


    public AdapterDisplayCategory(Context context, int layout, List<Category> categoryList) {
        this.context = context;
        this.layout = layout;
        this.categoryList = categoryList;
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return categoryList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        //nếu lần đầu gọi view
        if(view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,parent,false);

            //truyền component vào viewholder để ko gọi findview ở những lần hiển thị khác
            viewHolder.img_customcategory_CateImage = (ImageView)view.findViewById(R.id.img_customcategory_CateImage);
            viewHolder.txt_customcategory_CateName = (TextView)view.findViewById(R.id.txt_customcategory_CateName);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Category category = categoryList.get(position);

        viewHolder.txt_customcategory_CateName.setText(category.getKindName());

//        byte[] categoryimage = ;
//        Bitmap bitmap = BitmapFactory.decodeByteArray(categoryimage,0,categoryimage.length);
        viewHolder.img_customcategory_CateImage.setImageResource(R.drawable.espresso);
        return view;
    }

    //tạo viewholer lưu trữ component
    public class ViewHolder{
        TextView txt_customcategory_CateName;
        ImageView img_customcategory_CateImage;
    }
}
