package com.example.pendulumtestjava.fragments.pendulumFragments.settings;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
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
import com.example.pendulumtestjava.fragments.pendulumFragments.models.DoublePendulumModel;

import java.util.Objects;

import yuku.ambilwarna.AmbilWarnaDialog;

public class DoublePendulumSettings extends AppCompatDialogFragment {
    private Button ballColor1, ballColor2, traceColor1, traceColor2;
    private int trace1DefaultColor, trace2DefaultColor, ball1DefaultColor, ball2DefaultColor;
    private Switch trace1Switch, trace2Switch;
    private CheckBox checkBoxTrace1, checkBoxTrace2;
    private EditText trace1, trace2;
    private DoublePendulumModel data = DoublePendulumModel.getInstance();
    private SeekBar a1b, a2b, r1b, r2b, gb, m1b, m2b;
    private TextView a1num, a2num, r1num, r2num, gnum, m1num, m2num;

    @SuppressLint({"SetTextI18n", "DefaultLocale", "InflateParams"})
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.settings_doublep, null);

        a1b = view.findViewById(R.id.a1b);
        a2b = view.findViewById(R.id.a2b);
        r1b = view.findViewById(R.id.r1b);
        r2b = view.findViewById(R.id.r2b);
        gb = view.findViewById(R.id.gb);
        m1b = view.findViewById(R.id.m1b);
        m2b = view.findViewById(R.id.m2b);

        a1b.setProgress((int)Math.toDegrees(data.getA1()));
        a2b.setProgress((int)Math.toDegrees(data.getA2()));
        r1b.setProgress((int)data.getR1());
        r2b.setProgress((int)data.getR2());
        gb.setProgress((int)(data.getG()*1000));
        m1b.setProgress((int)data.getM1());
        m2b.setProgress((int)data.getM2());

        a1num = view.findViewById(R.id.a1num);
        a1num.setText(String.format("%.0f",Math.toDegrees(data.getA1())));
        a2num = view.findViewById(R.id.a2num);
        a2num.setText(String.format("%.0f",Math.toDegrees(data.getA2())));
        r1num = view.findViewById(R.id.r1num);
        r1num.setText(String.format("%.0f",data.getR1()));
        r2num = view.findViewById(R.id.r2num);
        r2num.setText(String.format("%.0f",data.getR2()));
        gnum = view.findViewById(R.id.gnum);
        gnum.setText(String.format("%.2f", data.getG()*10));
        m1num = view.findViewById(R.id.m1num);
        m1num.setText(String.format("%.0f",data.getM1()));
        m2num = view.findViewById(R.id.m2num);
        m2num.setText(String.format("%.0f",data.getM2()));

        a1b.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                a1num.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        a2b.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                a2num.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        r1b.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                r1num.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        r2b.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                r2num.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        gb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                gnum.setText(Float.toString((float)(progress * 0.01)));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        m1b.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                m1num.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        m2b.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                m2num.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        trace1 = view.findViewById(R.id.trace1);
        trace1.setText(String.valueOf(data.getTrace1()));
        trace2 = view.findViewById(R.id.trace2);
        trace2.setText(String.valueOf(data.getTrace2()));
        trace1Switch = view.findViewById(R.id.trace1Switch);
        trace1Switch.setChecked(data.isTrace1On());
        trace2Switch = view.findViewById(R.id.trace2Switch);
        trace2Switch.setChecked(data.isTrace2On());
        checkBoxTrace1 = view.findViewById(R.id.checkBoxTrace1);
        checkBoxTrace1.setChecked(data.isEndlessTrace1());
        checkBoxTrace2 = view.findViewById(R.id.checkBoxTrace2);
        checkBoxTrace2.setChecked(data.isEndlessTrace2());

        if (!data.isTrace1On()) {
            trace1Switch.setChecked(false);
        } else {
            trace1Switch.setChecked(true);
        }
        if (!data.isTrace2On()) {
            trace2Switch.setChecked(false);
        } else {
            trace2Switch.setChecked(true);
        }
        trace1DefaultColor = data.getTrace1Color();
        trace2DefaultColor = data.getTrace2Color();
        ball1DefaultColor = data.getBall1Color();
        ball2DefaultColor = data.getBall2Color();

        ballColor1 = view.findViewById(R.id.ballColor1);
        ballColor2 = view.findViewById(R.id.ballColor2);
        traceColor1 = view.findViewById(R.id.traceColor1);
        traceColor2 = view.findViewById(R.id.traceColor2);

        ballColor1.setBackgroundColor(data.getBall1Color());
        ballColor2.setBackgroundColor(data.getBall2Color());
        traceColor1.setBackgroundColor(data.getTrace1Color());
        traceColor2.setBackgroundColor(data.getTrace2Color());

        ballColor1.setOnClickListener(v -> ball1ColorPicker());
        ballColor2.setOnClickListener(v -> ball2ColorPicker());
        traceColor1.setOnClickListener(v -> trace1ColorPicker());
        traceColor2.setOnClickListener(v -> trace2ColorPicker());

        builder.setView(view)
                .setTitle("Settings")
                .setPositiveButton("OK",
                        (dialog, whichButton) -> {
                            data.setA1(a1b.getProgress());
                            data.setA2(a2b.getProgress());
                            data.setR1(r1b.getProgress());
                            data.setR2(r2b.getProgress());

                            data.setG((float)(gb.getProgress() * 0.01));
                            data.setM1(m1b.getProgress());
                            data.setM2(m2b.getProgress());

                            data.setBall1Color(ball1DefaultColor);
                            data.setBall2Color(ball2DefaultColor);
                            data.setTrace1Color(trace1DefaultColor);
                            data.setTrace2Color(trace2DefaultColor);

                            if (trace1Switch.isChecked()) {
                                data.setTrace1((int) Double.parseDouble(trace1.getText().toString()));
                                data.setTrace1On(true);
                                data.setEndlessTrace1(checkBoxTrace1.isChecked());
                            } else {
                                data.setTrace1On(false);
                                data.setEndlessTrace1(checkBoxTrace1.isChecked());
                            }
                            if (trace2Switch.isChecked()) {
                                data.setTrace2((int) Double.parseDouble(trace2.getText().toString()));
                                data.setTrace2On(true);
                                data.setEndlessTrace2(checkBoxTrace2.isChecked());
                            } else {
                                data.setTrace2On(false);
                                data.setEndlessTrace2(checkBoxTrace2.isChecked());
                            }
                        }
                ).setNegativeButton("Cancel", (dialog, whichButton) -> dialog.dismiss()
        );
        return builder.create();
    }

    private void ball1ColorPicker() {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(getActivity(), ball1DefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                ball1DefaultColor = color;
                ballColor1.setBackgroundColor(ball1DefaultColor);
            }
        });
        colorPicker.show();
    }

    private void ball2ColorPicker() {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(getActivity(), ball2DefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                ball2DefaultColor = color;
                ballColor2.setBackgroundColor(ball2DefaultColor);
            }
        });
        colorPicker.show();
    }

    private void trace1ColorPicker() {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(getActivity(), trace1DefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                trace1DefaultColor = color;
                traceColor1.setBackgroundColor(trace1DefaultColor);
            }
        });
        colorPicker.show();
    }

    private void trace2ColorPicker() {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(getActivity(), trace2DefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                trace2DefaultColor = color;
                traceColor2.setBackgroundColor(trace2DefaultColor);
            }
        });
        colorPicker.show();
    }
}
