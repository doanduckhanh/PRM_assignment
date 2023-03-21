package com.example.cafemanagerapp.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.cafemanagerapp.Activity.AddCategoryActivity;
import com.example.cafemanagerapp.Activity.AmountMenuActivity;
import com.example.cafemanagerapp.Activity.HomeActivity;
import com.example.cafemanagerapp.Adapter.AdapterDisplayMenu;
import com.example.cafemanagerapp.Adapter.AdapterDisplayOrder;
import com.example.cafemanagerapp.AppDatabase.AppDatabase;
import com.example.cafemanagerapp.DAO.OrderDAO;
import com.example.cafemanagerapp.Entity.Order;
import com.example.cafemanagerapp.R;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DisplayOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisplayOrderFragment extends Fragment {

    GridView gvDisplayOrder;

    List<Order> listOrder;

    AdapterDisplayOrder adapterDisplayOrder;

    FragmentManager fragmentManager;

    ActivityResultLauncher<Intent> resultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()== Activity.RESULT_OK){
                        Intent intent=result.getData();
                        boolean check=intent.getBooleanExtra("check",false);
                        if (check){
                            showOrder();
                            Toast.makeText(getActivity(), "Edit successfully", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getActivity(), "Edit failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_display_order, container,false);
        ((HomeActivity)getActivity()).getSupportActionBar().setTitle("Quan ly Order");

        gvDisplayOrder=(GridView)view.findViewById(R.id.gv_display_order);

        fragmentManager=getActivity().getSupportFragmentManager();

        showOrder();
        gvDisplayOrder.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent=new Intent(getActivity(), AmountMenuActivity.class);
//                intent.putExtra("orderId", listOrder.get(position).getOrder_id());
//                startActivity(intent);

                int orderId=listOrder.get(position).getOrder_id();
                String details=listOrder.get(position).getOrder_date()+"\n"+listOrder.get(position).getOrder_status();

                DisplayOrderFragment displayOrderFragment=new DisplayOrderFragment();
                Bundle bundle=new Bundle();
                bundle.putInt("orderId", orderId);
                bundle.putString("orderDetails", details);
                displayOrderFragment.setArguments(bundle);

                FragmentTransaction transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.contentView,displayOrderFragment).addToBackStack("showOrder");
                transaction.commit();
            }
        });
        setHasOptionsMenu(true);
        registerForContextMenu(gvDisplayOrder);

        return view;
    }

    @Override
    public void onCreateContextMenu(@NonNull @NotNull ContextMenu menu, @NonNull @NotNull View v, @Nullable @org.jetbrains.annotations.Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.edit_context_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.bt_edit){
            Intent intent=new Intent(getActivity(), AddCategoryActivity.class);
            resultLauncher.launch(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void showOrder(){
        listOrder= AppDatabase.getInstance(getContext()).orderDAO().getAll();
        adapterDisplayOrder=new AdapterDisplayOrder(getActivity(),R.layout.custom_layout_displayorder, listOrder);
        gvDisplayOrder.setAdapter(adapterDisplayOrder);
        adapterDisplayOrder.notifyDataSetChanged();
    }
}