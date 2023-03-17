package com.example.cafemanagerapp.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.cafemanagerapp.Adapter.FoodCrudAdapter;
import com.example.cafemanagerapp.AppDatabase.AppDatabase;
import com.example.cafemanagerapp.Entity.Category;
import com.example.cafemanagerapp.Entity.Food;
import com.example.cafemanagerapp.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


public class DisplayFoodCrudFragment extends Fragment {
    private EditText editFullName;
    private EditText editPrice;
    private RadioButton rbActive;
    private RadioButton rbInactive;
    private ImageView ivImg;
    private Button btnChosse;
    private Button btnAdd;
    private Button btnUpdate;
    private Button btnDelete;
    private List<Food> mListFood;
    private RecyclerView rcvItemFood;
    private FoodCrudAdapter foodCrudAdapter;
    private Context thisContext;
    private EditText editFoodId;
    private Uri thisUri;
    private Spinner mySpinner;
    private Spinner dropdown;
    ActivityResultLauncher<String> mGetContent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_display_food_crud, container, false);
        initUI(rootView);
        mListFood = new ArrayList<>();
        foodCrudAdapter = new FoodCrudAdapter(new FoodCrudAdapter.IItemClicked(){
            @Override
            public void getClickedItem(Food food) {
                showFoodInfo(food);
            }
        });
        thisContext =container.getContext();
