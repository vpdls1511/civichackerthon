package com.ngyu.civihacker.lib.adpater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ngyu.civihacker.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder> {

    private ArrayList<Data> listData = new ArrayList<>();

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void addItem(Data data) {
        listData.add(data);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView titleText;
        private TextView contentText;
        private TextView addrText;
        private TextView disText;

        public ItemViewHolder(View view) {
            super(view);

            titleText = view.findViewById(R.id.titleText);
            contentText = view.findViewById(R.id.contentText);
            addrText = view.findViewById(R.id.addrText);
            disText = view.findViewById(R.id.disText);

        }

        public void onBind(Data data) {

            titleText.setText(data.getTitle());
            contentText.setText(data.getContent());
            addrText.setText(data.getAddr());
            disText.setText(data.getDis());

        }
    }
}
