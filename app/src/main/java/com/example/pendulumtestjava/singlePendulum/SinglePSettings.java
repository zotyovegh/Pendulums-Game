package com.example.pendulumtestjava.singlePendulum;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.pendulumtestjava.R;

import yuku.ambilwarna.AmbilWarnaDialog;

public class SinglePSettings extends AppCompatDialogFragment {
    private Button traceColorButton, ballColorButton;
    private int traceDefaultColor, ballDefaultColor;
    SinglePData data = SinglePData.getInstance();
    private EditText a, r, g, damp, trace;
    private Switch switch1;
    private CheckBox checkBox;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.layout_singlepsettings, null);

        a = (EditText) view.findViewById(R.id.a);
        double temp = Math.toDegrees(data.getA());
        a.setText(String.format("%.0f", temp));
        r = (EditText) view.findViewById(R.id.r);
        r.setText(String.format("%.0f", data.getR()));
        g = (EditText) view.findViewById(R.id.g);
        g.setText(String.format("%.1f", data.getGravity()));
        damp = (EditText) view.findViewById(R.id.damp);
        damp.setText(String.format("%.4f", data.getDamping()));
        trace = (EditText) view.findViewById(R.id.trace);
        trace.setText(String.valueOf(data.getTrace()));
        switch1 = (Switch) view.findViewById(R.id.switch1);
        switch1.setChecked(data.isTraceOn());
        checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        checkBox.setChecked(data.isEndlessTrace());

        if(data.isTraceOn() == false)
        {
            switch1.setChecked(false);
        } else {
            switch1.setChecked(true);
        }

        traceDefaultColor = data.getTraceDrawColor();
        ballDefaultColor = data.getBallDrawColor();

        traceColorButton = (Button) view.findViewById(R.id.traceColorButton);
        traceColorButton.setBackgroundColor(data.getTraceDrawColor());
        traceColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker();
            }
        });

        ballColorButton = (Button) view.findViewById(R.id.ballColorButton);
        ballColorButton.setBackgroundColor(data.getBallDrawColor());
        ballColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker2();
            }
        });

        builder.setView(view)
                .setTitle("Settings")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                data.setA(Double.parseDouble(a.getText().toString()));
                                data.setR(Double.parseDouble(r.getText().toString()));
                                data.setGravity((float)Double.parseDouble(g.getText().toString()));
                                data.setDamping((float)Double.parseDouble(damp.getText().toString()));
                                if(switch1.isChecked()) {
                                    data.setTrace((int) Double.parseDouble(trace.getText().toString()));
                                    data.setTraceOn(true);
                                    data.setEndlessTrace(checkBox.isChecked());
                                }else {
                                    data.setTraceOn(false);
                                    data.setEndlessTrace(checkBox.isChecked());
                                }
                                data.setTraceDrawColor(traceDefaultColor);
                                data.setBallDrawColor(ballDefaultColor);

                            }
                        }
                )
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        }
                );
        return builder.create();
    }

    public void openColorPicker() {
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

    public void openColorPicker2() {
        AmbilWarnaDialog colorPicker2 = new AmbilWarnaDialog(getActivity(), ballDefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {}

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                ballDefaultColor = color;
                ballColorButton.setBackgroundColor(ballDefaultColor);
            }
        });
        colorPicker2.show();
    }
}
