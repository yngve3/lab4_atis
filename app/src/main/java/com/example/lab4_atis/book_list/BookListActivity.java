package com.example.lab4_atis.book_list;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4_atis.App;
import com.example.lab4_atis.R;
import com.example.lab4_atis.models.Book;
import com.example.lab4_atis.models.Request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BookListActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_list);

        TextView ticketNumber = findViewById(R.id.tvTicketNumberInfo);
        TextView kindRequest = findViewById(R.id.tvKindRequestInfo);
        Button btnOk = findViewById(R.id.btnOk);

        List<Book> books = new ArrayList<>();

        TextView label = findViewById(R.id.booksListLabel);
        label.setText(getIntent().getStringExtra("departmentName"));

        RecyclerView booksList = findViewById(R.id.booksList);

        if (App.getInstance().isWorker()) {
            int requestInd = getIntent().getIntExtra(Request.class.getSimpleName(), -1);
            Request request = App.getInstance().getRequests().get(requestInd);
            books.add(request.getBook());

            ticketNumber.setVisibility(View.VISIBLE);
            kindRequest.setVisibility(View.VISIBLE);
            btnOk.setVisibility(View.VISIBLE);

            btnOk.setOnClickListener((view -> {
                if (request.isReturn()) {
                    App.getInstance().getPeople().returnBook(request.getBook());
                    request.getBook().getBookCard().inc();
                    Toast.makeText(this, "Книга возвращена в библиотеку", Toast.LENGTH_SHORT).show();
                } else {
                    App.getInstance().getPeople().getBookFromLibrary(request.getBook());
                    request.getBook().getBookCard().dec();
                    Toast.makeText(this, "Книга отдана клиенту", Toast.LENGTH_SHORT).show();
                }

                App.getInstance().getRequests().remove(requestInd);
                finish();
            }));

            ticketNumber.setText("Номер читательского билета: " + request.getTicketNumber());
            if (request.isReturn()) {
                kindRequest.setText("Запрос на возвращение книги");
            } else {
                kindRequest.setText("Запрос на отдачу книги");
            }
        } else {
            books.addAll(App.getInstance().getPeople().viewBooks().stream().filter((book -> book.getBookCard().getCount() > 0)).collect(Collectors.toList()));
        }

        BookListAdapter adapter = new BookListAdapter(books, false, book -> {
            if (!App.getInstance().isWorker()) {
                Request request = new Request(App.getInstance().getPeople().getTicketNumber(), false, book);
                App.getInstance().getRequests().add(request);
                Toast.makeText(this, "Запрос отправлен\n" + book.getBookCard().getName(), Toast.LENGTH_SHORT).show();
            }
        });

        booksList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        booksList.setLayoutManager(new LinearLayoutManager(this));
        booksList.setAdapter(adapter);
    }
}
