package com.example.cafemanagerapp.Fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.cafemanagerapp.Activity.AddCategoryActivity;
import com.example.cafemanagerapp.Activity.HomeActivity;
import com.example.cafemanagerapp.Adapter.AdapterDisplayCategory;
import com.example.cafemanagerapp.AppDatabase.AppDatabase;
import com.example.cafemanagerapp.Entity.Category;
import com.example.cafemanagerapp.R;

import java.util.List;


public class DisplayCategoryFragment extends Fragment {

    GridView gvCategory;
    List<Category> categoryList;
    AdapterDisplayCategory adapter;
    FragmentManager fragmentManager;
    int table_id;
    ActivityResultLauncher<Intent> resultLauncherCategory = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent intent = result.getData();
                        boolean ktra = intent.getBooleanExtra("ktra",false);
                        String chucnang = intent.getStringExtra("chucnang");
                        if(chucnang.equals("themloai"))
                        {
                            if(ktra){
                                HienThiDSLoai();
                                Toast.makeText(getActivity(),"Thêm thành công",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getActivity(),"Thêm thất bại",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            if(ktra){
                                HienThiDSLoai();
                                Toast.makeText(getActivity(),"Sủa thành công",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getActivity(),"sửa thất bại",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                }
            });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_display_category,container,false);
        setHasOptionsMenu(true);
        ((HomeActivity)getActivity()).getSupportActionBar().setTitle("Category Menu");

        gvCategory = (GridView)view.findViewById(R.id.gvCategory);

        fragmentManager = getActivity().getSupportFragmentManager();

        HienThiDSLoai();

        Bundle bDataCategory = getArguments();
        if(bDataCategory != null){
            table_id = bDataCategory.getInt("maban");
        }

        gvCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int cate_id = categoryList.get(position).getId();
                String cate_name = categoryList.get(position).getKindName();
                DisplayMenuTableFragment displayMenuFragment = new DisplayMenuTableFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("maloai",cate_id);
                bundle.putString("tenloai",cate_name);
                bundle.putInt("maban",table_id);
                displayMenuFragment.setArguments(bundle);

                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.contentView,displayMenuFragment).addToBackStack("hienthiloai");
                transaction.commit();
            }
        });

        registerForContextMenu(gvCategory);

        return view;
    }

    //hiển thị contextmenu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.edit_context_menu,menu);
    }

    //xử lí context menu
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        int pos = menuInfo.position;
//        int cate_id = categoryList.get(pos).getId();
//
//        switch (id){
//            case R.id.itEdit:
//                Intent iEdit = new Intent(getActivity(), AddCategoryActivity.class);
//                iEdit.putExtra("maloai",cate_id);
//                resultLauncherCategory.launch(iEdit);
//                break;
//
//            case R.id.itDelete:
//                boolean ktra = loaiMonDAO.XoaLoaiMon(maloai);
//                if(ktra){
//                    HienThiDSLoai();
//                    Toast.makeText(getActivity(),getActivity().getResources().getString(R.string.delete_sucessful)
//                            ,Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(getActivity(),getActivity().getResources().getString(R.string.delete_failed)
//                            ,Toast.LENGTH_SHORT).show();
//                }
//                break;
//        }
//
//        return true;
//    }

    //khởi tạo nút thêm loại
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itAddCategory = menu.add(1,R.id.itAddCategory,1,R.string.addCategory);
        itAddCategory.setIcon(R.drawable.ic_baseline_add_24);
        itAddCategory.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    //xử lý nút thêm loại
    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.itAddCategory:
                Intent intent = new Intent(getActivity(), AddCategoryActivity.class);
                resultLauncherCategory.launch(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //hiển thị dữ liệu trên gridview
    private void HienThiDSLoai(){
        categoryList = AppDatabase.getInstance(getContext()).categoryDAO().getAllCategory();
        adapter = new AdapterDisplayCategory(getActivity(),R.layout.custom_layout_displaycategory,categoryList);
        gvCategory.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}