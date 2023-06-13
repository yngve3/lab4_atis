package com.example.lab4_atis.departments_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4_atis.App;
import com.example.lab4_atis.Departments;
import com.example.lab4_atis.R;
import com.example.lab4_atis.models.Request;

import java.util.List;

public class DepartmentsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Object item);
    }

    private final List<Object> items;
    private final OnItemClickListener listener;

    public DepartmentsListAdapter(List<Object> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (App.getInstance().isWorker()) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_request_list, parent, false);
            return new RequestViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_departments_list, parent, false);
            return new DepartmentViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DepartmentViewHolder) {
            ((DepartmentViewHolder) holder).bind(items.get(position), listener);
        } else if (holder instanceof RequestViewHolder) {
            ((RequestViewHolder) holder).bind(items.get(position), listener);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class DepartmentViewHolder extends RecyclerView.ViewHolder {

        private final TextView departmentName;

        public DepartmentViewHolder(@NonNull View itemView) {
            super(itemView);

            departmentName = itemView.findViewById(R.id.tvDepartmentName);
        }

        public void bind(final Object department, final OnItemClickListener listener) {
            departmentName.setText(((Departments)department).getName());
            itemView.setOnClickListener(view -> listener.onItemClick(department));
        }
    }

    static class RequestViewHolder extends RecyclerView.ViewHolder {

        private final TextView kindRequest;
        private final TextView nameBook;
        private final TextView authorBook;

        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);

            kindRequest = itemView.findViewById(R.id.tvKindRequest);
            nameBook = itemView.findViewById(R.id.tvNameBookRequest);
            authorBook = itemView.findViewById(R.id.tvAuthorBookRequest);
        }

        public void bind(final Object request, final OnItemClickListener listener) {
            Request req = (Request) request;
            if (req.isReturn()) {
                kindRequest.setText("Запрос на возвращение книги");
            } else {
                kindRequest.setText("Запрос на отдачу книги");
            }

            nameBook.setText(req.getBook().getBookCard().getName());
            authorBook.setText(req.getBook().getBookCard().getBookShelf());

            itemView.setOnClickListener(view -> listener.onItemClick(req));
        }
    }
}
