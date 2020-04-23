package com.example.pendulumtestjava.doublePendulum;

import android.app.Dialog;
import android.content.DialogInterface;
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

import java.util.Objects;

import yuku.ambilwarna.AmbilWarnaDialog;

public class DoublePSettings extends AppCompatDialogFragment {
    private Button ballColor1, ballColor2, traceColor1, traceColor2;
    private int trace1DefaultColor, trace2DefaultColor, ball1DefaultColor, ball2DefaultColor;
    private Switch trace1Switch, trace2Switch;
    private CheckBox checkBoxTrace1, checkBoxTrace2;
    private EditText a1, a2, r1, r2, g, m1, m2, trace1, trace2;
    private DoublePData data = DoublePData.getInstance();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.layout_doublepsettings, null);

        a1 = view.findViewById(R.id.a1);
        double temp1a = Math.toDegrees(data.getA1());
        a1.setText(String.format("%.0f", temp1a));
        a2 = view.findViewById(R.id.a2);
        double temp2a = Math.toDegrees(data.getA2());
        a2.setText(String.format("%.0f", temp2a));

        r1 = view.findViewById(R.id.r1);
        r1.setText(String.format("%.0f", data.getR1()));
        r2 = view.findViewById(R.id.r2);
        r2.setText(String.format("%.0f", data.getR2()));
        g = view.findViewById(R.id.g);
        g.setText(String.format("%.1f", data.getG()));

        m1 = view.findViewById(R.id.m1);
        m1.setText(String.format("%.0f", data.getM1()));
        m2 = view.findViewById(R.id.m2);
        m2.setText(String.format("%.0f", data.getM2()));

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
                            data.setA1(Double.parseDouble(a1.getText().toString()));
                            data.setA2(Double.parseDouble(a2.getText().toString()));
                            data.setR1(Double.parseDouble(r1.getText().toString()));
                            data.setR2(Double.parseDouble(r2.getText().toString()));
                            data.setG((float) Double.parseDouble(g.getText().toString()));
                            data.setM1(Double.parseDouble(m1.getText().toString()));
                            data.setM2(Double.parseDouble(m2.getText().toString()));
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

    public void ball1ColorPicker() {
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

    public void ball2ColorPicker() {
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

    public void trace1ColorPicker() {
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

    public void trace2ColorPicker() {
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
