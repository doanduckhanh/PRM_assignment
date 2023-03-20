package com.example.cafemanagerapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.cafemanagerapp.Activity.HomeActivity;
import com.example.cafemanagerapp.Activity.PaymentActivity;
import com.example.cafemanagerapp.AppDatabase.AppDatabase;
import com.example.cafemanagerapp.DAO.OrderDAO;
import com.example.cafemanagerapp.DAO.TableSeatDAO;
import com.example.cafemanagerapp.Entity.Category;
import com.example.cafemanagerapp.Entity.Order;
import com.example.cafemanagerapp.Entity.TableSeat;
import com.example.cafemanagerapp.Fragments.DisplayCategoryFragment;
import com.example.cafemanagerapp.Fragments.TableFragment;
import com.example.cafemanagerapp.R;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class AdapterDisplayTable extends BaseAdapter implements View.OnClickListener{
    Context context;
    int layout;
    List<TableSeat> banAnDTOList;
    ViewHolder viewHolder;
    FragmentManager fragmentManager;

    public AdapterDisplayTable(Context context, int layout, List<TableSeat> banAnDTOList){
        this.context = context;
        this.layout = layout;
        this.banAnDTOList = banAnDTOList;
        fragmentManager = ((HomeActivity)context).getSupportFragmentManager();
    }

    @Override
    public int getCount() {
        return banAnDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return banAnDTOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return banAnDTOList.get(position).getTable_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewHolder = new ViewHolder();
            view = inflater.inflate(layout,parent,false);

            viewHolder.imgTable = (ImageView) view.findViewById(R.id.img_customtable_tableimage);
            viewHolder.imgAddFood = (ImageView) view.findViewById(R.id.img_customtable_AddFood);
            viewHolder.imgPayment = (ImageView) view.findViewById(R.id.img_customtable_Payment);
            viewHolder.imgHidden = (ImageView) view.findViewById(R.id.img_customtable_ButtonResume);
            viewHolder.txtTableName = (TextView)view.findViewById(R.id.txt_customtable_tablename);

            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if(banAnDTOList.get(position).isOr_status()){
            displayButton();
        }else {
            hiddenButton();
        }

        TableSeat tableSeat = banAnDTOList.get(position);

        String kttinhtrang = "true";
        //đổi hình theo tình trạng
        if(kttinhtrang.equals("true")){
            viewHolder.imgTable.setImageResource(R.drawable.ic_baseline_airline_seat_legroom_normal_40);
        }else {
            viewHolder.imgTable.setImageResource(R.drawable.ic_baseline_event_seat_40);
        }

        viewHolder.txtTableName.setText(tableSeat.getTable_name());
        viewHolder.imgTable.setTag(position);

        //sự kiện click
        viewHolder.imgTable.setOnClickListener(this);
        viewHolder.imgAddFood.setOnClickListener(this);
        viewHolder.imgPayment.setOnClickListener(this);
        viewHolder.imgHidden.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        viewHolder = (ViewHolder) ((View) v.getParent()).getTag();

        int pos1 = (int) viewHolder.imgTable.getTag();

        int tableId = banAnDTOList.get(pos1).getTable_id();
        String tableName = banAnDTOList.get(pos1).getTable_name();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date_created = dateFormat.format(calendar.getTime());

        switch (id) {
            case R.id.img_customtable_tableimage:
                int pos = (int) v.getTag();
                banAnDTOList.get(pos).setOr_status(true);
                displayButton();
                break;

            case R.id.img_customtable_ButtonResume:
                hiddenButton();
                break;

            case R.id.img_customtable_AddFood:
                Intent getIHome = ((HomeActivity) context).getIntent();
                int user_id = getIHome.getIntExtra("manv", 0);

                //Thêm bảng gọi món và update tình trạng bàn
                Order order = new Order();
                order.setTable_id(tableId);
                order.setUser_id(user_id);
                order.setOrder_date(date_created);
                order.setOrder_status(String.valueOf(false));
                order.setTotal(String.valueOf(0));
                TableSeat tableSeat = AppDatabase.getInstance((HomeActivity) context).tableDAO().getById(tableId);
                tableSeat.setOr_status(true);
                AppDatabase.getInstance((HomeActivity) context).orderDAO().insert(order);
                AppDatabase.getInstance((HomeActivity) context).tableDAO().update(tableSeat);

                //chuyển qua trang category
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                DisplayCategoryFragment displayCategoryFragment = new DisplayCategoryFragment();
                Bundle bDataCategory = new Bundle();
                bDataCategory.putInt("maban",tableId);
                displayCategoryFragment.setArguments(bDataCategory);

                transaction.replace(R.id.contentView,displayCategoryFragment).addToBackStack("hienthibanan");
                transaction.commit();
                break;

            case R.id.img_customtable_Payment:
                Intent iThanhToan = new Intent(context, PaymentActivity.class);
                iThanhToan.putExtra("maban",tableId);
                iThanhToan.putExtra("tenban",tableName);
                iThanhToan.putExtra("ngaydat",date_created);
                context.startActivity(iThanhToan);
                break;
        }
    }


    private void displayButton(){
        viewHolder.imgAddFood.setVisibility(View.VISIBLE);
        viewHolder.imgPayment.setVisibility(View.VISIBLE);
        viewHolder.imgHidden.setVisibility(View.VISIBLE);
    }
    private void hiddenButton(){
        viewHolder.imgAddFood.setVisibility(View.INVISIBLE);
        viewHolder.imgPayment.setVisibility(View.INVISIBLE);
        viewHolder.imgHidden.setVisibility(View.INVISIBLE);
    }

    public class ViewHolder{
        ImageView imgTable, imgAddFood, imgPayment, imgHidden;
        TextView txtTableName;
    }
}
