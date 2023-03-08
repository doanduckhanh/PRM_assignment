package com.example.cafemanagerapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.LinearLayout;

import com.example.cafemanagerapp.Adapter.UserCRUDAdapter;
import com.example.cafemanagerapp.AppDatabase.UserDatabase;
import com.example.cafemanagerapp.Entity.User;
import com.example.cafemanagerapp.R;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class UserCRUDActivity extends AppCompatActivity {
    private List<User> mListUser;
    private UserCRUDAdapter userCRUDAdapter;
    private RecyclerView rcvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_crudactivity);
        initUi();
        mListUser = new ArrayList<>();
        userCRUDAdapter = new UserCRUDAdapter();
        LinearLayoutManager l = new LinearLayoutManager(this);
        rcvUser.setLayoutManager(l);
        rcvUser.setAdapter(userCRUDAdapter);
        LoadData();
    }
    private void LoadData(){
//        UserDatabase.getInstance(this).UserDAO().insert(new User(1, "nguyen a", "abc", "123", "abc@gmail", "19001000",true,new Date(1/1/1999), true));
//        UserDatabase.getInstance(this).UserDAO().insert(new User(0, "nguyen b", "abcd", "1234", "abcd@gmail", "19001000",false,new Date(1/1/1999), false));
        mListUser =  UserDatabase.getInstance(this).userDAO().getAll();
        userCRUDAdapter.setData(mListUser);
    }
    private void initUi(){
        rcvUser = findViewById(R.id.rcvUser);
    }
}