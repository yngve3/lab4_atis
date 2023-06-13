package com.example.lab4_atis.departments_list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4_atis.App;
import com.example.lab4_atis.logic.Departments;
import com.example.lab4_atis.activities.PersonalActivity;
import com.example.lab4_atis.R;
import com.example.lab4_atis.book_list.BookListActivity;
import com.example.lab4_atis.models.Request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DepartmentsListActivity extends AppCompatActivity {

    private RecyclerView departmentsList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departments_list);

        TextView label = findViewById(R.id.departmentsListLabel);

        if (App.getInstance().isWorker()) {
            label.setText("Запросы");
        }

        departmentsList = findViewById(R.id.departmentsList);

        departmentsList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        departmentsList.setLayoutManager(new LinearLayoutManager(this));

        if (!App.getInstance().isWorker()) {
            findViewById(R.id.btnToPersonal).setVisibility(View.VISIBLE);
            findViewById(R.id.btnToPersonal).setOnClickListener(view -> startActivity(new Intent(this, PersonalActivity.class)));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<Object> items = new ArrayList<>();

        if (App.getInstance().isWorker()) {
            items.addAll(App.getInstance().getRequests());
        } else {
            items.addAll(Arrays.stream(Departments.values()).collect(Collectors.toList()));
        }

        DepartmentsListAdapter adapter = new DepartmentsListAdapter(items, item -> {
            Intent intent = new Intent(this, BookListActivity.class);
            if (App.getInstance().isWorker()) {
                intent.putExtra(Request.class.getSimpleName(), App.getInstance().getRequests().indexOf(item));
            } else {
                intent.putExtra("departmentName", (Departments) item);
            }

            startActivity(intent);
        });

        departmentsList.setAdapter(adapter);
    }
}
