package com.example.cafemanagerapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cafemanagerapp.Activity.AddCategoryActivity;
import com.example.cafemanagerapp.Activity.HomeActivity;
import com.example.cafemanagerapp.Activity.UserCRUDActivity;
import com.example.cafemanagerapp.Adapter.AdapterDisplayOrder;
import com.example.cafemanagerapp.Adapter.AdapterRecycleViewCategory;
import com.example.cafemanagerapp.Adapter.AdapterRecycleViewOrder;
import com.example.cafemanagerapp.AppDatabase.AppDatabase;
import com.example.cafemanagerapp.DAO.CategoryDAO;
import com.example.cafemanagerapp.Entity.Category;
import com.example.cafemanagerapp.Entity.Order;
import com.example.cafemanagerapp.R;
import com.google.android.material.navigation.NavigationView;

import java.util.List;


public class DisplayHomeFragment extends Fragment implements View.OnClickListener{
    RecyclerView rcv_displayhome_LoaiMon, rcv_displayhome_order;
    RelativeLayout btn_statistic_home,btn_donhang_home, btn_menu_home, btn_user_home;
    TextView txt_displayhome_ViewAllCategory, txt_displayhome_ViewAllStatistic;
    AdapterRecycleViewCategory adapterRecycleViewCategory;

    AdapterRecycleViewOrder adapterDisplayOrder;

    List<Category> categoryList;
    List<Order> orderList;
    private Context thiscontext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display_home,container,false);
        thiscontext = container.getContext();
        ((HomeActivity)getActivity()).getSupportActionBar().setTitle("Trang chủ");
        setHasOptionsMenu(true);
        // Todo: lay doi tuong
        rcv_displayhome_LoaiMon = (RecyclerView)view.findViewById(R.id.rcv_displayhome_LoaiMon);
        rcv_displayhome_order = (RecyclerView)view.findViewById(R.id.rcv_displayhome_DonTrongNgay);
        btn_statistic_home = (RelativeLayout)view.findViewById(R.id.layout_displayhome_ThongKe);
        btn_donhang_home = (RelativeLayout)view.findViewById(R.id.layout_displayhome_XemBan);
        btn_menu_home = (RelativeLayout)view.findViewById(R.id.layout_displayhome_XemMenu);
        btn_user_home = (RelativeLayout)view.findViewById(R.id.layout_displayhome_XemNV);
        txt_displayhome_ViewAllCategory = (TextView) view.findViewById(R.id.txt_displayhome_ViewAllCategory);
        txt_displayhome_ViewAllStatistic = (TextView) view.findViewById(R.id.txt_displayhome_ViewAllStatistic);
        DisplayListCategory();
        DisplayOrder();
        btn_donhang_home.setOnClickListener(this);
        btn_statistic_home.setOnClickListener(this);
        btn_menu_home.setOnClickListener(this);
        btn_user_home.setOnClickListener(this);
        txt_displayhome_ViewAllCategory.setOnClickListener(this);
        txt_displayhome_ViewAllStatistic.setOnClickListener(this);
        return view;
    }
    private void DisplayListCategory(){
        rcv_displayhome_LoaiMon.setHasFixedSize(true);
        rcv_displayhome_LoaiMon.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        categoryList = AppDatabase.getInstance(thiscontext).categoryDAO().getAllCategory();
        adapterRecycleViewCategory = new AdapterRecycleViewCategory(getActivity(), R.layout.custom_layout_displaycategory, categoryList);
        rcv_displayhome_LoaiMon.setAdapter(adapterRecycleViewCategory);
        adapterRecycleViewCategory.notifyDataSetChanged();
    }
    private void DisplayOrder(){
        rcv_displayhome_order.setHasFixedSize(true);
        rcv_displayhome_order.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        orderList=AppDatabase.getInstance(thiscontext).orderDAO().getAll();
        adapterDisplayOrder=new AdapterRecycleViewOrder(getActivity(), R.layout.custom_layout_displayorder, orderList);
        rcv_displayhome_order.setAdapter(adapterDisplayOrder);
        adapterDisplayOrder.notifyDataSetChanged();
    }
    public void onClick(View view){
        int id = view.getId();
        NavigationView navigationView = (NavigationView)getActivity().findViewById(R.id.navigation_view_trangchu);
        switch (id){
            case R.id.layout_displayhome_ThongKe:
                Intent intent1 = new Intent(getActivity(), AddCategoryActivity.class);
                startActivity(intent1);
                break;
//            case R.id.txt_displayhome_ViewAllStatistic:
//                FragmentTransaction tranDisplayStatistic = getActivity().getSupportFragmentManager().beginTransaction();
//                tranDisplayStatistic.replace(R.id.contentView,new DisplayOrderFragment());
//                tranDisplayStatistic.addToBackStack(null);
//                tranDisplayStatistic.commit();
//                navigationView.setCheckedItem(R.id.nav_statistic);
//                break;
            case R.id.layout_displayhome_XemBan:
                FragmentTransaction tranDisplayTable = getActivity().getSupportFragmentManager().beginTransaction();
                tranDisplayTable.replace(R.id.contentView,new TableFragment());
                tranDisplayTable.addToBackStack(null);
                tranDisplayTable.commit();
                navigationView.setCheckedItem(R.id.nav_table);
                break;
            case R.id.layout_displayhome_XemMenu:
                FragmentTransaction tranDisplayFood = getActivity().getSupportFragmentManager().beginTransaction();
                tranDisplayFood.replace(R.id.contentView,new DisplayFoodCrudFragment());
                tranDisplayFood.addToBackStack(null);
                tranDisplayFood.commit();
                navigationView.setCheckedItem(R.id.nav_category);
                break;
            case R.id.layout_displayhome_XemNV:
                Intent intent = new Intent(getActivity(), UserCRUDActivity.class);
                startActivity(intent);
                break;
            case R.id.txt_displayhome_ViewAllCategory:
                FragmentTransaction tranDisplayCategory = getActivity().getSupportFragmentManager().beginTransaction();
                tranDisplayCategory.replace(R.id.contentView,new DisplayCategoryFragment());
                tranDisplayCategory.addToBackStack(null);
                tranDisplayCategory.commit();
                navigationView.setCheckedItem(R.id.nav_category);
                break;

        }
    }
}