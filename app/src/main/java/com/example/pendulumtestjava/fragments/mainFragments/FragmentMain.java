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
import com.example.pendulumtestjava.doublePendulum.DoublePendulumModel;
import com.example.pendulumtestjava.doublePendulum.DoublePendulumView;
import com.example.pendulumtestjava.firebase.FirebaseAuthActivity;
import com.example.pendulumtestjava.singlePendulum.SinglePendulumModel;
import com.example.pendulumtestjava.singlePendulum.SinglePendulumView;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class FragmentMain extends Fragment {

    private SinglePendulumModel dataS = SinglePendulumModel.getInstance();
    private DoublePendulumModel dataD = DoublePendulumModel.getInstance();
    private Button logout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment, container, false);

        Button singlePendulum = v.findViewById(R.id.singlePendulum);
        singlePendulum.setOnClickListener(v1 -> openSinglePendulumActivity());
        Button doublePendulum = v.findViewById(R.id.doublePendulum);
        doublePendulum.setOnClickListener(v12 -> openDoublePendulumActivity());
        logout = v.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut(v);
            }
        });

        return v;
    }

    public void signOut(View view) {
        AuthUI.getInstance()
                .signOut(getActivity())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(
                                    getActivity(),
                                    FirebaseAuthActivity.class));
                            getActivity().finish();
                        } else {
                            // Report error to user
                        }
                    }
                });
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
