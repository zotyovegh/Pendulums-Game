package com.example.pendulumtestjava.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pendulumtestjava.R;
import com.example.pendulumtestjava.doublePendulum.DoublePendulum;
import com.example.pendulumtestjava.singlePendulum.SinglePendulum;

public class FragmentMain extends Fragment {

    View v;
    private Button doublePendulum, singlePendulum;
    public FragmentMain(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.main_fragment, container,false);

        singlePendulum = (Button) v.findViewById(R.id.singlePendulum);
        singlePendulum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSinglePendulumActivity();
            }
        });
        doublePendulum = (Button) v.findViewById(R.id.doublePendulum);
        doublePendulum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDoublePendulumActivity();
            }
        });


        return v;
    }







    public void openDoublePendulumActivity()
    {
        Intent intent = new Intent(getActivity(), DoublePendulum.class);
        startActivity(intent);
    }

    public void openSinglePendulumActivity()
    {
        Intent intent = new Intent(getActivity(), SinglePendulum.class);
        startActivity(intent);
    }
}
