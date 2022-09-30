package com.example.movies_danqi_dzhao40.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies_danqi_dzhao40.databinding.TicketRowLayoutBinding;
import com.example.movies_danqi_dzhao40.listeners.RowItemClickListener;
import com.example.movies_danqi_dzhao40.models.Purchase;

import java.util.ArrayList;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder> {

    private final Context context;
    private final ArrayList<Purchase> ticketsArrayList;
    private final RowItemClickListener clickListener;

    public TicketAdapter(Context context, ArrayList<Purchase> itemsArrayList, RowItemClickListener clickListener) {
        this.context = context;
        this.ticketsArrayList = itemsArrayList;
        this.clickListener = clickListener;
    }


    @NonNull
    @Override
    public TicketAdapter.TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TicketAdapter.TicketViewHolder(TicketRowLayoutBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TicketAdapter.TicketViewHolder holder, int position) {
        Purchase item = ticketsArrayList.get(position);
        holder.bind(context,item,clickListener);
    }

    @Override
    public int getItemCount() {
        return this.ticketsArrayList.size();
    }

    public static class TicketViewHolder extends RecyclerView.ViewHolder{

        TicketRowLayoutBinding ticketBinding;

        public TicketViewHolder(TicketRowLayoutBinding binding) {
            super(binding.getRoot());
            this.ticketBinding = binding;
        }

        public void bind(Context context, Purchase currentItem,RowItemClickListener clickListener){
            ticketBinding.tvTitle.setText(currentItem.getMovieTitle());
            ticketBinding.tvQuantity.setText("Tickets Purchased: "+currentItem.getQuantity());

            ticketBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onRowItemClicked(currentItem);
                }
            });
        }

    }
}

