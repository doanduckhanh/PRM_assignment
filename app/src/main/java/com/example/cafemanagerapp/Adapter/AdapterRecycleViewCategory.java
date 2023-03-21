package com.example.cafemanagerapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafemanagerapp.DAO.CategoryDAO;
import com.example.cafemanagerapp.Entity.Category;
import com.example.cafemanagerapp.R;

import java.util.List;

public class AdapterRecycleViewCategory extends RecyclerView.Adapter<AdapterRecycleViewCategory.ViewHolder>  {
    Context context;
    int layout;
    List<Category> categoryList;

    public AdapterRecycleViewCategory(Context context, int layout, List<Category> categoryList) {
        this.context = context;
        this.layout = layout;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.txt_customcategory_Name.setText(category.getKindName());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public void setData(List<Category> listCate){
        this.categoryList = listCate;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_customcategory_Name;
        ImageView img_customcategory_Image;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txt_customcategory_Name = itemView.findViewById(R.id.txt_customcategory_CateName);
            img_customcategory_Image = itemView.findViewById(R.id.img_customcategory_CateImage);
        }
    }



}
