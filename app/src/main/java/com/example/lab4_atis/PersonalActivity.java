package com.example.lab4_atis;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4_atis.book_list.BookListAdapter;
import com.example.lab4_atis.models.Book;
import com.example.lab4_atis.models.Request;

public class PersonalActivity extends AppCompatActivity implements BookListAdapter.OnItemClickListener {

    private BookListAdapter adapter;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        TextView ticketNumber = findViewById(R.id.tvTicketNumberPersonal);
        TextView fine = findViewById(R.id.tvFine);
        RecyclerView booksList = findViewById(R.id.booksListPersonal);

        ticketNumber.setText("Номер читательского билета: " + App.getInstance().getPeople().getTicketNumber());

        adapter = new BookListAdapter(App.getInstance().getPeople().getCustomerBookList(),true, this);

        booksList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        booksList.setLayoutManager(new LinearLayoutManager(this));
        booksList.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Book book) {
        Request request = new Request(App.getInstance().getPeople().getTicketNumber(), true, book);
        App.getInstance().getRequests().add(request);
        Toast.makeText(this, "Запрос отправлен", Toast.LENGTH_SHORT).show();
    }
}
