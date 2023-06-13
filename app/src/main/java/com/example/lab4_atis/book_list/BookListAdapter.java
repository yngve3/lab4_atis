package com.example.lab4_atis.book_list;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4_atis.App;
import com.example.lab4_atis.Date;
import com.example.lab4_atis.R;
import com.example.lab4_atis.models.Book;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Book book);
    }

    private final List<Book> books;
    private final OnItemClickListener listener;
    private final boolean isPersonal;

    public BookListAdapter(List<Book> books, boolean isPersonal, OnItemClickListener listener) {
        this.books = books;
        this.listener = listener;
        this.isPersonal = isPersonal;
    }

    @NonNull
    @Override
    public BookListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_books_list, parent, false);

        return new BookListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BookListAdapter.ViewHolder holder, int position) {
        holder.bind(books.get(position), listener, isPersonal);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void removeItem(Book book) {
        books.remove(book);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView bookName;
        private final TextView bookAuthor;
        private final TextView bookCount;
        private final SwitchMaterial lostBook;
        private final Button takeBook;
        private final TextView tvLostBook;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bookName = itemView.findViewById(R.id.tvNameBook);
            bookAuthor = itemView.findViewById(R.id.tvAuthorBook);
            bookCount = itemView.findViewById(R.id.tvCountBook);
            lostBook = itemView.findViewById(R.id.btnLostBook);
            takeBook = itemView.findViewById(R.id.btnGiveBook);
            tvLostBook = itemView.findViewById(R.id.tvLost);
        }

        @SuppressLint("SetTextI18n")
        public void bind(final Book book, final BookListAdapter.OnItemClickListener listener, final boolean isPersonal) {
            bookName.setText(book.getBookCard().getName());
            bookAuthor.setText(book.getBookCard().getBookShelf());

            if (isPersonal) {
                takeBook.setVisibility(View.VISIBLE);
                takeBook.setText("Отдать");
                bookCount.setText("Дата выдачи: " + book.getBookInsert().getDate()
                        + "\nДедлайн: " + book.getBookInsert().getDeadline()
                );

                lostBook.setVisibility(View.VISIBLE);
                lostBook.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    book.getBookInsert().setLost(isChecked);
                });

                takeBook.setOnClickListener(view -> {
                    takeBook.setVisibility(View.GONE);
                    lostBook.setVisibility(View.GONE);
                    tvLostBook.setVisibility(View.VISIBLE);
                    tvLostBook.setText("Запрос в обработке");
                    listener.onItemClick(book);
                });
            } else {
                if (App.getInstance().isWorker()) {

                    if (Date.isAfterDeadline()) {
                        bookCount.setText("ПОСЛЕ ДЕДЛАЙНА");
                        bookCount.setTextColor(Color.parseColor("#6750a4"));
                    } else {
                        bookCount.setVisibility(View.GONE);
                    }


                    if (book.getBookInsert().isLost()) {
                        tvLostBook.setVisibility(View.VISIBLE);
                    }

                } else {
                    bookCount.setText("Количество экземпляров: " + book.getBookCard().getCount());
                    takeBook.setVisibility(View.VISIBLE);
                    takeBook.setOnClickListener(view -> listener.onItemClick(book));
                }
            }
        }
    }
}
