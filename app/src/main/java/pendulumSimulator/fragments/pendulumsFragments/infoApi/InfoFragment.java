package pendulumSimulator.fragments.pendulumsFragments.infoApi;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.pendulumtestjava.R;

import java.util.Objects;

public class InfoFragment extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.info_api_layout, null);

        Bundle bundle = this.getArguments();

        String type = bundle.getString("type", "default");





        builder.setView(view)
                .setTitle("Description of " + type + " pendulum");
//                .setPositiveButton("OK",
//                        (dialog, whichButton) -> {
//
//                        }
//                )
//                .setNegativeButton("Cancel",
//                        (dialog, whichButton) -> dialog.dismiss()
//                );
        return builder.create();
    }
}
