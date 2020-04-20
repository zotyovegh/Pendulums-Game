package com.example.pendulumtestjava.main.listFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pendulumtestjava.R;
import com.example.pendulumtestjava.singlePendulum.SinglePendulum;

import java.util.ArrayList;
import java.util.List;

public class DbAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SinglePendulumObject> pendulums = new ArrayList<>();
    private OnItemClickListener listener;
    private static int TYPE_SINGLE = 1;
    private static int TYPE_DOUBLE = 2;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType == TYPE_SINGLE)
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_singlep_item, parent, false);
            return new SingleViewHolder(view);
        }

        //IMPLEMENT DOUBLE
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == TYPE_SINGLE){
            SinglePendulumObject currentPendulum = pendulums.get(position);
            ((SingleViewHolder)holder).timeStamp.setText(currentPendulum.getTimeStamp());
        }
        else {
            //DOUBLE
        }
    }


    @Override
    public int getItemCount() {
        return pendulums.size(); //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    }

    @Override
    public int getItemViewType(int position) {
        if (pendulums.size() != 0) {  //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            return TYPE_SINGLE;

        } else {
            return TYPE_DOUBLE;
        }
    }

    public class SingleViewHolder extends RecyclerView.ViewHolder {
        private TextView timeStamp;

        public SingleViewHolder(@NonNull View itemView) {
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
    //LATER CHANGE TO DOUBLE
    public class DoubleViewHolder extends RecyclerView.ViewHolder {
        private TextView timeStamp;

        public DoubleViewHolder(@NonNull View itemView) {
            super(itemView);
            timeStamp = itemView.findViewById(R.id.time_stamp);
        }
    }

    public void setSinglePendulums(List<SinglePendulumObject> pendulums) {
        this.pendulums = pendulums;
        notifyDataSetChanged();
    }

    public SinglePendulumObject getSinglePendulumAt(int position) {
        return pendulums.get(position);
    }

    public interface OnItemClickListener {
        void onItemClick(SinglePendulumObject pendulum);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
