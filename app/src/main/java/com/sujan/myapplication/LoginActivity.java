package com.sujan.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sujan.myapplication.category.CategoryActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtEmailAddress, edtPassword;
    private Button btnSubmit;
    private Toolbar toolbar;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        } else if (item.getItemId() == R.id.search) {
            onBackPressed();
            return true;

        } else
            return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
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
            Intent intent = new Intent(this, CategoryActivity.class);
            intent.putExtra("Name", "LoginActivity");
            startActivity(intent);
            finish();
        } else if (view.getId() == R.id.edtPassword) {
            Log.d("EditPassword", "Edit password has new state.");
        }

    }
}
