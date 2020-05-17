package com.example.pendulumtestjava.fragments.pendulumFragments.settings;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

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
    private EditText r, g, damp, trace;
    private Switch switch1;
    private CheckBox checkBox;
    private SeekBar aSeekBar, rSeekBar, gSeekBar, dampSeekBar, traceSeekBar;
    private TextView aNum, rNum, gNum, dampNum, traceNum;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.settings_singlep, null);

        aSeekBar = view.findViewById(R.id.aSeekBar);
        rSeekBar = view.findViewById(R.id.rSeekBar);
        gSeekBar = view.findViewById(R.id.gSeekBar);
        dampSeekBar = view.findViewById(R.id.dampSeekBar);
        traceSeekBar = view.findViewById(R.id.traceSeekBar);

        aSeekBar.setProgress((int)Math.toDegrees(data.getA()));
        rSeekBar.setProgress((int)data.getR());
        gSeekBar.setProgress( (int)(data.getGravity()*1000));
        dampSeekBar.setProgress((int)(data.getDamping()*10000));
        traceSeekBar.setProgress(data.getTrace());

        aNum = view.findViewById(R.id.aNum);
        aNum.setText(String.format("%.0f",Math.toDegrees(data.getA())));
        rNum = view.findViewById(R.id.rNum);
        rNum.setText(String.format("%.0f",data.getR()));
        gNum = view.findViewById(R.id.gNum);
        gNum.setText(String.format("%.2f", data.getGravity()*10));
        dampNum = view.findViewById(R.id.dampNum);
        dampNum.setText(String.format("%.4f", data.getDamping()));
//        traceNum = view.findViewById(R.id.traceNum);
//        traceNum.setText(String.format("%.0f", data.getTrace()));
//
        aSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                aNum.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        rSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rNum.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        gSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                gNum.setText(Float.toString((float)(progress * 0.01)));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        dampSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                dampNum.setText(Float.toString((float)(progress * 0.0001)));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
//
//        traceSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//
//            }
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//            }
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//            }
//        });

        r = view.findViewById(R.id.r);
        r.setText(String.format("%.0f", data.getR()));
        g = view.findViewById(R.id.g);
        g.setText(String.format("%.2f", data.getGravity()));
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
                            data.setA(aSeekBar.getProgress());
                            data.setR(rSeekBar.getProgress());
                            data.setGravity((float)(gSeekBar.getProgress() * 0.01));
                            data.setDamping((float)(dampSeekBar.getProgress() * 0.0001));

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
