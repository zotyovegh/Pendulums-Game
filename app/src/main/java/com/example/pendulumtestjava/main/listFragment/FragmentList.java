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
import com.example.pendulumtestjava.singlePendulum.SinglePData;
import com.example.pendulumtestjava.singlePendulum.SinglePendulum;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FragmentList extends Fragment {
    View v;
    private SinglePViewModel singlePViewModel;
    SinglePData data = SinglePData.getInstance();

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

       new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
               ItemTouchHelper.LEFT) {
           @Override
           public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
               return false;
           }

           @Override
           public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                singlePViewModel.delete(adapter.getSinglePendulumAt(viewHolder.getAdapterPosition()));
               Toast.makeText(getActivity(), "Note deleted", Toast.LENGTH_SHORT).show();
           }
       }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new SinglePAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SinglePendulumObject pendulum) {
                Intent intent = new Intent(getActivity(), SinglePendulum.class);
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Float>>(){}.getType();
                data.setA(Math.toDegrees(pendulum.getA()));
                data.setR(pendulum.getR());
                data.setGravity(pendulum.getG());
                data.setDamping(pendulum.getDamping());
                data.setTrace(pendulum.getTrace());
                data.setBallDrawColor(pendulum.getBallColor());
                data.setTraceDrawColor(pendulum.getTraceColor());

                ArrayList<Float> temp = gson.fromJson(pendulum.getPointsJson(), listType);
                data.setPoints(temp);
                data.setStop(true);


                startActivity(intent);
            }
        });

        return v;
    }

    public void openSinglePendulumActivity()
    {
        Intent intent = new Intent(getActivity(), SinglePendulum.class);
        startActivity(intent);
    }

}
