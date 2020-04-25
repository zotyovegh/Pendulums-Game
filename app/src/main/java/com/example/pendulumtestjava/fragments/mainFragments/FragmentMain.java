package com.example.pendulumtestjava.fragments.mainFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pendulumtestjava.R;
import com.example.pendulumtestjava.doublePendulum.DoublePModel;
import com.example.pendulumtestjava.doublePendulum.DoublePendulum;
import com.example.pendulumtestjava.singlePendulum.SinglePModel;
import com.example.pendulumtestjava.singlePendulum.SinglePendulum;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class FragmentMain extends Fragment {

    private static final int MY_REQUEST_CODE = 7117;
    private SinglePModel dataS = SinglePModel.getInstance();
    private DoublePModel dataD = DoublePModel.getInstance();
    List<AuthUI.IdpConfig> providers;
    Button logout;
    
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
                AuthUI.getInstance()
                        .signOut(getActivity())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                logout.setEnabled(false);
                                showSignOptions();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        providers = Arrays.asList(
            new AuthUI.IdpConfig.EmailBuilder().build(),
            new AuthUI.IdpConfig.GoogleBuilder().build()    
        );

        showSignOptions();
        
        return v;
    }

    private void showSignOptions() {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(providers)
                        .setLogo(R.drawable.loginlogo)
                .setTheme(R.style.LogInTheme)
                .build(), MY_REQUEST_CODE
        );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == MY_REQUEST_CODE)
        {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if(resultCode == RESULT_OK)
            {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                Toast.makeText(getActivity(), "" + user.getEmail(), Toast.LENGTH_SHORT).show();
                logout.setEnabled(true);
            }else{
                Toast.makeText(getActivity(), "" + response.getError().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
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
