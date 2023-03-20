package com.example.cafemanagerapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.AdapterView;
import android.widget.GridView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentManager;
import com.example.cafemanagerapp.Activity.AmountMenuActivity;
import com.example.cafemanagerapp.Activity.HomeActivity;
import com.example.cafemanagerapp.Adapter.AdapterDisplayMenu;
import com.example.cafemanagerapp.Adapter.AdapterDisplayOrder;
import com.example.cafemanagerapp.AppDatabase.AppDatabase;
import com.example.cafemanagerapp.DAO.OrderDAO;
import com.example.cafemanagerapp.Entity.Order;
import com.example.cafemanagerapp.R;

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
//
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public DisplayOrderFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment DisplayOrderFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static DisplayOrderFragment newInstance(String param1, String param2) {
//        DisplayOrderFragment fragment = new DisplayOrderFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_display_order, container,false);
        ((HomeActivity)getActivity()).getSupportActionBar().setTitle("Quan ly Order");

        gvDisplayOrder=(GridView)view.findViewById(R.id.gv_display_order);

        showOrder();
        gvDisplayOrder.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), AmountMenuActivity.class);
                intent.putExtra("orderId", listOrder.get(position).getOrder_id());
                startActivity(intent);
            }
        });
        setHasOptionsMenu(true);
        registerForContextMenu(gvDisplayOrder);
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

    private void showOrder(){
        listOrder= AppDatabase.getInstance(getContext()).orderDAO().getAll();
        adapterDisplayOrder=new AdapterDisplayOrder(getActivity(),R.layout.custom_layout_displayorder, listOrder);
        gvDisplayOrder.setAdapter(adapterDisplayOrder);
        adapterDisplayOrder.notifyDataSetChanged();
    }
}