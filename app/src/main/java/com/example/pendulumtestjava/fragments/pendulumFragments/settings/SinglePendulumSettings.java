package com.example.pendulumtestjava.fragments.pendulumFragments.settings;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.pendulumtestjava.R;
import com.example.pendulumtestjava.fragments.connection.RandomizerRepository;
import com.example.pendulumtestjava.fragments.pendulumFragments.models.SinglePendulumModel;

import java.util.Objects;

import yuku.ambilwarna.AmbilWarnaDialog;

public class SinglePendulumSettings extends AppCompatDialogFragment {
    private SinglePendulumModel data = SinglePendulumModel.getInstance();
    private Button traceColorButton, ballColorButton;
    private TextView aNum, rNum, gNum, dampNum, traceNum;
    private int traceDefaultColor, ballDefaultColor;
    private SeekBar a, r, g, damp, trace;
    private static int TRACEMAX = 101;
    private RandomizerRepository randomizerRepository;

    @SuppressLint({"SetTextI18n", "DefaultLocale", "InflateParams"})
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.settings_singlep, null);
        randomizerRepository = RandomizerRepository.getInstance();

        a = view.findViewById(R.id.aSeekBar);
        r = view.findViewById(R.id.rSeekBar);
        g = view.findViewById(R.id.gSeekBar);
        damp = view.findViewById(R.id.dampSeekBar);
        trace = view.findViewById(R.id.traceSeekBar);

        a.setProgress((int) Math.toDegrees(data.getA()));
        r.setProgress((int) data.getR());
        g.setProgress((int) (data.getGravity() * 1000));
        damp.setProgress((int) (data.getDamping() * 10000));
        trace.setProgress(data.getTrace());

        aNum = view.findViewById(R.id.aNum);
        aNum.setText(String.format("%.0f", Math.toDegrees(data.getA())));
        rNum = view.findViewById(R.id.rNum);
        rNum.setText(String.format("%.0f", data.getR()));
        gNum = view.findViewById(R.id.gNum);
        gNum.setText(String.format("%.2f", data.getGravity() * 10));
        dampNum = view.findViewById(R.id.dampNum);
        dampNum.setText(String.format("%.4f", data.getDamping()));
        traceNum = view.findViewById(R.id.traceNum);
        if (!data.isTraceOn()) {
            traceNum.setText("Off");
            trace.setProgress(30);
        } else if (data.isEndlessTrace()) {
            traceNum.setText("Endless");
            trace.setProgress(TRACEMAX);
        } else {
            traceNum.setText(String.valueOf(data.getTrace()));
        }

        a.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                aNum.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        r.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
        g.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                gNum.setText(Float.toString((float) (progress * 0.01)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        damp.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                dampNum.setText(Float.toString((float) (progress * 0.0001)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        trace.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 30) {
                    traceNum.setText("Off");
                } else if (progress == TRACEMAX) {
                    traceNum.setText("Endless");
                } else {
                    traceNum.setText(String.valueOf(progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

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
                            data.setA(a.getProgress());
                            data.setR(r.getProgress());
                            data.setGravity((float) (g.getProgress() * 0.01));
                            data.setDamping((float) (damp.getProgress() * 0.0001));
                            if (trace.getProgress() == 30) {
                                data.setTraceOn(false);
                                data.setEndlessTrace(false);
                            } else if (trace.getProgress() == TRACEMAX) {
                                data.setTraceOn(true);
                                data.setEndlessTrace(true);
                            } else {
                                data.setTrace(trace.getProgress());
                                data.setTraceOn(true);
                                data.setEndlessTrace(false);
                            }
                            data.setTraceDrawColor(traceDefaultColor);
                            data.setBallDrawColor(ballDefaultColor);
                        }
                )
                .setNeutralButton("Randomize", (dialog, which) -> {
                })
                .setNegativeButton("Cancel",
                        (dialog, whichButton) -> dialog.dismiss()
                );
        return builder.create();
    }

    private void openColorPicker() {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(getActivity(), traceDefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
            }

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

    @Override
    public void onResume() {
        super.onResume();
        final AlertDialog d = (AlertDialog) getDialog();
        if (d != null) {
            Button positiveButton = d.getButton(Dialog.BUTTON_NEUTRAL);
            positiveButton.setOnClickListener(v -> {
                randomizerRepository.requestSingleRandom();
            });
        }
    }
}
