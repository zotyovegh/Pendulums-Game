package com.example.pendulumtestjava.fragments.savingsFragment.shared;

import android.app.Application;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pendulumtestjava.R;
import com.example.pendulumtestjava.fragments.pendulumFragments.views.DoublePendulumView;
import com.example.pendulumtestjava.fragments.pendulumFragments.views.SinglePendulumView;
import com.example.pendulumtestjava.fragments.savingsFragment.doubleP.DoublePObject;
import com.example.pendulumtestjava.fragments.savingsFragment.doubleP.DoublePRepository;
import com.example.pendulumtestjava.fragments.savingsFragment.savedObject.PendulumsRepository;
import com.example.pendulumtestjava.fragments.savingsFragment.savedObject.SavePendulumModel;
import com.example.pendulumtestjava.fragments.savingsFragment.singleP.SinglePObject;
import com.example.pendulumtestjava.fragments.savingsFragment.singleP.SinglePRepository;
import com.github.clans.fab.FloatingActionButton;

import java.util.List;

public class DbViewModel extends AndroidViewModel {

    private SinglePRepository singlePRepository;
    private DoublePRepository doublePRepository;
    private LiveData<List<SavePendulumModel>> allPendulums;

    public DbViewModel(@NonNull Application application) {
        super(application);

        singlePRepository = new SinglePRepository(application);
        doublePRepository = new DoublePRepository(application);
        PendulumsRepository pendulumsRepository = new PendulumsRepository(application);

        allPendulums = pendulumsRepository.getAllPendulums();
    }

    public void insertSingleP(SinglePObject pendulum)
    {
        singlePRepository.insertSinglePendulum(pendulum);
    }

    public void insertDoubleP(DoublePObject pendulum)
    {
        doublePRepository.insertDoublePendulum(pendulum);
    }

    public void deleteSingleP(SinglePObject pendulum)
    {
        singlePRepository.deleteSinglePendulum(pendulum);
    }

    public void deleteDoubleP(DoublePObject pendulum)
    {
        doublePRepository.deleteDoublePendulum(pendulum);
    }

    public SinglePObject getSinglePendulum(int id)
    {
        return singlePRepository.getSinglePendulum(id);
    }

    public  DoublePObject getDoublePendulum(int id)
    {
        return  doublePRepository.getDoublePendulum(id);
    }

    private void installSinglePendulum(SinglePObject pendulum)
    {
        singlePRepository.installSinglePendulum(pendulum);
    }

    private void installDoublePendulum(DoublePObject pendulum)
    {
        doublePRepository.installDoublePendulum(pendulum);
    }

    public void deleteAllSinglePendulums()
    {
        singlePRepository.deleteAllSinglePendulum();
    }

    public void deleteAllDoublePendulums()
    {
        doublePRepository.deleteAllDoublePendulum();
    }

    public LiveData<List<SavePendulumModel>> getAllPendulums()
    {
        return allPendulums;
    }

    public void deleteAllPendulums() {
        singlePRepository.deleteAllSinglePendulum();
        doublePRepository.deleteAllDoublePendulum();
    }

    public void openPendulum(PendulumAdapter adapter, FragmentActivity activity) {
        adapter.setOnItemClickListener(pendulum -> {
            if (pendulum.getType().equals("Single")) {
                SinglePObject p = getSinglePendulum(pendulum.getId());
                Intent intent = new Intent(activity, SinglePendulumView.class);

                installSinglePendulum(p);

                String type = "single";
                intent.putExtra("path", type);
                activity.startActivity(intent);
            }
            else if (pendulum.getType().equals("Double")) {
                DoublePObject p = getDoublePendulum(pendulum.getId());
                Intent intent = new Intent(activity, DoublePendulumView.class);

                installDoublePendulum(p);

                String type = "double";
                intent.putExtra("path", type);
                activity.startActivity(intent);
            }
        });
    }
}
