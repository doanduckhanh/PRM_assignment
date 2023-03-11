package com.example.cafemanagerapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.cafemanagerapp.Adapter.UserCRUDAdapter;
import com.example.cafemanagerapp.AppDatabase.AppDatabase;
import com.example.cafemanagerapp.Entity.User;
import com.example.cafemanagerapp.R;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class UserCRUDActivity extends AppCompatActivity {
    private Button btnAdd;
    private List<User> mListUser;
    private UserCRUDAdapter userCRUDAdapter;
    private RecyclerView rcvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_crudactivity);
        initUi();
        mListUser = new ArrayList<>();
        userCRUDAdapter = new UserCRUDAdapter(new UserCRUDAdapter.IClickUpdate() {
            @Override
            public void updateUser(User user) {
                updateUserFunction(user);
            }
        }, new UserCRUDAdapter.IClickDetail() {
            @Override
            public void detailUser(User user) {
                detailUserFunction(user);
            }
        });
        LinearLayoutManager l = new LinearLayoutManager(this);
        rcvUser.setLayoutManager(l);
        rcvUser.setAdapter(userCRUDAdapter);
        LoadData();
    }
    private void LoadData(){
//        User u = new User();
//        u.setFull_name("Nguyen Huy Hoang");
//        u.setEmail("ABC@GMAI");
//        u.setAdmin(true);
//        u.setGender(true);
//        u.setPassword("123");
//        u.setPhone("1900100co");
//        u.setUsername("ABC");
//        u.setDob(Date.valueOf("1999-4-23"));
//        AppDatabase.getInstance(this).userDAO().insert(u);
        mListUser =  AppDatabase.getInstance(this).userDAO().getAll();
        userCRUDAdapter.setData(mListUser);
    }
    private void initUi(){
        rcvUser = findViewById(R.id.rcvUser);
        btnAdd = findViewById(R.id.btn_add);
    }
    private void updateUserFunction(User u){
        Intent i = new Intent(UserCRUDActivity.this,UserCrudUpdateActivity.class);
        Bundle b = new Bundle();
        b.putInt("id",u.getUser_id());
        b.putInt("function",2);
        i.putExtras(b);
        startActivity(i);
    }
    private void detailUserFunction(User u){
        Intent i = new Intent(UserCRUDActivity.this,UserCrudUpdateActivity.class);
        Bundle b = new Bundle();
        b.putInt("id",u.getUser_id());
        b.putInt("function",1);
        i.putExtras(b);
        startActivity(i);
    }
}