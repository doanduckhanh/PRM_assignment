package com.example.cafemanagerapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cafemanagerapp.Entity.Category;
import com.example.cafemanagerapp.Entity.Order;
import com.example.cafemanagerapp.R;

import java.util.List;

public class AdapterRecycleViewOrder extends RecyclerView.Adapter<AdapterRecycleViewOrder.ViewHolder>  {
    Context context;
    int layout;
    List<Order> orderList;

    public AdapterRecycleViewOrder(Context context, int layout, List<Order> orderList) {
        this.context = context;
        this.layout = layout;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.txt_custom_order_id.setText(String.valueOf(order.getOrder_id()));
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public void setData(List<Order> listOrder){
        this.orderList = listOrder;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_custom_order_id, txt_custom_order_detail;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txt_custom_order_id = itemView.findViewById(R.id.tv_order_id);
            txt_custom_order_detail = itemView.findViewById(R.id.tv_order_detail);
        }
    }
}
