package com.example.movies_danqi_dzhao40.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movies_danqi_dzhao40.R;
import com.example.movies_danqi_dzhao40.adapter.TicketAdapter;
import com.example.movies_danqi_dzhao40.databinding.Fragment2Binding;
import com.example.movies_danqi_dzhao40.db.PurchaseDatabase;
import com.example.movies_danqi_dzhao40.listeners.RowItemClickListener;
import com.example.movies_danqi_dzhao40.models.Purchase;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class Fragment2 extends Fragment implements RowItemClickListener {

    private Fragment2Binding binding;
    private TicketAdapter adapter;
    private ArrayList<Purchase> ticketsList = new ArrayList<>();
    private List<Purchase> purchasesFromDB;
    private PurchaseDatabase db;

    private final String TAG="A4LOGS";

    public Fragment2() {
        // Required empty public constructor
        super(R.layout.fragment_2);
    }

    // lifecycle functions - required for configuring view bindings
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = Fragment2Binding.inflate(inflater, container, false);
        View view = binding.getRoot();

        this.db = PurchaseDatabase.getDatabase(this.getContext());
        purchasesFromDB = this.db.purchaseDAO().getAllPurchases();
        if(purchasesFromDB.size() == 0){
            binding.tvNotice.setText("You do not have any tickets.");
        }else {
            ticketsList.clear();
            ticketsList.addAll(purchasesFromDB);
        }

        adapter = new TicketAdapter(this.getActivity(), ticketsList, Fragment2.this);
        binding.rvTickets.setLayoutManager(new LinearLayoutManager(this.getContext()));
        this.binding.rvTickets.addItemDecoration(new DividerItemDecoration(this.getContext(),DividerItemDecoration.VERTICAL));
        binding.rvTickets.setAdapter(this.adapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onRowItemClicked(Purchase purchase) {
        Snackbar.make(binding.getRoot(), "Deleted!", Snackbar.LENGTH_SHORT)
                .show();
        db.purchaseDAO().deletePurchase(purchase);
        purchasesFromDB = this.db.purchaseDAO().getAllPurchases();
        if (purchasesFromDB.size() == 0) {
            binding.tvNotice.setText("You do not have any tickets.");
        }
        ticketsList.clear();
        ticketsList.addAll(purchasesFromDB);
        adapter.notifyDataSetChanged();

    }
}