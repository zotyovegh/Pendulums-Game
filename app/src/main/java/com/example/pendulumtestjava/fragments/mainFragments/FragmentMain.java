package com.example.pendulumtestjava.fragments.mainFragments;

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
import com.example.pendulumtestjava.doublePendulum.DoublePData;
import com.example.pendulumtestjava.doublePendulum.DoublePendulum;
import com.example.pendulumtestjava.singlePendulum.SinglePData;
import com.example.pendulumtestjava.singlePendulum.SinglePendulum;

public class FragmentMain extends Fragment {

    private SinglePData dataS = SinglePData.getInstance();
    private DoublePData dataD = DoublePData.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment, container, false);

        Button singlePendulum = v.findViewById(R.id.singlePendulum);
        singlePendulum.setOnClickListener(v1 -> openSinglePendulumActivity());
        Button doublePendulum = v.findViewById(R.id.doublePendulum);
        doublePendulum.setOnClickListener(v12 -> openDoublePendulumActivity());

        return v;
    }

    private void openDoublePendulumActivity()
    {
        Intent intent = new Intent(getActivity(), DoublePendulum.class);
        dataD.resetValues();
        startActivity(intent);
    }

    private void openSinglePendulumActivity()
    {
        Intent intent = new Intent(getActivity(), SinglePendulum.class);
        dataS.resetValues();
        startActivity(intent);
    }
}
