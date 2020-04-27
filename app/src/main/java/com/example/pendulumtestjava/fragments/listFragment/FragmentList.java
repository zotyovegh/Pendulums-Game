package com.example.pendulumtestjava.fragments.listFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pendulumtestjava.R;
import com.example.pendulumtestjava.doublePendulum.DoublePendulumView;
import com.example.pendulumtestjava.fragments.listFragment.doubleP.DoublePObject;
import com.example.pendulumtestjava.fragments.listFragment.shared.DbViewModel;
import com.example.pendulumtestjava.fragments.listFragment.shared.PendulumAdapter;
import com.example.pendulumtestjava.fragments.listFragment.shared.SavePendulumModel;
import com.example.pendulumtestjava.fragments.listFragment.singleP.SinglePObject;
import com.example.pendulumtestjava.singlePendulum.SinglePendulumView;

import java.util.Objects;

public class FragmentList extends Fragment {
    private DbViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_fragment, container, false);

        RecyclerView recyclerView = v.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        PendulumAdapter adapter = new PendulumAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(DbViewModel.class);
        viewModel.getAllPendulums().observe(getActivity(), pendulums -> adapter.submitList(pendulums));

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                SavePendulumModel pendulum = adapter.getPendulumAt(viewHolder.getAdapterPosition());
                if(pendulum.getType().equals("Single"))
                {
                    SinglePObject p = viewModel.getSinglePendulum(pendulum.getId());
                    viewModel.deleteSingleP(p);
                    Toast.makeText(getActivity(), "Pendulum deleted", Toast.LENGTH_SHORT).show();
                }
                else if(pendulum.getType().equals("Double"))
                {
                    DoublePObject p = viewModel.getDoublePendulum(pendulum.getId());
                    viewModel.deleteDoubleP(p);
                    Toast.makeText(getActivity(), "Pendulum deleted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
                }
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(pendulum -> {
            if(pendulum.getType().equals("Single"))
            {
                SinglePObject p = viewModel.getSinglePendulum(pendulum.getId());
                Intent intent = new Intent(getActivity(), SinglePendulumView.class);

                viewModel.installSinglePendulum(p);

                String type = "single";
                intent.putExtra("path", type);
                startActivity(intent);
            }
            else if(pendulum.getType().equals("Double"))
            {
                DoublePObject p = viewModel.getDoublePendulum(pendulum.getId());
                viewModel.installDoublePendulum(p);

                Intent intent = new Intent(getActivity(), DoublePendulumView.class);
                String type = "double";
                intent.putExtra("path", type);
                startActivity(intent);
            }
        });

        return v;
    }
}
