package com.example.pendulumtestjava.fragments.savingsFragment.shared;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pendulumtestjava.R;
import com.example.pendulumtestjava.fragments.savingsFragment.savedObject.SavePendulumModel;

public class PendulumAdapter extends ListAdapter<SavePendulumModel, PendulumAdapter.PendulumHolder> {
    private OnItemClickListener listener;

    public PendulumAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<SavePendulumModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<SavePendulumModel>() {
        @Override
        public boolean areItemsTheSame(SavePendulumModel oldItem, SavePendulumModel newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(SavePendulumModel oldItem, SavePendulumModel newItem) {
            return oldItem.getTimeStamp().equals(newItem.getTimeStamp()) &&
                   oldItem.getType().equals(newItem.getType()) &&
                    oldItem.getId() == newItem.getId();
        }
    };

    @NonNull
    @Override
    public PendulumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new PendulumHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PendulumHolder holder, int position) {
        SavePendulumModel currentPendulum = getItem(position);
        holder.timeStamp.setText(currentPendulum.getTimeStamp());
        holder.type.setText(currentPendulum.getType());
    }

    public SavePendulumModel getPendulumAt(int position)
    {
        return getItem(position);
    }

    class PendulumHolder extends RecyclerView.ViewHolder {
        private TextView timeStamp;
        private TextView type;

        public PendulumHolder(View itemView)
        {
            super(itemView);
            timeStamp = itemView.findViewById(R.id.time_stamp);
            type = itemView.findViewById(R.id.type);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(getItem(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(SavePendulumModel pendulum);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.listener = listener;
    }
}