//        fixedCategory();
        LinearLayoutManager l = new LinearLayoutManager(thisContext);
        rcvItemFood.setLayoutManager(l);
        rcvItemFood.setAdapter(foodCrudAdapter);
        LoadData();

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteFoodFunction();
            }
        });
        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri uri) {
                        // Handle the returned Uri
                        thisUri = uri;
                        ivImg.setImageURI(uri);
                    }
                });

        btnChosse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(thisContext, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions((Activity) thisContext,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}
                            ,999);
                }
                mGetContent.launch("image/*");
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFoodFunction();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFoodFunction();
            }
        });
        return rootView;
    }
    private void fixedCategory(){
        Category c = new Category();
        c.setKindName("Coffee");
        Category c2 = new Category();
        c2.setKindName("Tea");
        Category c3 = new Category();
        c3.setKindName("Milk tea");
        Category c4 = new Category();
        c4.setKindName("Fast food");
        Category c5 = new Category();
        c5.setKindName("Cake");
        AppDatabase.getInstance(thisContext).categoryDAO().addCategory(c);
        AppDatabase.getInstance(thisContext).categoryDAO().addCategory(c2);
        AppDatabase.getInstance(thisContext).categoryDAO().addCategory(c3);
        AppDatabase.getInstance(thisContext).categoryDAO().addCategory(c4);
        AppDatabase.getInstance(thisContext).categoryDAO().addCategory(c5);
    }

    private void initUI(View v){
        editFullName = v.findViewById(R.id.edit_FoodName);
        editPrice = v.findViewById(R.id.edit_Price);
        rbActive =v.findViewById(R.id.rb_Active);
        rbInactive =v.findViewById(R.id.rb_Inactive);
        ivImg = v.findViewById(R.id.tv_Img);
        btnChosse = v.findViewById(R.id.btn_Img);
        btnAdd = v.findViewById(R.id.btn_Add);
        btnUpdate = v.findViewById(R.id.btn_Update);
        btnDelete = v.findViewById(R.id.btn_Delete);
        rcvItemFood =v.findViewById(R.id.rcv_item_food);
        editFoodId =v.findViewById(R.id.edit_FoodId);
        //get the spinner from the xml.
        dropdown = v.findViewById(R.id.spinner1);
    }
    private void LoadData(){
//        Food f = new Food();
//        f.setFood_name("Hello");
//        f.setCategory_id(1);
//        f.setPrice("123");
//        f.setStatus("true");
//        f.setImage(new byte[]{1});
//        AppDatabase.getInstance(getActivity()).foodDAO().addFood(f);
        mListFood =  AppDatabase.getInstance(thisContext).foodDAO().getAllFood();
        foodCrudAdapter.setData(mListFood);

//create a list of items for the spinner.
        List<Category> listC = AppDatabase.getInstance(thisContext).categoryDAO().getAllCategory();
        String[] items = new String[listC.size()];
        int index = 0;
        for (Category c : listC) {
            items[index] = (String) c.getKindName();
            index++;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(thisContext, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

    private Bitmap convertImageViewToBitmap(ImageView v){
        Bitmap bm=((BitmapDrawable)v.getDrawable()).getBitmap();
        return bm;
    }
    private Food getFoodInfo(){
        Food f = new Food();
        if(!editFoodId.getText().toString().isEmpty()){
            f.setFood_id(Integer.parseInt(editFoodId.getText().toString()));
        }
        try{
            if(editFullName.getText().toString().isEmpty()||
            editPrice.getText().toString().isEmpty()||
                    Integer.parseInt(editPrice.getText().toString())<=0||
                    dropdown.getSelectedItem().toString().isEmpty()
            ){
                return null;
            }
            else{
                f.setCategory_id(1);
                f.setFood_name(editFullName.getText().toString());
                f.setPrice(editPrice.getText().toString());
                if(rbActive.isChecked()){
                    f.setStatus("true");
                }else{
                    f.setStatus("false");
                }
                f.setImage(getBytesFromBitmap(convertImageViewToBitmap(ivImg)));
                f.setCategory_id(getCategoryId(dropdown.getSelectedItem().toString()));
            }
        }catch(Exception ex){
            return null;
        }
        return f;
    }
    private int getCategoryId(String name){
        Category c = AppDatabase.getInstance(thisContext).categoryDAO().getCategoryWithName(name);
        return c.getId();
    }
    private void showFoodInfo(Food f){
        if(!String.valueOf(f.getFood_id()).isEmpty()){
            editFoodId.setText(String.valueOf(f.getFood_id()));
        }
        editFullName.setText(f.getFood_name());
        editPrice.setText((f.getPrice()));
        ivImg.setImageBitmap(BitmapFactory.decodeByteArray(f.getImage(),0,f.getImage().length));
        if(f.getStatus().equals("true")){
            rbActive.setChecked(true);
        }
        else{
            rbInactive.setChecked(true);
        }
        dropdown.setSelection(f.getCategory_id()-1);
    }
    public byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        return stream.toByteArray();
    }
    private void clearFoodInfo(){
        editFoodId.setText("");
        editPrice.setText("");
        editFullName.setText("");
    }
    private void DeleteFoodFunction(){
        if(editFoodId.getText().toString().length()==0){
            Toast.makeText(thisContext, "Select a food first!", Toast.LENGTH_SHORT).show();
            return;
        }
        try{
            Food f = getFoodInfo();
            AppDatabase.getInstance(thisContext).foodDAO().deleteFood(f);
            clearFoodInfo();
            LoadData();
        }
        catch (Exception ex){
            Toast.makeText(thisContext, "Select a food first!", Toast.LENGTH_SHORT).show();
            return;
        }
    }
    private void addFoodFunction(){
        Food f = new Food();
        try{
            f = getFoodInfo();
            if(f == null){
                Toast.makeText(thisContext, "All fields must be filled", Toast.LENGTH_SHORT).show();
                return;
            }
            else{
                try{
                    AppDatabase.getInstance(thisContext).foodDAO().addFood(f);
                    LoadData();
                    Toast.makeText(thisContext, "ADD successfull!", Toast.LENGTH_SHORT).show();
                    clearFoodInfo();
                }catch (Exception ex){
                    Food fn =new Food();
                    fn.setFood_name(f.getFood_name());
                    fn.setImage(f.getImage());
                    fn.setPrice(f.getPrice());
                    fn.setStatus(f.getStatus());
                    fn.setCategory_id(f.getCategory_id());
                    AppDatabase.getInstance(thisContext).foodDAO().addFood(fn);
                    LoadData();
                    Toast.makeText(thisContext, "ADD successfull!", Toast.LENGTH_SHORT).show();
                    clearFoodInfo();
                }
            }
        }catch (Exception ex){
            return;
        }
        return;
    }
    private void updateFoodFunction(){
        try{
            Food f = getFoodInfo();
            if(editFoodId.getText().length()==0){
                Toast.makeText(thisContext, "Select a food first!", Toast.LENGTH_SHORT).show();
                return;
            }
            if(f!=null){
                AppDatabase.getInstance(thisContext).foodDAO().updateFood(f);
                Toast.makeText(thisContext, "Food updated successfull!", Toast.LENGTH_SHORT).show();
                clearFoodInfo();
                LoadData();
            }
            else{
                Toast.makeText(thisContext, "Select a food first!", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        catch(Exception ex){
            Toast.makeText(thisContext, "Select a food first!", Toast.LENGTH_SHORT).show();
            return;
        }

    }
}