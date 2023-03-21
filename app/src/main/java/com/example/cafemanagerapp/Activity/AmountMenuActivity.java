package com.example.cafemanagerapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cafemanagerapp.AppDatabase.AppDatabase;
import com.example.cafemanagerapp.Entity.OrderDetail;
import com.example.cafemanagerapp.Fragments.DisplayCategoryFragment;
import com.example.cafemanagerapp.R;
import com.google.android.material.textfield.TextInputLayout;

public class AmountMenuActivity extends AppCompatActivity {

    TextInputLayout TXTL_amountmenu_Quantity;
    Button BTN_amountmenu_Confirm;
    int table_id, food_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount_menu);

        //Lấy đối tượng view
        TXTL_amountmenu_Quantity = (TextInputLayout)findViewById(R.id.txtl_amountmenu_Quantity);
        BTN_amountmenu_Confirm = (Button)findViewById(R.id.btn_amountmenu_Confirm);


        //Lấy thông tin từ bàn được chọn
        Intent intent = getIntent();
        table_id = intent.getIntExtra("maban",0);
        food_id = intent.getIntExtra("mamon",0);

        BTN_amountmenu_Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateAmount()){
                    return;
                }

                int order_id = AppDatabase.getInstance(AmountMenuActivity.this).orderDAO().getByTableId(table_id).getOrder_id();
                OrderDetail orderDetail = AppDatabase.getInstance(AmountMenuActivity.this).orderDetailDAO().getQuantityByOrIdAndFoodId(order_id, food_id);
                if(orderDetail != null){
                    //update số lượng món đã chọn
                    int old_quantity = AppDatabase.getInstance(AmountMenuActivity.this).orderDetailDAO().getQuantityByOrIdAndFoodId(order_id, food_id).getQuantity();
                    int new_quantity = Integer.parseInt(TXTL_amountmenu_Quantity.getEditText().getText().toString());
                    int total_quantity = old_quantity + new_quantity;

                    OrderDetail orderDetail1 = new OrderDetail();
                    orderDetail1.setOrderdetail_id(orderDetail.getOrderdetail_id());
                    orderDetail1.setOrder_id(order_id);
                    orderDetail1.setFood_id(food_id);
                    orderDetail1.setQuantity(total_quantity);

                    AppDatabase.getInstance(AmountMenuActivity.this).orderDetailDAO().update(orderDetail1);
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.add_sucessful),Toast.LENGTH_SHORT).show();

                }else {
                    //thêm số lượng món nếu chưa chọn món này
                    int quantity = Integer.parseInt(TXTL_amountmenu_Quantity.getEditText().getText().toString());
                    OrderDetail orderDetail1 = new OrderDetail();
                    orderDetail1.setOrder_id(order_id);
                    orderDetail1.setFood_id(food_id);
                    orderDetail1.setQuantity(quantity);

                    AppDatabase.getInstance(AmountMenuActivity.this).orderDetailDAO().insert(orderDetail1);
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.add_sucessful),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateAmount(){
        String val = TXTL_amountmenu_Quantity.getEditText().getText().toString().trim();
        if(val.isEmpty()){
            TXTL_amountmenu_Quantity.setError(getResources().getString(R.string.not_empty));
            return false;
        }else if(!val.matches(("\\d+(?:\\.\\d+)?"))){
            TXTL_amountmenu_Quantity.setError("Quantity is error!");
            return false;
        }else {
            TXTL_amountmenu_Quantity.setError(null);
            TXTL_amountmenu_Quantity.setErrorEnabled(false);
            return true;
        }
    }
}