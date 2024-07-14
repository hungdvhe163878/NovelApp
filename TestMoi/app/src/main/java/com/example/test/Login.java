package com.example.test;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    EditText edtUserName, edtPass;
    Button btnLogin, btnRegister;
    DBContext DBContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();
        DBContext = new DBContext(this);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edtUserName.getText().toString();
                String password = edtPass.getText().toString();

                Cursor cursor = DBContext.getData();
                boolean isLoggedIn = false;

                while (cursor.moveToNext()) {
                    String datausername = cursor.getString(1);
                    String datapass = cursor.getString(3);

                    if (datausername.equals(userName) && datapass.equals(password)) {
                        int role = cursor.getInt(4);
                        int idd = cursor.getInt(0);
                        String email = cursor.getString(2);
                        String uName = cursor.getString(1);

                        Intent intent = new Intent(Login.this, MainActivity.class);
                        intent.putExtra("role", role);
                        intent.putExtra("idd", idd);
                        intent.putExtra("email", email);
                        intent.putExtra("uName", uName);

                        startActivity(intent);
                        isLoggedIn = true;
                        break;
                    }
                }
                if (!isLoggedIn) {
                    Toast.makeText(Login.this, "Login failed. Invalid username or password.", Toast.LENGTH_SHORT).show();
                }
                cursor.close();
            }
        });
    }

    private void AnhXa() {
        edtPass = findViewById(R.id.password);
        edtUserName = findViewById(R.id.userName);
        btnRegister = findViewById(R.id.register);
        btnLogin = findViewById(R.id.login);
    }
}
