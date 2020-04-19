package com.example.pendulumtestjava.main.listFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pendulumtestjava.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentList extends Fragment {
    View v;
    private SinglePViewModel singlePViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.list_fragment, container, false);

        RecyclerView recyclerView = v.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        SinglePAdapter adapter = new SinglePAdapter();
        recyclerView.setAdapter(adapter);

        singlePViewModel = ViewModelProviders.of(getActivity()).get(SinglePViewModel.class);
        singlePViewModel.getAllPendulums().observe(getActivity(), new Observer<List<SinglePendulumObject>>() {
            @Override
            public void onChanged(List<SinglePendulumObject> singlePendulumObjects) {
                //update recyclerView below
//                Toast.makeText(getActivity(), "onChanged", Toast.LENGTH_SHORT).show();
                adapter.setSinglePendulums(singlePendulumObjects);
            }
        });

        return v;
    }
}
