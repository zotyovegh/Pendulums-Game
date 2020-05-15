package com.example.pendulumtestjava.fragments.pendulumFragments.settings;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.pendulumtestjava.R;
import com.example.pendulumtestjava.fragments.pendulumFragments.models.SinglePendulumModel;

import java.util.Objects;

import yuku.ambilwarna.AmbilWarnaDialog;

public class SinglePendulumSettings extends AppCompatDialogFragment {
    private Button traceColorButton, ballColorButton;
    private int traceDefaultColor, ballDefaultColor;
    private SinglePendulumModel data = SinglePendulumModel.getInstance();
    private EditText a, r, g, damp, trace;
    private Switch switch1;
    private CheckBox checkBox;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.settings_singlep, null);

        a = view.findViewById(R.id.a);
        double temp = Math.toDegrees(data.getA());
        a.setText(String.format("%.0f", temp));
        r = view.findViewById(R.id.r);
        r.setText(String.format("%.0f", data.getR()));
        g = view.findViewById(R.id.g);
        g.setText(String.format("%.1f", data.getGravity()));
        damp = view.findViewById(R.id.damp);
        damp.setText(String.format("%.4f", data.getDamping()));
        trace = view.findViewById(R.id.trace);
        trace.setText(String.valueOf(data.getTrace()));
        switch1 = view.findViewById(R.id.switch1);
        switch1.setChecked(data.isTraceOn());
        checkBox = view.findViewById(R.id.checkBox);
        checkBox.setChecked(data.isEndlessTrace());

        if(!data.isTraceOn())
        {
            switch1.setChecked(false);
        } else {
            switch1.setChecked(true);
        }

        traceDefaultColor = data.getTraceDrawColor();
        ballDefaultColor = data.getBallDrawColor();

        traceColorButton = view.findViewById(R.id.traceColorButton);
        traceColorButton.setBackgroundColor(data.getTraceDrawColor());
        traceColorButton.setOnClickListener(v -> openColorPicker());

        ballColorButton = view.findViewById(R.id.ballColorButton);
        ballColorButton.setBackgroundColor(data.getBallDrawColor());
        ballColorButton.setOnClickListener(v -> openColorPicker2());

        builder.setView(view)
                .setTitle("Settings")
                .setPositiveButton("OK",
                        (dialog, whichButton) -> {
                            data.setA(Double.parseDouble(a.getText().toString()));
                            data.setR(Double.parseDouble(r.getText().toString()));

                            data.setGravity(Float.parseFloat((g.getText().toString()).replace(',','.')));
                            data.setDamping(Float.parseFloat((damp.getText().toString()).replace(',','.')));

                            if(switch1.isChecked()) {
                                data.setTrace(Integer.parseInt(trace.getText().toString()));
                                data.setTraceOn(true);
                                data.setEndlessTrace(checkBox.isChecked());
                            }else {
                                data.setTraceOn(false);
                                data.setEndlessTrace(checkBox.isChecked());
                            }
                            data.setTraceDrawColor(traceDefaultColor);
                            data.setBallDrawColor(ballDefaultColor);
                        }
                )
                .setNegativeButton("Cancel",
                        (dialog, whichButton) -> dialog.dismiss()
                );
        return builder.create();
    }

    private void openColorPicker() {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(getActivity(), traceDefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {}

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                traceDefaultColor = color;
                traceColorButton.setBackgroundColor(traceDefaultColor);
            }
        });
        colorPicker.show();
    }

    private void openColorPicker2() {
        AmbilWarnaDialog colorPicker2 = new AmbilWarnaDialog(getActivity(), ballDefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                ballDefaultColor = color;
                ballColorButton.setBackgroundColor(ballDefaultColor);
            }
        });
        colorPicker2.show();
    }
}
