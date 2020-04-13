package com.example.pendulumtestjava;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity{

    private Button doublePendulum, singlePendulum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        singlePendulum = (Button) findViewById(R.id.singlePendulum);
        singlePendulum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSinglePendulumActivity();
            }
        });
        doublePendulum = (Button) findViewById(R.id.doublePendulum);
        doublePendulum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDoublePendulumActivity();
            }
        });

    }

    public void openDoublePendulumActivity()
    {
        Intent intent = new Intent(this, DoublePendulum.class);
        startActivity(intent);
    }

    public void openSinglePendulumActivity()
    {
        Intent intent = new Intent(this, SinglePendulum.class);
        startActivity(intent);
    }
}
