package com.example.movies_danqi_dzhao40.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movies_danqi_dzhao40.databinding.ItemRowLayoutBinding;
import com.example.movies_danqi_dzhao40.db.PurchaseDatabase;
import com.example.movies_danqi_dzhao40.models.Movie;
import com.example.movies_danqi_dzhao40.models.Purchase;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private final Context context;
    private final ArrayList<Movie> itemsArrayList;


    public ItemAdapter(Context context, ArrayList<Movie> itemsArrayList) {
        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(ItemRowLayoutBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Movie item = itemsArrayList.get(position);
        holder.bind(context,item);
    }

    @Override
    public int getItemCount() {
        return this.itemsArrayList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{

        ItemRowLayoutBinding itemBinding;
        PurchaseDatabase db;
        List<Purchase> purchasesFromDB;
        private final String TAG="A4LOGS";

        public ItemViewHolder(ItemRowLayoutBinding binding) {
            super(binding.getRoot());
            this.itemBinding = binding;
        }

        public void bind(Context context, Movie currentItem){
            itemBinding.tvName.setText(currentItem.getName());
            itemBinding.tvRelease.setText("Released: "+currentItem.getReleaseDate());
            itemBinding.tvRating.setText((int)currentItem.getRating()+"%");
            itemBinding.tvOverview.setText(currentItem.getOverview());
            Glide.with(context).load(currentItem.getImageUrl()).into(itemBinding.ivPhoto);

            this.db = PurchaseDatabase.getDatabase(context);


            itemBinding.btnBuyTicket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(itemBinding.getRoot(), "Ticket purchased", Snackbar.LENGTH_SHORT)
                            .show();
                    Log.d(TAG,""+currentItem.getId());
                    Purchase purchaseFound = db.purchaseDAO().getPurchaseByMovieId(currentItem.getId());
                    if(purchaseFound == null){
                        Purchase purchase = new Purchase(currentItem.getName(),currentItem.getId(),1);
                        db.purchaseDAO().insertPurchase(purchase);
                    }else {
                        purchaseFound.setQuantity(purchaseFound.getQuantity()+1);
                        db.purchaseDAO().updatePurchase(purchaseFound);
                    }
                }
            });
        }

    }
}
