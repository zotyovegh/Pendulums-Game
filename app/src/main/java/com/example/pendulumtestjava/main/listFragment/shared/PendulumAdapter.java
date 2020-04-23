package com.example.pendulumtestjava.main.listFragment.shared;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pendulumtestjava.R;

import java.util.ArrayList;
import java.util.List;

public class PendulumAdapter extends RecyclerView.Adapter<PendulumAdapter.PendulumHolder> {
    private List<SaveObjectModel> pendulums = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public PendulumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_pendulum_item, parent, false);
        return new PendulumHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PendulumHolder holder, int position) {
        SaveObjectModel currentPendulum = pendulums.get(position);
        holder.timeStamp.setText(currentPendulum.getTimeStamp());
        holder.type.setText(currentPendulum.getType());

    }

    @Override
    public int getItemCount() {
        return pendulums.size();
    }

    public void setPendulums(List<SaveObjectModel> pendulums)
    {
        this.pendulums = pendulums;
        notifyDataSetChanged();
    }

    public SaveObjectModel getPendulumAt(int position)
    {
        return pendulums.get(position);
    }

    class PendulumHolder extends RecyclerView.ViewHolder {
        private TextView timeStamp;
        private TextView type;

        public PendulumHolder(View itemView)
        {
            super(itemView);
            timeStamp = itemView.findViewById(R.id.time_stamp);
            type = itemView.findViewById(R.id.type);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(pendulums.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(SaveObjectModel pendulum);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.listener = listener;
    }
}
