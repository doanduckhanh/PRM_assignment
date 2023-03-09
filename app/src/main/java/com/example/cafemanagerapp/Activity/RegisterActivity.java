package com.example.cafemanagerapp.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cafemanagerapp.AppDatabase.UserDatabase;
import com.example.cafemanagerapp.Entity.User;
import com.example.cafemanagerapp.R;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {
    private EditText full_name;
    private EditText username;
    private EditText password;
    private EditText email;
    private EditText phone;
    private RadioGroup gender;
    private DatePicker dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button btn_signupsubmit = findViewById(R.id.btn_signupsubmit);

        full_name = findViewById(R.id.txtl_signup_HoVaTen);
        username = findViewById(R.id.txtl_signup_TenDN);
        password = findViewById(R.id.txtl_signup_MatKhau);
        email = findViewById(R.id.txtl_signup_Email);
        phone = findViewById(R.id.txtl_signup_SDT);
        gender = findViewById(R.id.rg_signup_GioiTinh);
        dob = findViewById(R.id.dt_signup_NgaySinh);

        ((Button)btn_signupsubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headlerRegister();
            }
        });
    }

    private void headlerRegister(){
        String fullName = full_name.getText().toString();
        String userName = username.getText().toString();
        String passWord = password.getText().toString();
        String Email = email.getText().toString();

        int userCount = UserDatabase.getInstance(this).userDAO().getUserCount();;
        if(!validateGender()){
            return;
        }
        
    }

    private boolean validateGender(){
        if(gender.getCheckedRadioButtonId() == -1){
            Toast.makeText(this,"Hãy chọn giới tính",Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
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
}