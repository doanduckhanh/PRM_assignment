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
import com.example.cafemanagerapp.AppDatabase.AppDatabase;
import com.example.cafemanagerapp.DAO.OrderDAO;
import com.example.cafemanagerapp.DAO.TableSeatDAO;
import com.example.cafemanagerapp.Entity.TableSeat;
import com.example.cafemanagerapp.Fragments.TableFragment;
import com.example.cafemanagerapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class AdapterDisplayTable extends BaseAdapter implements View.OnClickListener{
    Context context;
    int layout;
    List<TableSeat> banAnDTOList;
    ViewHolder viewHolder;
    TableSeatDAO tableSeatDAO;
    OrderDAO orderDAO;
    FragmentManager fragmentManager;

    public AdapterDisplayTable(Context context, int layout, List<TableSeat> banAnDTOList){
        this.context = context;
        this.layout = layout;
        this.banAnDTOList = banAnDTOList;
//        banAnDAO = new BanAnDAO(context);
//        donDatDAO = new DonDatDAO(context);
//        fragmentManager = ((HomeActivity)context).getSupportFragmentManager();
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

            viewHolder.imgBanAn = (ImageView) view.findViewById(R.id.img_customtable_tableimage);
            viewHolder.imgGoiMon = (ImageView) view.findViewById(R.id.img_customtable_AddFood);
            viewHolder.imgThanhToan = (ImageView) view.findViewById(R.id.img_customtable_Payment);
            viewHolder.imgAnNut = (ImageView) view.findViewById(R.id.img_customtable_ButtonResume);
            viewHolder.txtTenBanAn = (TextView)view.findViewById(R.id.txt_customtable_tablename);

            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if(banAnDTOList.get(position).isOr_status()){
            HienThiButton();
        }else {
            AnButton();
        }

        TableSeat tableSeat = banAnDTOList.get(position);

        String kttinhtrang = "true";
        //đổi hình theo tình trạng
        if(kttinhtrang.equals("true")){
            viewHolder.imgBanAn.setImageResource(R.drawable.ic_baseline_airline_seat_legroom_normal_40);
        }else {
            viewHolder.imgBanAn.setImageResource(R.drawable.ic_baseline_event_seat_40);
        }

        viewHolder.txtTenBanAn.setText(tableSeat.getTable_name());
        viewHolder.imgBanAn.setTag(position);

        //sự kiện click
        viewHolder.imgBanAn.setOnClickListener(this);
        viewHolder.imgGoiMon.setOnClickListener(this);
        viewHolder.imgThanhToan.setOnClickListener(this);
        viewHolder.imgAnNut.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        viewHolder = (ViewHolder) ((View) v.getParent()).getTag();

        int vitri1 = (int) viewHolder.imgBanAn.getTag();

        int maban = banAnDTOList.get(vitri1).getTable_id();
        String tenban = banAnDTOList.get(vitri1).getTable_name();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String ngaydat= dateFormat.format(calendar.getTime());

        switch (id){
            case R.id.img_customtable_tableimage:
                int vitri = (int)v.getTag();
                banAnDTOList.get(vitri).setOr_status(true);
                HienThiButton();
                break;

            case R.id.img_customtable_ButtonResume:
                AnButton();
                break;

            case R.id.img_customtable_AddFood:
//                Intent getIHome = ((HomeActivity)context).getIntent();
//                int manv = getIHome.getIntExtra("manv",0);
//                String tinhtrang = banAnDAO.LayTinhTrangBanTheoMa(maban);
//
//                if(tinhtrang.equals("false")){
//                    //Thêm bảng gọi món và update tình trạng bàn
//                    DonDatDTO donDatDTO = new DonDatDTO();
//                    donDatDTO.setMaBan(maban);
//                    donDatDTO.setMaNV(manv);
//                    donDatDTO.setNgayDat(ngaydat);
//                    donDatDTO.setTinhTrang("false");
//                    donDatDTO.setTongTien("0");
//
//                    long ktra = donDatDAO.ThemDonDat(donDatDTO);
//                    banAnDAO.CapNhatTinhTrangBan(maban,"true");
//                    if(ktra == 0){ Toast.makeText(context,context.getResources().getString(R.string.add_failed),Toast.LENGTH_SHORT).show(); }
//                }
//                //chuyển qua trang category
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                DisplayCategoryFragment displayCategoryFragment = new DisplayCategoryFragment();
//
//                Bundle bDataCategory = new Bundle();
//                bDataCategory.putInt("maban",maban);
//                displayCategoryFragment.setArguments(bDataCategory);
//
//                transaction.replace(R.id.contentView,displayCategoryFragment).addToBackStack("hienthibanan");
//                transaction.commit();
                break;

            case R.id.img_customtable_Payment:
                //chuyển dữ liệu qua trang thanh toán
//                Intent iThanhToan = new Intent(context, PaymentActivity.class);
//                iThanhToan.putExtra("maban",maban);
//                iThanhToan.putExtra("tenban",tenban);
//                iThanhToan.putExtra("ngaydat",ngaydat);
//                context.startActivity(iThanhToan);
                break;
        }
    }

    private void HienThiButton(){
        viewHolder.imgGoiMon.setVisibility(View.VISIBLE);
        viewHolder.imgThanhToan.setVisibility(View.VISIBLE);
        viewHolder.imgAnNut.setVisibility(View.VISIBLE);
    }
    private void AnButton(){
        viewHolder.imgGoiMon.setVisibility(View.INVISIBLE);
        viewHolder.imgThanhToan.setVisibility(View.INVISIBLE);
        viewHolder.imgAnNut.setVisibility(View.INVISIBLE);
    }

    public class ViewHolder{
        ImageView imgBanAn, imgGoiMon, imgThanhToan, imgAnNut;
        TextView txtTenBanAn;
    }
}
