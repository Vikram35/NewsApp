package com.choudhary.newsz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HealthFragment extends Fragment {


    String API_KEY = "b0a320f12ce6435db52d94dd968f5259";
    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<Model> modelArrayList;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_health,null);

        recyclerView = v.findViewById(R.id.heath_recylerview);
        modelArrayList = new ArrayList<>();
        adapter = new Adapter(getContext(),modelArrayList);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        getNews();


        return v;
    }


    void  getNews(){

        ApiUtilities.getApiInterface().getCategory("in","health",100,API_KEY).enqueue(new Callback<MainNews>() {
            @Override
            public void onResponse(Call<MainNews> call, Response<MainNews> response) {
                if (response.isSuccessful()){

                    modelArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MainNews> call, Throwable t) {

            }
        });
    }
}