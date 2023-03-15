package com.example.cafemanagerapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cafemanagerapp.AppDatabase.AppDatabase;
import com.example.cafemanagerapp.Entity.TableSeat;
import com.example.cafemanagerapp.R;
import com.google.android.material.textfield.TextInputLayout;

public class AddTableActivity extends AppCompatActivity {

    TextInputLayout TXTL_addtable_tablename;
    Button BTN_addtable_AddTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_table);

        TXTL_addtable_tablename = (TextInputLayout)findViewById(R.id.txtl_addtable_tablename);
        BTN_addtable_AddTable = (Button)findViewById(R.id.btn_addtable_AddTable);

        BTN_addtable_AddTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sTableName = TXTL_addtable_tablename.getEditText().getText().toString();
                if(sTableName != null || sTableName.equals("")){
                    TableSeat tableSeat = new TableSeat();
                    tableSeat.setTable_name(sTableName);
                    tableSeat.setOr_status(false);
                    //trả về result cho displaytable
                    Intent intent = new Intent();
                    AppDatabase.getInstance(AddTableActivity.this).tableDAO().insert(tableSeat);
                    intent.putExtra("ketquathem", true);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });
    }
    private boolean validateName(){
        String val = TXTL_addtable_tablename.getEditText().getText().toString().trim();
        if(val.isEmpty()){
            TXTL_addtable_tablename.setError(getResources().getString(R.string.not_empty));
            return false;
        }else {
            TXTL_addtable_tablename.setError(null);
            TXTL_addtable_tablename.setErrorEnabled(false);
            return true;
        }
    }
}