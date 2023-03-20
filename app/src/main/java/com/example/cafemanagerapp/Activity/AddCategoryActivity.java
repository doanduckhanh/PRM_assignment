package com.example.cafemanagerapp.Activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cafemanagerapp.AppDatabase.AppDatabase;
import com.example.cafemanagerapp.Entity.Category;
import com.example.cafemanagerapp.R;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddCategoryActivity extends AppCompatActivity implements View.OnClickListener{
    Button BTN_addcategory_AddCate;
    ImageView IMG_addcategory_back, IMG_addcategory_AddImageCate;
    TextView TXT_addcategory_title;
    TextInputLayout TXTL_addcategory_CateName;
    int cate_id = 0;
    Bitmap bitmapold;   //Bitmap dạng ảnh theo ma trận các pixel

    //dùng result launcher do activityforresult ko dùng đc nữa
    ActivityResultLauncher<Intent> resultLauncherOpenIMG = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK && result.getData() != null){
                        Uri uri = result.getData().getData();
                        try{
                            InputStream inputStream = getContentResolver().openInputStream(uri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            IMG_addcategory_AddImageCate.setImageBitmap(bitmap);
                        }catch (FileNotFoundException e){
                            e.printStackTrace();
                        }
                    }
                }
            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);


        // Lấy đối tượng view
        BTN_addcategory_AddCate = (Button)findViewById(R.id.btn_addcategory_AddCate);
        TXTL_addcategory_CateName = (TextInputLayout)findViewById(R.id.txtl_addcategory_CateName);
        IMG_addcategory_back = (ImageView)findViewById(R.id.img_addcategory_back);
        IMG_addcategory_AddImageCate = (ImageView)findViewById(R.id.img_addcategory_AddImageCate);
        TXT_addcategory_title = (TextView)findViewById(R.id.txt_addcategory_title);


        BitmapDrawable olddrawable = (BitmapDrawable)IMG_addcategory_AddImageCate.getDrawable();
        bitmapold = olddrawable.getBitmap();

        //region Hiển thị trang sửa nếu được chọn từ context menu sửa
        cate_id = getIntent().getIntExtra("maloai",0);
//        if(cate_id != 0){
//            TXT_addcategory_title.setText(getResources().getString(R.string.editcategory));
//            LoaiMonDTO loaiMonDTO = loaiMonDAO.LayLoaiMonTheoMa(maloai);
//
//            //Hiển thị lại thông tin từ csdl
//            TXTL_addcategory_TenLoai.getEditText().setText(loaiMonDTO.getTenLoai());
//
//            byte[] categoryimage = loaiMonDTO.getHinhAnh();
//            Bitmap bitmap = BitmapFactory.decodeByteArray(categoryimage,0,categoryimage.length);
//            IMG_addcategory_ThemHinh.setImageBitmap(bitmap);
//
//            BTN_addcategory_TaoLoai.setText("Sửa loại");
//        }
        //endregion

        IMG_addcategory_back.setOnClickListener(this);
        IMG_addcategory_AddImageCate.setOnClickListener(this);
        BTN_addcategory_AddCate.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        boolean ktra;
        String chucnang;
        switch (id){
            case R.id.img_addcategory_back:
                finish();
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right); //animation
                break;

            case R.id.img_addcategory_AddImageCate:
                Intent iGetIMG = new Intent();
                iGetIMG.setType("image/*"); //lấy những mục chứa hình ảnh
                iGetIMG.setAction(Intent.ACTION_GET_CONTENT);   //lấy mục hiện tại đang chứa hình
                resultLauncherOpenIMG.launch(Intent.createChooser(iGetIMG,getResources().getString(R.string.choseimg)));    //mở intent chọn hình ảnh
                break;

            case R.id.btn_addcategory_AddCate:
                if(!validateImage() | !validateName()){
                    return;
                }

                String sCateName = TXTL_addcategory_CateName.getEditText().getText().toString();
                Category category = new Category();
                category.setKindName(sCateName);
                AppDatabase.getInstance(this).categoryDAO().addCategory(category);

                //Thêm, sửa loại dựa theo obj loaimonDTO
                Intent intent = new Intent();
                intent.putExtra("ktra",true);
                intent.putExtra("chucnang","themloai");
                setResult(RESULT_OK,intent);
                finish();
                break;

        }
    }

    //Chuyển ảnh bitmap về mảng byte lưu vào csdl
    private byte[] imageViewtoByte(ImageView imageView){
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    //region validate fields
    private boolean validateImage(){
        BitmapDrawable drawable = (BitmapDrawable)IMG_addcategory_AddImageCate.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        if(bitmap == bitmapold){
            Toast.makeText(getApplicationContext(),"Choose the image",Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }

    private boolean validateName(){
        String val = TXTL_addcategory_CateName.getEditText().getText().toString().trim();
        if(val.isEmpty()){
            TXTL_addcategory_CateName.setError(getResources().getString(R.string.not_empty));
            return false;
        }else {
            TXTL_addcategory_CateName.setError(null);
            TXTL_addcategory_CateName.setErrorEnabled(false);
            return true;
        }
    }
}