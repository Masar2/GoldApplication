package com.ehb.masar.goldapplicatie.Activities;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ehb.masar.goldapplicatie.Manifest;
import com.ehb.masar.goldapplicatie.R;
import com.ehb.masar.goldapplicatie.database.ProductDAO;
import com.ehb.masar.goldapplicatie.domain.Product;
import com.google.android.gms.flags.impl.DataUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;


public class AddNewProduct extends AppCompatActivity {

    private Button addNewProduct;
    private Button addNewImage ;
    private EditText productName, productDescription, productPrice;
    private ImageView productImage;
    private static final int PhotoPickCode = 999;

    private ProductDAO mProductDAO;
    private Product mProduct;


    private String name, description;
    private Integer price;
    private Uri photoUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);


        mProductDAO = new ProductDAO(this);
        addNewProduct = (Button) findViewById(R.id.add_product);
        addNewImage = (Button) findViewById(R.id.add_image);
        productName = (EditText) findViewById(R.id.product_name);
        productImage = (ImageView) findViewById(R.id.product_image);
        productPrice = (EditText) findViewById(R.id.product_price);


        addNewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        AddNewProduct.this,
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        PhotoPickCode
                );
                //OpenGallery();
            }
        });

        addNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateProductGegevens();

            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PhotoPickCode) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, PhotoPickCode);
            } else {
                Toast.makeText(this, "You do'nt have permission to access the data ..", Toast.LENGTH_LONG).show();

            }
            return;

        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /*
    private void OpenGallery(){

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ring_product);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] photo = stream.toByteArray();

        Intent imageIntent = new Intent(Intent.ACTION_PICK,Uri.parse(Arrays.toString(photo)));
        imageIntent.setAction(Intent.ACTION_GET_CONTENT);
        //hier moeten wij nog zien , welke type hier wordt geplaatst
        imageIntent.setData(photoUri);
        startActivityForResult(imageIntent, PhotoPickCode);


    } */


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PhotoPickCode && resultCode == RESULT_OK && data != null) {

            photoUri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(photoUri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                productImage.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


            productImage.setImageURI(photoUri);

        }


    }


    public void ValidateProductGegevens() {

        description = productDescription.getText().toString();
        price = productPrice.getText().length();
        name = productName.getText().toString();

        if (photoUri == null) {
            Toast.makeText(this, "Product photo is mandatroy ..", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(description)) {
            Toast.makeText(this, "Product description is mandatroy ..", Toast.LENGTH_LONG).show();

        } else if (price == null) {
            Toast.makeText(this, "Product price is mandatroy ..", Toast.LENGTH_LONG).show();

        } else if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "product name is mandatory..", Toast.LENGTH_LONG).show();
        } else {
            AddProductGegevens();
        }

    }


    private void AddProductGegevens() {
        Product product = new Product();
        product.setProductName(name);
        product.setDescription(description);
        product.setPrice(price);
        //wij moeten een ArrayAdabater maken
        mProductDAO.addProduct(product);

    }


}
