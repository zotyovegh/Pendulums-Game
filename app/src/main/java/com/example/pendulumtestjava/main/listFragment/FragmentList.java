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
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pendulumtestjava.R;
import com.example.pendulumtestjava.doublePendulum.DoublePData;
import com.example.pendulumtestjava.doublePendulum.DoublePendulum;
import com.example.pendulumtestjava.main.listFragment.doubleP.DoublePObject;
import com.example.pendulumtestjava.main.listFragment.shared.DbViewModel;
import com.example.pendulumtestjava.main.listFragment.shared.PendulumAdapter;
import com.example.pendulumtestjava.main.listFragment.shared.SaveObjectModel;
import com.example.pendulumtestjava.main.listFragment.singleP.SinglePObject;
import com.example.pendulumtestjava.singlePendulum.SinglePData;
import com.example.pendulumtestjava.singlePendulum.SinglePendulum;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FragmentList extends Fragment {
    View v;
    private DbViewModel viewModel;
    private SinglePData dataS = SinglePData.getInstance();
    private DoublePData dataD = DoublePData.getInstance();


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
                adapter.submitList(pendulums);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                SaveObjectModel pendulum = adapter.getPendulumAt(viewHolder.getAdapterPosition());
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

        adapter.setOnItemClickListener(new PendulumAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SaveObjectModel pendulum) {
                if(pendulum.getType().equals("Single"))
                {
                    SinglePObject p = viewModel.getSinglePendulum(pendulum.getId());
                    Intent intent = new Intent(getActivity(), SinglePendulum.class);
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<Float>>(){}.getType();
                    dataS.setA(Math.toDegrees(p.getA()));
                    dataS.setR(p.getR());
                    dataS.setGravity(p.getG());
                    dataS.setDamping(p.getDamping());
                    dataS.setTrace(p.getTrace());
                    dataS.setBallDrawColor(p.getBallColor());
                    dataS.setTraceDrawColor(p.getTraceColor());
                    ArrayList<Float> temp = gson.fromJson(p.getPointsJson(), listType);
                    dataS.setPoints(temp);
                    dataS.setStop(true);
                    dataS.setEndlessTrace(p.isInfinity());
                    dataS.setTraceOn(p.isTraceOn());

                    String type = "single";
                    intent.putExtra("path", type);
                    startActivity(intent);
                }
                else if(pendulum.getType().equals("Double"))
                {
                    DoublePObject p = viewModel.getDoublePendulum(pendulum.getId());
                    Intent intent = new Intent(getActivity(), DoublePendulum.class);
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<Float>>(){}.getType();
                    dataD.setA1(Math.toDegrees(p.getA1()));
                    dataD.setA2(Math.toDegrees(p.getA2()));
                    dataD.setR1(p.getR1());
                    dataD.setR2(p.getR2());
                    dataD.setG(p.getG());
                    dataD.setM1(p.getM1());
                    dataD.setM2(p.getM2());
                    dataD.setTrace1(p.getTrace1());
                    dataD.setTrace2(p.getTrace2());
                    dataD.setTrace1Color(p.getTraceColor1());
                    dataD.setTrace2Color(p.getTraceColor2());
                    dataD.setBall1Color(p.getBallColor1());
                    dataD.setBall2Color(p.getBallColor2());
                    ArrayList<Float> json1 = gson.fromJson(p.getPoints1Json(), listType);
                    ArrayList<Float> json2 = gson.fromJson(p.getPoints2Json(), listType);
                    dataD.setPoints1(json1);
                    dataD.setPoints2(json2);
                    dataD.setEndlessTrace1(p.isEndlessTrace1());
                    dataD.setEndlessTrace2(p.isEndlessTrace2());
                    dataD.setTrace1On(p.isTrace1On());
                    dataD.setTrace2On(p.isTrace2On());
                    dataD.setStop(true);

                    String type = "double";
                    intent.putExtra("path", type);
                    startActivity(intent);
                }
            }
        });


        return v;
    }


}
