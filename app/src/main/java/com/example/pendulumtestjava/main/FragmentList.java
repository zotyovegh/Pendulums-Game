package com.example.pendulumtestjava.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pendulumtestjava.R;

import java.util.ArrayList;

public class FragmentList extends Fragment {
    View v;
    RecyclerView list;
    SavedPendulumAdapter pendulumAdapter;



    public FragmentList() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.list_fragment, container, false);

        list = v.findViewById(R.id.rv);
        list.hasFixedSize();
        list.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<TempListItem> items = new ArrayList<>();
        items.add(new TempListItem("1", R.drawable.ball));
        items.add(new TempListItem("2", R.drawable.ball));
        items.add(new TempListItem("3", R.drawable.ball));
        items.add(new TempListItem("4", R.drawable.ball));
        items.add(new TempListItem("5", R.drawable.ball));
        items.add(new TempListItem("6", R.drawable.ball));
        items.add(new TempListItem("7", R.drawable.ball));
        items.add(new TempListItem("8", R.drawable.ball));
        items.add(new TempListItem("9", R.drawable.ball));
        items.add(new TempListItem("10", R.drawable.ball));
        items.add(new TempListItem("11", R.drawable.ball));
        items.add(new TempListItem("12", R.drawable.ball));
        items.add(new TempListItem("13", R.drawable.ball));
        items.add(new TempListItem("14", R.drawable.ball));
        items.add(new TempListItem("15", R.drawable.ball));
        items.add(new TempListItem("16", R.drawable.ball));
        items.add(new TempListItem("17", R.drawable.ball));
        items.add(new TempListItem("18", R.drawable.ball));
        items.add(new TempListItem("19", R.drawable.ball));
        items.add(new TempListItem("20", R.drawable.ball));
        items.add(new TempListItem("21", R.drawable.ball));
        items.add(new TempListItem("22", R.drawable.ball));



        pendulumAdapter = new SavedPendulumAdapter(items);
        list.setAdapter(pendulumAdapter);

        return v;
    }
}
