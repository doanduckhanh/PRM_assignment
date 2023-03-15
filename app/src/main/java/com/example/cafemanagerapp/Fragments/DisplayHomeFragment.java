package com.example.cafemanagerapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cafemanagerapp.Activity.HomeActivity;
import com.example.cafemanagerapp.Activity.UserCRUDActivity;
import com.example.cafemanagerapp.R;
import com.google.android.material.navigation.NavigationView;


public class DisplayHomeFragment extends Fragment implements View.OnClickListener{
    RecyclerView rcv_displayhome_LoaiMon, rcv_displayhome_DonTrongNgay;
    RelativeLayout btn_statistic_home,btn_donhang_home, btn_menu_home, btn_user_home;
    TextView txt_displayhome_ViewAllCategory, txt_displayhome_ViewAllStatistic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display_home,container,false);
        ((HomeActivity)getActivity()).getSupportActionBar().setTitle("Trang chủ");
        setHasOptionsMenu(true);

        // Todo: lay doi tuong
        rcv_displayhome_LoaiMon = (RecyclerView)view.findViewById(R.id.rcv_displayhome_LoaiMon);
        rcv_displayhome_DonTrongNgay = (RecyclerView)view.findViewById(R.id.rcv_displayhome_DonTrongNgay);
        btn_statistic_home = (RelativeLayout)view.findViewById(R.id.layout_displayhome_ThongKe);
        btn_donhang_home = (RelativeLayout)view.findViewById(R.id.layout_displayhome_XemBan);
        btn_menu_home = (RelativeLayout)view.findViewById(R.id.layout_displayhome_XemMenu);
        btn_user_home = (RelativeLayout)view.findViewById(R.id.layout_displayhome_XemNV);
        txt_displayhome_ViewAllCategory = (TextView) view.findViewById(R.id.txt_displayhome_ViewAllCategory);
        txt_displayhome_ViewAllStatistic = (TextView) view.findViewById(R.id.txt_displayhome_ViewAllStatistic);

        btn_donhang_home.setOnClickListener(this);
        btn_statistic_home.setOnClickListener(this);
        btn_menu_home.setOnClickListener(this);
        btn_user_home.setOnClickListener(this);

        return view;
    }

    public void onClick(View view){
        int id = view.getId();
        NavigationView navigationView = (NavigationView)getActivity().findViewById(R.id.navigation_view_trangchu);
        switch (id){
            case R.id.layout_displayhome_ThongKe:

            case R.id.txt_displayhome_ViewAllStatistic:
//                FragmentTransaction tranDisplayStatistic = getActivity().getSupportFragmentManager().beginTransaction();
//                tranDisplayStatistic.replace(R.id.contentView,new DisplayStatisticFragment());
//                tranDisplayStatistic.addToBackStack(null);
//                tranDisplayStatistic.commit();
//                navigationView.setCheckedItem(R.id.nav_statistic);

                break;

            case R.id.layout_displayhome_XemBan:
                FragmentTransaction tranDisplayTable = getActivity().getSupportFragmentManager().beginTransaction();
                tranDisplayTable.replace(R.id.contentView,new TableFragment());
                tranDisplayTable.addToBackStack(null);
                tranDisplayTable.commit();
                navigationView.setCheckedItem(R.id.nav_table);

                break;

            case R.id.layout_displayhome_XemMenu:
//                Intent iAddCategory = new Intent(getActivity(), AddCategoryActivity.class);
//                startActivity(iAddCategory);
//                navigationView.setCheckedItem(R.id.nav_category);

                break;
            case R.id.layout_displayhome_XemNV:
                Intent intent = new Intent(getActivity(), UserCRUDActivity.class);
                startActivity(intent);
                break;

            case R.id.txt_displayhome_ViewAllCategory:
//                FragmentTransaction tranDisplayCategory = getActivity().getSupportFragmentManager().beginTransaction();
//                tranDisplayCategory.replace(R.id.contentView,new DisplayCategoryFragment());
//                tranDisplayCategory.addToBackStack(null);
//                tranDisplayCategory.commit();
//                navigationView.setCheckedItem(R.id.nav_category);

                break;

        }
    }
}