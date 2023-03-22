package com.example.cafemanagerapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.cafemanagerapp.Adapter.AdapterDisplayPayment;
import com.example.cafemanagerapp.AppDatabase.AppDatabase;
import com.example.cafemanagerapp.DAO.OrderDAO;
import com.example.cafemanagerapp.DAO.PaymentDAO;
import com.example.cafemanagerapp.DAO.TableSeatDAO;
import com.example.cafemanagerapp.Entity.Order;
import com.example.cafemanagerapp.Entity.Payment;
import com.example.cafemanagerapp.Entity.TableSeat;
import com.example.cafemanagerapp.R;

import java.util.List;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView IMG_payment_backbtn;
    TextView TXT_payment_TenBan, TXT_payment_NgayDat, TXT_payment_TongTien;
    Button BTN_payment_ThanhToan;
    GridView gvDisplayPayment;
    List<Payment> paymentList;
    AdapterDisplayPayment adapterDisplayPayment;
    long tongtien = 0;
    int maban, madondat;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_layout);

        //region thuộc tính view
        gvDisplayPayment= (GridView)findViewById(R.id.gvDisplayPayment);
        IMG_payment_backbtn = (ImageView)findViewById(R.id.img_payment_backbtn);
        TXT_payment_TenBan = (TextView)findViewById(R.id.txt_payment_TenBan);
        TXT_payment_NgayDat = (TextView)findViewById(R.id.txt_payment_NgayDat);
        TXT_payment_TongTien = (TextView)findViewById(R.id.txt_payment_TongTien);
        BTN_payment_ThanhToan = (Button)findViewById(R.id.btn_payment_ThanhToan);
        //endregion

        fragmentManager = getSupportFragmentManager();

        //lấy data từ mã bàn đc chọn
        Intent intent = getIntent();
        maban = intent.getIntExtra("maban",0);
        String tenban = intent.getStringExtra("tenban");
        String ngaydat = intent.getStringExtra("ngaydat");

        TXT_payment_TenBan.setText(tenban);
        TXT_payment_NgayDat.setText(ngaydat);

        //ktra mã bàn tồn tại thì hiển thị
        if(maban !=0 ){
            DisplayPayment();
            for (int i=0;i<paymentList.size();i++){
                int soluong = paymentList.get(i).getAmount();
                int giatien = paymentList.get(i).getPrice();

                tongtien += (soluong * giatien);
            }
            TXT_payment_TongTien.setText(String.valueOf(tongtien) +" VNĐ");
        }

        BTN_payment_ThanhToan.setOnClickListener(this);
        IMG_payment_backbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_payment_ThanhToan:
                TableSeat tableSeat = AppDatabase.getInstance(this).tableDAO().getById(maban);
                tableSeat.setOr_status(false);
                AppDatabase.getInstance(this).tableDAO().delete(tableSeat);
                Order order = AppDatabase.getInstance(this).orderDAO().getByTableId(maban);
                order.setOrder_status("true");
                order.setTotal(String.valueOf(tongtien));
                AppDatabase.getInstance(this).orderDAO().update(order);
                DisplayPayment();
                TXT_payment_TongTien.setText("0 VNĐ");
                Toast.makeText(getApplicationContext(),"Thanh toán thành công!",Toast.LENGTH_SHORT);
                //Toast.makeText(getApplicationContext(),"Lỗi thanh toán!",Toast.LENGTH_SHORT);
                Intent intent1 = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent1);
                break;
            case R.id.img_payment_backbtn:
                finish();
                break;
        }
    }

    //hiển thị data lên gridview
    private void DisplayPayment(){
        madondat = AppDatabase.getInstance(this).orderDAO().getOrderIdByTable(maban,"false");
        paymentList = AppDatabase.getInstance(this).paymentDAO().GetListFoodByOrderId(madondat);
        adapterDisplayPayment = new AdapterDisplayPayment(this,R.layout.custom_layout_paymentmenu,paymentList);
        gvDisplayPayment.setAdapter(adapterDisplayPayment);
        adapterDisplayPayment.notifyDataSetChanged();
    }
}