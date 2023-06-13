package com.example.lab4_atis.book_list;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4_atis.App;
import com.example.lab4_atis.R;
import com.example.lab4_atis.models.Book;

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
        private final TextView lostBook;
        private final Button takeBook;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bookName = itemView.findViewById(R.id.tvNameBook);
            bookAuthor = itemView.findViewById(R.id.tvAuthorBook);
            bookCount = itemView.findViewById(R.id.tvCountBook);
            lostBook = itemView.findViewById(R.id.tvLostBook);
            takeBook = itemView.findViewById(R.id.btnGiveBook);
        }

        @SuppressLint("SetTextI18n")
        public void bind(final Book book, final BookListAdapter.OnItemClickListener listener, final boolean isPersonal) {
            bookName.setText(book.getBookCard().getName());
            bookAuthor.setText(book.getBookCard().getBookShelf());

            if (isPersonal) {
                takeBook.setVisibility(View.VISIBLE);
                takeBook.setOnClickListener(view -> listener.onItemClick(book));
                takeBook.setText("Отдать");
                bookCount.setText("Дата выдачи: " + book.getBookInsert().getDate()
                        + "\nДедлайн: " + book.getBookInsert().getDeadline()
                );
                lostBook.setVisibility(View.VISIBLE);
            } else {
                if (App.getInstance().isWorker()) {
                    bookCount.setText("Дата выдачи: " + book.getBookInsert().getDate()
                            + "\nДедлайн: " + book.getBookInsert().getDeadline()
                    );

                    if (book.getBookInsert().isLost()) {
                        lostBook.setVisibility(View.VISIBLE);
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
