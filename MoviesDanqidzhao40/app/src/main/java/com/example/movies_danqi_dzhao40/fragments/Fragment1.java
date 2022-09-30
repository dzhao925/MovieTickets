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
import com.example.movies_danqi_dzhao40.adapter.ItemAdapter;
import com.example.movies_danqi_dzhao40.databinding.Fragment1Binding;
import com.example.movies_danqi_dzhao40.models.Movie;
import com.example.movies_danqi_dzhao40.models.ResponseObject;
import com.example.movies_danqi_dzhao40.network.API;
import com.example.movies_danqi_dzhao40.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment1 extends Fragment {

    private Fragment1Binding binding;
    private ItemAdapter adapter;
    private ArrayList<Movie> moviesList = new ArrayList<>();
    private List<Movie> itemsFromAPI;
    private API api;

    private final String TAG="A4LOGS";

    public Fragment1() {
        // Required empty public constructor
        super(R.layout.fragment_1);
    }

    // lifecycle functions - required for configuring view bindings
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = Fragment1Binding.inflate(inflater, container, false);
        View view = binding.getRoot();



        adapter = new ItemAdapter(this.getActivity(), moviesList);
        binding.rvMovies.setLayoutManager(new LinearLayoutManager(this.getContext()));
        this.binding.rvMovies.addItemDecoration(new DividerItemDecoration(this.getContext(),DividerItemDecoration.VERTICAL));
        binding.rvMovies.setAdapter(this.adapter);

        this.api = RetrofitClient.getInstance().getApi();
        Call<ResponseObject> request = api.getItems();
        request.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                if(response.isSuccessful() ==false){
                    Log.d(TAG,"Error code: "+response.code());
                    return;
                }

                ResponseObject obj = response.body();
                itemsFromAPI = obj.getResults();
                Log.d(TAG,"get: "+itemsFromAPI.size());

                moviesList.clear();
                moviesList.addAll(itemsFromAPI);
                adapter.notifyDataSetChanged();

                for (int i = 0;i<itemsFromAPI.size();i++){
                    Movie currentItem = itemsFromAPI.get(i);
                    Log.d(TAG,currentItem.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                Log.d(TAG,t.getMessage());
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




}

