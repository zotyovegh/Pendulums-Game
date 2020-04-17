package com.example.pendulumtestjava.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pendulumtestjava.R;

import java.util.ArrayList;

public class SavedPendulumAdapter extends RecyclerView.Adapter<SavedPendulumAdapter.ViewHolder> {
    private ArrayList<TempListItem> items;

    public SavedPendulumAdapter(ArrayList<TempListItem> items) {
        this.items = items;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedPendulumAdapter.ViewHolder holder, int position) {
        holder.timeStamp.setText(items.get(position).getTimeStamp());
        holder.icon.setImageResource(items.get(position).getmIconId());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView timeStamp;
        ImageView icon;

        ViewHolder(View itemView)
        {
            super(itemView);
            timeStamp = itemView.findViewById(R.id.timeStamp);
            icon = itemView.findViewById(R.id.icon);
        }
    }
}
