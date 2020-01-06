package com.sujan.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sujan.myapplication.category.CategoryActivity;
import com.sujan.myapplication.home.DashboardActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtEmailAddress, edtPassword;
    private Button btnSubmit;
    private Dialog originalDialog;
    private AlertDialog.Builder alertDialogBuilder;
    private Toolbar toolbar;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        } else if (item.getItemId() == R.id.call) {
            checkCallPermission();
            return true;

        } else
            return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_call, menu);
        return true;
    }

    private void checkCallPermission() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //   is not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this, R.style.DialogTheme)
                        .setTitle("Call Permission Needed")
                        .setMessage("This app needs the Call  permission permission , please accept to use Call functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent();
                                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", "com.sujan.myapplication", null);
                                intent.setData(uri);
                                startActivity(intent);
                            }
                        })
                        .create();
                alertDialog.setCancelable(false);
                alertDialog.show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            }
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "9864831976"));
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this,
                            Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "9864831976"));
                        startActivity(intent);
                    }
                } else {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                    } else {
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", "com.sujan.myapplication", null);
                        intent.setData(uri);
                        startActivity(intent);
                    }
                }
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        iniToolbar();
        edtEmailAddress = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
        edtEmailAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = edtEmailAddress.getText().toString();
                Log.d("TextChanged", text);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
//        edtPassword.setOnClickListener(this);
//       btnSubmit.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view) {
//
//           }
//       });
    }

    private void iniToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("BIM 5th sem");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSubmit) {
            selectImage();
//            Intent intent = new Intent(this, CategoryActivity.class);
//            intent.putExtra("Name", "LoginActivity");
//            startActivity(intent);
//            finish();
        } else if (view.getId() == R.id.edtPassword) {
            Log.d("EditPassword", "Edit password has new state.");
        }

    }

    private void selectImage() {
        alertDialogBuilder = new AlertDialog.Builder(this);
        View dialogView = View.inflate(this, R.layout.upload_photo, null);
        TextView txtTakePhoto = dialogView.findViewById(R.id.txtTakePhoto);
        TextView txtChooseGallery = dialogView.findViewById(R.id.txtChooseGallery);
        TextView txtCancel = dialogView.findViewById(R.id.txtCancel);
        alertDialogBuilder.setView(dialogView);
        originalDialog = alertDialogBuilder.create();
        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                originalDialog.dismiss();
            }
        });
        originalDialog.getWindow().setDimAmount(0.7f);
        originalDialog.setCanceledOnTouchOutside(true);
        originalDialog.show();
    }
}
