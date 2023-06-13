package com.example.lab4_atis;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab4_atis.departments_list.DepartmentsListActivity;

public class LoginActivity extends AppCompatActivity {

    private final String WORKER_LOGIN = "wr";
    private final String CLIENT_LOGIN = "cl";
    private final String PASS = "qw";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiry_login);

        Button btnEnter = findViewById(R.id.btnEnter);
        EditText etPass = findViewById(R.id.etPass);
        EditText etLogin = findViewById(R.id.etLogin);

        btnEnter.setOnClickListener(view -> {
            String pass = etPass.getText().toString();
            String login = etLogin.getText().toString();
            if (pass.equals(PASS) && (login.equals(WORKER_LOGIN) || login.equals(CLIENT_LOGIN))) {
                switch (login) {
                    case WORKER_LOGIN -> {
                        Toast.makeText(this, "Работник вошел", Toast.LENGTH_SHORT).show();
                        App.getInstance().getPeople().authorizeLibrarian();
                        App.getInstance().setWorker(true);
                    }
                    case CLIENT_LOGIN -> {
                        Toast.makeText(this, "Клиент вошел", Toast.LENGTH_SHORT).show();
                        App.getInstance().getPeople().authorizeCustomer();
                        App.getInstance().setWorker(false);
                    }
                }

                startActivity(new Intent(this, DepartmentsListActivity.class));
            } else {
                Toast.makeText(this, "Неправильный логин или пароль", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
