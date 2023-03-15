package com.example.cafemanagerapp.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.cafemanagerapp.Activity.AmountMenuActivity;
import com.example.cafemanagerapp.Activity.HomeActivity;
import com.example.cafemanagerapp.Adapter.AdapterDisplayMenu;
import com.example.cafemanagerapp.AppDatabase.AppDatabase;
import com.example.cafemanagerapp.Entity.Food;
import com.example.cafemanagerapp.R;

import java.util.List;


public class DisplayMenuTableFragment extends Fragment {

    int cate_id, table_id;
    String cate_name,status;
    GridView gvDisplayMenu;
    List<Food> foodList;
    AdapterDisplayMenu adapterDisplayMenu;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_display_menu_table,container,false);
        ((HomeActivity)getActivity()).getSupportActionBar().setTitle("Menu");

        gvDisplayMenu = (GridView)view.findViewById(R.id.gvDisplayTableMenu);

        Bundle bundle = getArguments();
        if(bundle !=null){
            cate_id = bundle.getInt("maloai");
            cate_name = bundle.getString("tenloai");
            table_id = bundle.getInt("maban");
            HienThiDSMon();

            gvDisplayMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //nếu lấy đc mã bàn mới mở
                    status = foodList.get(position).getStatus();

                        if(status.equals("true")){
                            Intent iAmount = new Intent(getActivity(), AmountMenuActivity.class);
                            iAmount.putExtra("maban",table_id);
                            iAmount.putExtra("mamon",foodList.get(position).getFood_id());
                            startActivity(iAmount);
                        }else {
                            Toast.makeText(getActivity(),"The food is over!", Toast.LENGTH_SHORT).show();
                        }

                }
            });
        }
        setHasOptionsMenu(true);
        registerForContextMenu(gvDisplayMenu);
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    getParentFragmentManager().popBackStack("hienthiloai", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
                return false;
            }
        });

        return view;
    }

    //tạo 1 menu context show lựa chọn
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.edit_context_menu,menu);
    }


    private void HienThiDSMon(){
        foodList = AppDatabase.getInstance(getContext()).foodDAO().getByCateId(cate_id);
        adapterDisplayMenu = new AdapterDisplayMenu(getActivity(),R.layout.custom_layout_displaymenu,foodList);
        gvDisplayMenu.setAdapter(adapterDisplayMenu);
        adapterDisplayMenu.notifyDataSetChanged();
    }

}