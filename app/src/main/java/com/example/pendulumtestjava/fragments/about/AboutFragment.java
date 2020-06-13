package com.example.pendulumtestjava.fragments.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pendulumtestjava.R;

public class AboutFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about, container, false);

        TextView github = v.findViewById(R.id.github);
        TextView linkedIn = v.findViewById(R.id.linkedIn);
        github.setOnClickListener(v1 -> {
            openLink("https://github.com/zotyovegh/PendulumAndroid");
        });
        linkedIn.setOnClickListener(v12 -> {
            openLink("https://www.linkedin.com/in/zoltan-vegh/");
        });

        return v;
    }

    public void openLink(String url)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
