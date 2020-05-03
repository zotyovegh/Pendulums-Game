package com.example.pendulumtestjava.fragments.savingsFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pendulumtestjava.R;
import com.example.pendulumtestjava.fragments.savingsFragment.doubleP.DoublePObject;
import com.example.pendulumtestjava.fragments.savingsFragment.shared.DbViewModel;
import com.example.pendulumtestjava.fragments.savingsFragment.shared.PendulumAdapter;
import com.example.pendulumtestjava.fragments.savingsFragment.savedObject.SavePendulumModel;
import com.example.pendulumtestjava.fragments.savingsFragment.singleP.SinglePObject;
import com.github.clans.fab.FloatingActionButton;

import java.util.Objects;

public class FragmentList extends Fragment {
    private DbViewModel viewModel;
    PendulumAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_list, container, false);

        trashFloatingButton(v);

        RecyclerView recyclerView = v.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        adapter = new PendulumAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(DbViewModel.class);
        viewModel.getAllPendulums().observe(getActivity(), adapter::submitList);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                SavePendulumModel pendulum = adapter.getPendulumAt(viewHolder.getAdapterPosition());
                if (pendulum.getType().equals("Single")) {
                    SinglePObject p = viewModel.getSinglePendulum(pendulum.getId());
                    viewModel.deleteSingleP(p);
                    Toast.makeText(getActivity(), "Pendulum deleted", Toast.LENGTH_SHORT).show();
                } else if (pendulum.getType().equals("Double")) {
                    DoublePObject p = viewModel.getDoublePendulum(pendulum.getId());
                    viewModel.deleteDoubleP(p);
                    Toast.makeText(getActivity(), "Pendulum deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
                }
            }
        }).attachToRecyclerView(recyclerView);

        viewModel.openPendulum(adapter, getActivity());

        return v;
    }

    private void trashFloatingButton(View v) {
        FloatingActionButton deleteSingle = v.findViewById(R.id.deleteSingle);
        FloatingActionButton deleteDouble = v.findViewById(R.id.deleteDouble);
        FloatingActionButton deleteAll = v.findViewById(R.id.deleteAll);

        deleteSingle.setOnClickListener(v12 -> {
            viewModel.deleteAllSinglePendulums();
            Toast.makeText(getActivity(), "All Single Pendulums deleted", Toast.LENGTH_SHORT).show();
        });
        deleteDouble.setOnClickListener(v13 -> {
            viewModel.deleteAllDoublePendulums();
            Toast.makeText(getActivity(), "All Double Pendulums deleted", Toast.LENGTH_SHORT).show();
        });
        deleteAll.setOnClickListener(v14 -> {
            viewModel.deleteAllPendulums();
            Toast.makeText(getActivity(), "All Pendulums deleted", Toast.LENGTH_SHORT).show();
        });
    }

}
