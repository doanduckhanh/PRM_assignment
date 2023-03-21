package com.example.cafemanagerapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cafemanagerapp.Entity.Order;
import com.example.cafemanagerapp.R;

import java.util.List;

public class AdapterDisplayOrder extends BaseAdapter {

    Context context;

    int layout;

    List<Order> listOrder;

    ViewHolder viewHolder;

    public class ViewHolder{
        Button btEdit;
        TextView tvOrderId, tvOrderDetail;
    }

    public AdapterDisplayOrder(Context context, int layout, List<Order> listOrder) {
        this.context = context;
        this.layout = layout;
        this.listOrder = listOrder;
    }

    @Override
    public int getCount() {
        return listOrder.size();
    }

    @Override
    public Object getItem(int position) {
        return listOrder.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listOrder.get(position).getOrder_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        if (view==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,parent,false);

            viewHolder.btEdit=(Button)view.findViewById(R.id.bt_edit);
            viewHolder.tvOrderId=(TextView)view.findViewById(R.id.tv_order_id);
            viewHolder.tvOrderDetail=(TextView)view.findViewById(R.id.tv_order_detail);
            view.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)view.getTag();
        }
        Order order=listOrder.get(position);
        viewHolder.tvOrderId.setText(order.getOrder_id());
        viewHolder.tvOrderDetail.setText(order.getOrder_date()+"\n"+order.getOrder_status());
        return view;
    }
}
