package cis3334.java_heartrate_start;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HeartrateViewHolder extends RecyclerView.ViewHolder {
    TextView textViewPulse;
    TextView textViewHeartRate;
    TextView textViewDescription;
    public HeartrateViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewPulse = itemView.findViewById(R.id.textViewPulse);
        textViewHeartRate = itemView.findViewById(R.id.textViewHeartRate);
        textViewDescription = itemView.findViewById(R.id.textViewDescription);
    }
}
