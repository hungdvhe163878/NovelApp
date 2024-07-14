package com.example.test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.model.Account;

public class Register extends AppCompatActivity {
    EditText edtDKUser, edtDKPass, edtDKEmail;
    Button btnDKRegister, btnDKLogin;
    DBContext dbContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        dbContext = new DBContext(this);
        initializeUI();

        btnDKRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoan = edtDKUser.getText().toString();
                String matkhau = edtDKPass.getText().toString();
                String email = edtDKEmail.getText().toString();

                if (taikhoan.equals("") || matkhau.equals("") || email.equals("")) {
                    Log.e("Thông báo:", "Chưa nhập đầy đủ thông tin");
                    Toast.makeText(Register.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    Account acc1 = createAccount();
                    boolean isInserted = dbContext.AddAccount(acc1);
                    if (isInserted) {
                        Toast.makeText(Register.this, "Đăng ký thành công", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Register.this, "Đăng ký thất bại", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btnDKLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private Account createAccount() {
        String dkname = edtDKUser.getText().toString();
        String dkpass = edtDKPass.getText().toString();
        String dkemail = edtDKEmail.getText().toString();
        int role = 1;

        return new Account(dkname, dkpass, dkemail, role);
    }

    private void initializeUI() {
        edtDKEmail = findViewById(R.id.dkemail);
        edtDKPass = findViewById(R.id.dkpass);
        edtDKUser = findViewById(R.id.dkname);
        btnDKRegister = findViewById(R.id.dk);
        btnDKLogin = findViewById(R.id.back);
    }
}
