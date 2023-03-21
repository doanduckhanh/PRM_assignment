package com.example.cafemanagerapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cafemanagerapp.AppDatabase.AppDatabase;
import com.example.cafemanagerapp.Entity.User;
import com.example.cafemanagerapp.R;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class UserCrudUpdateActivity extends AppCompatActivity {
    private TextView edit_dob;
    DatePickerDialog.OnDateSetListener setListener;
    private int id;
    private User user;
    private TextView tvId;
    private EditText editFullName;
    private EditText editEmail;
    private EditText editPhone;
    private EditText editUserName;
    private EditText editPassword;
    private Button btnUpdate;
    private RadioButton rbMale;
    private RadioButton rbFeMale;
    private RadioButton rbIsAdmin;
    private RadioButton rbNotAdmin;
    private String date;
    private int defaultDay=1;
    private int defaultMonth=1;
    private int defaultYear=2001;
    private int function;
    String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_crud_update);

        id = getIntent().getExtras().getInt("id");
        function = getIntent().getExtras().getInt("function");

        if(function ==1){
            user = AppDatabase.getInstance(this).userDAO().LayNVTheoMa(id);
            initUI();
            setCalendar();
            loadData();
            btnUpdate.setText("Go back");
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserCrudUpdateActivity.super.onBackPressed();
                }
            });
        } else if (function == 2) {
            user = AppDatabase.getInstance(this).userDAO().LayNVTheoMa(id);
            initUI();
            setCalendar();
            loadData();
            List<User> allAdmins = AppDatabase.getInstance(UserCrudUpdateActivity.this).userDAO().getAllAdmin();
            int size = allAdmins.size();
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    User u = null;
                    u = getInputUser();
                    if(size <= 1 && user.isAdmin == true && u.isAdmin ==false) {
                        rbIsAdmin.setChecked(true);
                        Toast.makeText(UserCrudUpdateActivity.this, "You are the last admin!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(u == null){
                        Toast.makeText(UserCrudUpdateActivity.this, "All field must be fill correctly!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else{
                        AppDatabase.getInstance(UserCrudUpdateActivity.this).userDAO().update(u);
                        Toast.makeText(UserCrudUpdateActivity.this, "User updated!", Toast.LENGTH_SHORT).show();
                        UserCrudUpdateActivity.super.onBackPressed();
                    }
                }
            });
        } else if (function == 0) {
            initUI();
            setCalendar();
            btnUpdate.setText("Add new");
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    User u = null;
                    u = getInputUser();
                    if(u == null){
                        Toast.makeText(UserCrudUpdateActivity.this, "All field must be fill!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else{
                        AppDatabase.getInstance(UserCrudUpdateActivity.this).userDAO().insert(u);
                        Toast.makeText(UserCrudUpdateActivity.this, "User added!", Toast.LENGTH_SHORT).show();
                        UserCrudUpdateActivity.super.onBackPressed();
                    }
                }
            });
        }
    }
    private void setCalendar(){
        Calendar calendar =Calendar.getInstance();
        edit_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        UserCrudUpdateActivity.this,android.R.style.Theme_Holo_Dialog
                        ,setListener,defaultYear,defaultMonth-1,defaultDay);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view,int year,int month,int dayOfMonth){
                defaultYear=year;
                defaultMonth = month+1;
                defaultDay =dayOfMonth;
                date =defaultYear+"-"+defaultMonth+"-"+defaultDay;
                edit_dob.setText(date);
            }
        };
    }
    private void initUI(){
        tvId= findViewById(R.id.tv_id);
        editFullName= findViewById(R.id.edit_fullName);
        editEmail= findViewById(R.id.edit_email);
        editPhone= findViewById(R.id.edit_phone);
        editUserName = findViewById(R.id.edit_userName);
        editPassword = findViewById(R.id.edit_password);
        rbMale = findViewById(R.id.radio_male);
        rbFeMale = findViewById(R.id.radio_female);
        rbIsAdmin=findViewById(R.id.radio_isAdmin);
        rbNotAdmin=findViewById(R.id.radio_notAdmin);
        edit_dob =findViewById(R.id.edit_dob);
        btnUpdate = findViewById(R.id.btn_update);
    }
    private void loadData(){
        tvId.setText(String.valueOf(user.getUser_id()));
        editFullName.setText(user.getFull_name());
        editEmail.setText(user.getEmail());
        editPhone.setText(user.getPhone());
        editUserName.setText(user.getUsername());
        editPassword.setText(user.getPassword());
        if(user.gender){
            rbMale.setChecked(true);
        }else{
            rbFeMale.setChecked(true);
        }
        if(user.isAdmin){
            rbIsAdmin.setChecked(true);
        }
        else{
            rbNotAdmin.setChecked(true);
        }

        edit_dob.setText(user.getDob().toString());
        String[] dateArray = user.getDob().toString().split("-");
        defaultYear = Integer.parseInt(dateArray[0]);
        defaultMonth = Integer.parseInt(dateArray[1]);
        defaultDay = Integer.parseInt(dateArray[2]);
    }
    public void MaleClicked(View view) {
        rbMale.setChecked(true);
    }
    public void FeMaleClicked(View view) {
        rbFeMale.setChecked(true);
    }
    public void IsAdminClicked(View view) {
        rbIsAdmin.setChecked(true);
    }public void NotAdminClicked(View view) {
        rbNotAdmin.setChecked(true);
    }
    private User getInputUser(){
        User u =new User();
        if(id != 0){
            u.setUser_id(id);
        }
            if(editFullName.getText().toString().isEmpty()
            ||editUserName.getText().toString().isEmpty()
            ||editPassword.getText().toString().isEmpty()
            ||editEmail.getText().toString().isEmpty()
            ||editPhone.getText().toString().isEmpty()
            ||edit_dob.getText().toString().isEmpty()){
                return null;
            }else{
                if(editPassword.getText().length()<6){
                    Toast.makeText(UserCrudUpdateActivity.this, "Password have to be 6 or more character!", Toast.LENGTH_SHORT).show();
                    return null;
                }
                if(!editEmail.getText().toString().trim().matches(checkEmail)){
                    Toast.makeText(UserCrudUpdateActivity.this, "Email in wrong format", Toast.LENGTH_SHORT).show();
                    return null;
                }
                u.setFull_name(editFullName.getText().toString());
                u.setUsername(editUserName.getText().toString());
                u.setPassword(editPassword.getText().toString());
                u.setEmail(editEmail.getText().toString());
                u.setPhone(editPhone.getText().toString());
            }
            if(rbMale.isChecked()){
                u.setGender(true);
            }else{
                u.setGender(false);
            }
            Date date1=Date.valueOf( edit_dob.getText().toString());
            u.setDob(date1);
            if(rbIsAdmin.isChecked()){
                u.setAdmin(true);
            }else{
                u.setAdmin(false);
            }
        return u;
    }
}