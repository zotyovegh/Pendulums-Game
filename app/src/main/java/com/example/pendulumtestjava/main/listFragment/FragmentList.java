package com.example.pendulumtestjava.main.listFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pendulumtestjava.R;
import com.example.pendulumtestjava.main.listFragment.shared.DbViewModel;
import com.example.pendulumtestjava.main.listFragment.shared.PendulumAdapter;
import com.example.pendulumtestjava.main.listFragment.shared.SaveObjectModel;
import com.example.pendulumtestjava.singlePendulum.SinglePData;
import com.example.pendulumtestjava.singlePendulum.SinglePendulum;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FragmentList extends Fragment {
    View v;
    SinglePData data = SinglePData.getInstance();
    private DbViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.list_fragment, container, false);

        RecyclerView recyclerView = v.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        PendulumAdapter adapter = new PendulumAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(getActivity()).get(DbViewModel.class);
        viewModel.getAllPendulums().observe(getActivity(), new Observer<List<SaveObjectModel>>() {
            @Override
            public void onChanged(List<SaveObjectModel> pendulums) {
                adapter.setPendulums(pendulums);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if(adapter.getPendulumAt(viewHolder.getAdapterPosition()).getType().equals("Single"))
                {
                    Toast.makeText(getActivity(), "s", Toast.LENGTH_SHORT).show();

                }
                else if(adapter.getPendulumAt(viewHolder.getAdapterPosition()).getType().equals("Double"))
                {
                    Toast.makeText(getActivity(), "d", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
                }
            }
        }).attachToRecyclerView(recyclerView);


        return v;
    }


}
