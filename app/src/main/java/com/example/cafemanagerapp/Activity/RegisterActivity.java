package com.example.cafemanagerapp.Activity;

import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cafemanagerapp.AppDatabase.UserDatabase;
import com.example.cafemanagerapp.Entity.User;
import com.example.cafemanagerapp.R;
import com.google.android.material.textfield.TextInputLayout;

import java.sql.Date;

public class RegisterActivity extends AppCompatActivity {
    private TextInputLayout edtFullname;
    private TextInputLayout edtUsername;
    private TextInputLayout edtPassword;
    private TextInputLayout edtEmail;
    private TextInputLayout edtPhone;
    private RadioButton rbMale;
    private RadioButton rbFeMale;
    private String date;
    private TextView edt_dob;
    private int defaultDay=1;
    private int defaultMonth=1;
    private int defaultYear=2001;
    Button btn_signupsubmit;

    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btn_signupsubmit = findViewById(R.id.btn_signupsubmit);
        edtFullname = findViewById(R.id.txtl_signup_HoVaTen);
        edtUsername = findViewById(R.id.txtl_signup_TenDN);
        edtPassword = findViewById(R.id.txtl_signup_MatKhau);
        edtEmail = findViewById(R.id.txtl_signup_Email);
        edtPhone = findViewById(R.id.txtl_signup_SDT);
        rbMale = findViewById(R.id.rb_signup_Nam);
        rbFeMale = findViewById(R.id.rb_signup_Nu);
        edt_dob = findViewById(R.id.edit_signup_dob);

        edt_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        RegisterActivity.this, android.R.style.Theme_Holo_Dialog,
                        setListener, defaultYear, defaultMonth-1,defaultDay
                        );
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
                edt_dob.setText(date);
            }
        };
        ((Button)btn_signupsubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = getInputRegister();
                if(user== null){
                    Toast.makeText(RegisterActivity.this,"Register Account Fail", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    Toast.makeText(RegisterActivity.this,"Register Account Successfully!", Toast.LENGTH_SHORT).show();
                    UserDatabase.getInstance(RegisterActivity.this).userDAO().insert(user);
                    Intent intent = new Intent(RegisterActivity.this, UserCRUDActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private User getInputRegister(){
        User user = new User();
        if (edtFullname.getEditText().getText().toString().isEmpty()
                || edtUsername.getEditText().getText().toString().isEmpty()
                || edtEmail.getEditText().getText().toString().isEmpty()
                || edtPhone.getEditText().getText().toString().isEmpty()
                || edtPassword.getEditText().getText().toString().isEmpty()
                || edt_dob.getText().toString().isEmpty()
        ) {
            return null;
        }else {
            user.setFull_name(edtFullname.getEditText().getText().toString());
            user.setUsername(edtUsername.getEditText().getText().toString());
            user.setEmail(edtEmail.getEditText().getText().toString());
            user.setPhone(edtPhone.getEditText().getText().toString());
            user.setPassword(edtPassword.getEditText().getText().toString());
        }
        if(rbMale.isChecked()){
            user.setGender(true);
        }else {
            user.setGender(false);
        }
        Date dob = Date.valueOf(edt_dob.getText().toString());
        user.setDob(dob);

        int userCount = UserDatabase.getInstance(RegisterActivity.this).userDAO().getUserCount();
        if(userCount == 0 ){
            user.setAdmin(true);
        }else {
            user.setAdmin(false);
        }
        return user;
    }
    public void rb_male(View view) {
        rbMale.setChecked(true);
    }
    public void rb_female(View view) {
        rbFeMale.setChecked(true);
    }

    public void backFromRegister(View view){
        Intent intent = new Intent(getApplicationContext(),WellcomeActivity.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.layoutRegister),"transition_signup");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(RegisterActivity.this,pairs);
            startActivity(intent,options.toBundle());
        }else {
            startActivity(intent);
        }
    }

    public void backLoginFromRegister(View view){
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.layoutRegister),"transition_signup");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(RegisterActivity.this,pairs);
            startActivity(intent,options.toBundle());
        }else {
            startActivity(intent);
        }
    }
}