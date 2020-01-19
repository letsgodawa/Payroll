package com.sujan.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sujan.myapplication.home.DashboardActivity;

import io.realm.Realm;

public class SignupActivity extends AppCompatActivity {
    private EditText edtEmailAddress, edtPassword, edtPhone, edtUsername;
    private Button btnSubmit;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        iniToolbar();
        findViews();
        eventHandling();
    }

    private void eventHandling() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmailAddress.getText().toString().trim();
                String username = edtUsername.getText().toString().trim();
                String phone = edtPhone.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                if (!TextUtils.isEmpty(email)
                        && !TextUtils.isEmpty(username)
                        && !TextUtils.isEmpty(phone)
                        && !TextUtils.isEmpty(password)){
                    User user= new User();
                    user.setEmail(email);
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setPhone(phone);
                    saveSignupDb(user);
                }
            }
        });
    }

    private void saveSignupDb(final User user) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Number currentIdNum = realm.where(User.class).max("id");
                int nextId;
                if(currentIdNum == null) {
                    nextId = 1;
                } else {
                    nextId = currentIdNum.intValue() + 1;
                }
                user.setId(nextId);
                //...
                realm.insertOrUpdate(user);
                startActivity(new Intent(SignupActivity.this, DashboardActivity.class));
            }
        });
    }

    private void findViews() {
        edtEmailAddress = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtPhone = findViewById(R.id.edtPhone);
        edtUsername = findViewById(R.id.edtUsername);
        btnSubmit = findViewById(R.id.btnSubmit);

    }

    private void iniToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("BIM 5th sem");
    }
}
