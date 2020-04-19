package com.example.pendulumtestjava.main.listFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pendulumtestjava.R;

import java.util.ArrayList;
import java.util.List;

public class SinglePAdapter extends RecyclerView.Adapter<SinglePAdapter.SinglePHolder> {

    private List<SinglePendulumObject> pendulums = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public SinglePHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_singlep_item, parent, false);
        return new SinglePHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SinglePHolder holder, int position) {
        SinglePendulumObject currentPendulum = pendulums.get(position);
        holder.timeStamp.setText(currentPendulum.getTimeStamp());
    }

    @Override
    public int getItemCount() {
        return pendulums.size();
    }

    public void setSinglePendulums(List<SinglePendulumObject> pendulums) {
        this.pendulums = pendulums;
        notifyDataSetChanged();
    }

    public SinglePendulumObject getSinglePendulumAt(int position) {
        return pendulums.get(position);
    }

    class SinglePHolder extends RecyclerView.ViewHolder {
        private TextView timeStamp;

        public SinglePHolder(@NonNull View itemView) {
            super(itemView);

            timeStamp = itemView.findViewById(R.id.time_stamp);

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
        void onItemClick(SinglePendulumObject pendulum);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
