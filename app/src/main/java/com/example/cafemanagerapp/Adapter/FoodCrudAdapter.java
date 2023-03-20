package com.example.cafemanagerapp.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafemanagerapp.Entity.Food;
import com.example.cafemanagerapp.Entity.User;
import com.example.cafemanagerapp.R;

import java.util.List;

public class FoodCrudAdapter extends RecyclerView.Adapter<FoodCrudAdapter.FoodCrudViewHolder> {
    private List<Food> mListFood;
    private  IItemClicked iItemClicked;
    public interface IItemClicked {
        void getClickedItem(Food food);
    }
    public FoodCrudAdapter (IItemClicked iItemClicked){
        this.iItemClicked =iItemClicked;
    }
    @NonNull
    @Override
    public FoodCrudViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food,parent,false);
        return new FoodCrudViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodCrudViewHolder holder, int position) {
        Food f = mListFood.get(position);
        holder.tvFullName.setText(f.getFood_name());
        holder.tvPrice.setText(f.getPrice());
        holder.ivImage.setImageBitmap(BitmapFactory.decodeByteArray(f.getImage(),0,f.getImage().length));
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iItemClicked.getClickedItem(f);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mListFood != null){
            return mListFood.size();
        }
        return 0;
    }
    public void setData(List<Food> list){
        this.mListFood = list;
        notifyDataSetChanged();
    }

    public class FoodCrudViewHolder extends RecyclerView.ViewHolder{
        private TextView tvFullName;
        private ImageView ivImage;
        private View layoutItem;
        private TextView tvPrice;
        public FoodCrudViewHolder(@NonNull View itemView){
            super(itemView);
            tvFullName = itemView.findViewById(R.id.tv_fullName);
            ivImage = itemView.findViewById(R.id.iv_image);
            layoutItem = itemView.findViewById(R.id.layout_item);
            tvPrice = itemView.findViewById(R.id.tv_price);
        }
    }
}
