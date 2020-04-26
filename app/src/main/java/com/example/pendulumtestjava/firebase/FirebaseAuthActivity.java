package com.example.pendulumtestjava.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.pendulumtestjava.MainActivity;
import com.example.pendulumtestjava.R;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.auth.api.Auth;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirebaseAuthActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 101;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_auth);

        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() != null)
        {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }else{
            authenticateUser();
        }
    }

    private void authenticateUser() {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(getProviderList())
                .setIsSmartLockEnabled(false)
                        .setLogo(R.drawable.loginlogo)
                .build(),
                REQUEST_CODE);
    }

    private List<AuthUI.IdpConfig> getProviderList() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build()
        );
        return  providers;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IdpResponse response = IdpResponse.fromResultIntent(data);

        if (requestCode == REQUEST_CODE) {

            if (resultCode == RESULT_OK) {
                startActivity(new Intent(this, MainActivity.class));
                return;
            }
        } else {
            if (response == null) {
                // User cancelled Sign-in
                return;
            }

            if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                // Device has no network connection
                return;
            }

            if (response.getError().getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                // Unknown error occurred
                return;
            }
        }
    }
}
