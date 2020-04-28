package com.example.pendulumtestjava.fragments.pendulumsFragments;

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
import com.example.pendulumtestjava.fragments.pendulumsFragments.models.DoublePendulumModel;
import com.example.pendulumtestjava.fragments.pendulumsFragments.views.DoublePendulumView;
import com.example.pendulumtestjava.fragments.pendulumsFragments.models.SinglePendulumModel;
import com.example.pendulumtestjava.fragments.pendulumsFragments.views.SinglePendulumView;

public class FragmentMain extends Fragment {

    private SinglePendulumModel dataS = SinglePendulumModel.getInstance();
    private DoublePendulumModel dataD = DoublePendulumModel.getInstance();



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        Button singlePendulum = v.findViewById(R.id.singlePendulum);
        singlePendulum.setOnClickListener(v1 -> openSinglePendulumActivity());
        Button doublePendulum = v.findViewById(R.id.doublePendulum);
        doublePendulum.setOnClickListener(v12 -> openDoublePendulumActivity());


        return v;
    }

    private void openDoublePendulumActivity()
    {
        Intent intent = new Intent(getActivity(), DoublePendulumView.class);
        dataD.resetValues();
        startActivity(intent);
    }

    private void openSinglePendulumActivity()
    {
        Intent intent = new Intent(getActivity(), SinglePendulumView.class);
        dataS.resetValues();
        startActivity(intent);
    }
}
