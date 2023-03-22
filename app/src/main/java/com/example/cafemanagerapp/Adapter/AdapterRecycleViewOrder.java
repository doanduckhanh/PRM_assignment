package com.example.cafemanagerapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafemanagerapp.Activity.HomeActivity;
import com.example.cafemanagerapp.AppDatabase.AppDatabase;
import com.example.cafemanagerapp.Entity.Category;
import com.example.cafemanagerapp.Entity.Food;
import com.example.cafemanagerapp.Entity.Order;
import com.example.cafemanagerapp.Entity.OrderDetail;
import com.example.cafemanagerapp.Entity.TableSeat;
import com.example.cafemanagerapp.Fragments.DisplayHomeFragment;
import com.example.cafemanagerapp.Fragments.DisplayOrderFragment;
import com.example.cafemanagerapp.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecycleViewOrder extends RecyclerView.Adapter<AdapterRecycleViewOrder.ViewHolder>  {
    Context context;
    int layout;
    List<Order> orderList;
    List<OrderDetail> orderDetailsList;
    List<Food> foodList;
    List<TableSeat> tableSeatsList;
    public AdapterRecycleViewOrder(Context context, int layout, List<Order> orderList) {
        this.context = context;
        this.layout = layout;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layout, parent, false);
        orderDetailsList = AppDatabase.getInstance(parent.getContext()).orderDetailDAO().getAllOrderDetail();
        foodList =AppDatabase.getInstance(parent.getContext()).foodDAO().getAllFood();
        tableSeatsList =AppDatabase.getInstance(parent.getContext()).tableDAO().getAll();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = orderList.get(position);
        for (TableSeat ts:tableSeatsList){
            if(ts.getTable_id() == order.getTable_id()){
                holder.txt_custom_order_id.setText(ts.getTable_name());
            }
        }
        holder.txt_custom_order_detail.setText(order.getOrder_date());
        int price = 0;
        List<OrderDetail> thisListOrderDetail = new ArrayList<>();
        for (OrderDetail od:orderDetailsList
             ) {
            if(od.getOrder_id() == order.getOrder_id()){
                thisListOrderDetail.add(od);
            }
        }
        for (OrderDetail od:thisListOrderDetail
             ) {
            for (Food f:foodList
                 ) {
                if(od.getFood_id()==f.getFood_id()){
                    price+= Integer.parseInt(f.getPrice())* od.getQuantity();
                }
            }
        }
        holder.txt_custom_order_price.setText(String.valueOf(price)+"VND");

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
        TextView txt_custom_order_id, txt_custom_order_detail, txt_custom_order_price;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txt_custom_order_id = itemView.findViewById(R.id.tv_order_id);
            txt_custom_order_detail = itemView.findViewById(R.id.tv_order_detail);
            txt_custom_order_price = itemView.findViewById(R.id.tv_order_price);
        }
    }
}
