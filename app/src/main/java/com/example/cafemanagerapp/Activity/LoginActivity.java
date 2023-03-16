package com.example.cafemanagerapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.cafemanagerapp.AppDatabase.AppDatabase;
import com.example.cafemanagerapp.Entity.User;
import com.example.cafemanagerapp.R;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    Button btn_login;
    TextInputLayout edt_username;
    TextInputLayout edt_password;

    CheckBox cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_username = findViewById(R.id.txtl_login_TenDN);
        edt_password = findViewById(R.id.txtl_login_MatKhau);
        btn_login = findViewById(R.id.btn_login_DangNhap);
        cb = findViewById(R.id.cb_save);

        SharedPreferences s_f = getSharedPreferences("account", MODE_PRIVATE);
        String username = s_f.getString("username", "");
        String pass = s_f.getString("pass", "");
        boolean is_save = s_f.getBoolean("is_save", false);
        if (is_save) {
            edt_username.getEditText().setText(username);
            edt_password.getEditText().setText(pass);
            cb.setChecked(true);
        }

        ((Button)btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edt_username.getEditText().getText().toString();
                String password = edt_password.getEditText().getText().toString();

                if (cb.isChecked()) {
                    SharedPreferences s_f = getSharedPreferences("account", MODE_PRIVATE);
                    SharedPreferences.Editor editor = s_f.edit();
                    editor.putString("username", edt_username.getEditText().getText().toString());
                    editor.putString("pass", edt_password.getEditText().getText().toString());
                    editor.putBoolean("is_save", true);
                    editor.commit();
                } else {
                    SharedPreferences s_ff = getSharedPreferences("account", MODE_PRIVATE);
                    SharedPreferences.Editor editor = s_ff.edit();
                    editor.clear();
                    editor.commit();
                }

                User user = AppDatabase.getInstance(LoginActivity.this).userDAO().getUserLogin(username, password);
                if(!validateUserName() | !validatePassWord()){
                    return;
                }
                if(user != null){
                    // Todo: Login thanh cong
                  //  Toast.makeText(LoginActivity.this, "Username" + user.getUsername() + "Role" + user.getAdmin(), Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences = getSharedPreferences("save role", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("role", user.getAdmin());
                    editor.putInt("u_id", user.getUser_id());
                    editor.commit();

                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.putExtra("username", user.getUsername());
                    intent.putExtra("user_id", user.getUser_id());
                    startActivity(intent);
                }else {
                    Toast.makeText(LoginActivity.this, "Login Fail!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Todo: go back wellcome
    public void backFromLogin(View view){
        Intent intent = new Intent(getApplicationContext(),WellcomeActivity.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.layoutLogin),"transition_login");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this,pairs);
            startActivity(intent,options.toBundle());
        }
        else {
            startActivity(intent);
        }
    }

    // Todo : go to register
    public void callRegisterFromLogin(View view)
    {
        Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    private boolean validateUserName(){
        String val = edt_username.getEditText().getText().toString().trim();

        if(val.isEmpty()){
            edt_username.setError(getResources().getString(R.string.not_empty));
            return false;
        }else {
            edt_username.setError(null);
            edt_username.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassWord(){
        String val = edt_password.getEditText().getText().toString().trim();

        if(val.isEmpty()){
            edt_password.setError(getResources().getString(R.string.not_empty));
            return false;
        }else{
            edt_password.setError(null);
            edt_password.setErrorEnabled(false);
            return true;
        }
    }
}