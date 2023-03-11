package com.example.cafemanagerapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
    private int u_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_crudactivity);
        initUi();
        mListUser = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("save role", Context.MODE_PRIVATE);
        u_id = sharedPreferences.getInt("u_id",0);
        Toast.makeText(UserCRUDActivity.this, "hello:"+u_id, Toast.LENGTH_SHORT).show();
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
        }, new UserCRUDAdapter.IClickDelete() {
            @Override
            public void deleteUser(User user) {
                deleteUserFunction(user);
            }
        });
        LinearLayoutManager l = new LinearLayoutManager(this);
        rcvUser.setLayoutManager(l);
        rcvUser.setAdapter(userCRUDAdapter);
        LoadData();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserCRUDActivity.this,UserCrudUpdateActivity.class);
                Bundle b = new Bundle();
                b.putInt("id",0);
                b.putInt("function",0);
                i.putExtras(b);
                startActivity(i);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        LoadData();
    }
    private void LoadData(){
//        User u = new User();
//        u.setFull_name("Nguyen Huy Hoang");
//        u.setEmail("ABC@GMAI");
//        u.setAdmin(false);
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
    private void deleteUserFunction(User u){

        List<User> allAdmins = AppDatabase.getInstance(this).userDAO().getAllAdmin();
        if(u.getUser_id() == u_id){
            Toast.makeText(UserCRUDActivity.this, "Can not delete yourselt!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(allAdmins.size() <= 1 && u.isAdmin == true){
                        Toast.makeText(UserCRUDActivity.this, "Can not delete the last admin!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        new AlertDialog.Builder(this).setTitle("Confirm delete")
                                .setMessage("Are you sure?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        AppDatabase.getInstance(UserCRUDActivity.this).userDAO().delete(u);
                                        Toast.makeText(UserCRUDActivity.this, "User deleted!!", Toast.LENGTH_SHORT).show();
                                        LoadData();
                                    }
                                })
                                .setNegativeButton("No",null)
                                .show();
    }
        LoadData();}
}