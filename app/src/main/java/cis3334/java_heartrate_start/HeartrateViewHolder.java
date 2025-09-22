package cis3334.java_heartrate_start;

import android.util.Log;
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
        // Debug: Check if views are found
        if (textViewPulse == null) Log.e("CIS 3334", "textViewPulse is null!");
        if (textViewHeartRate == null) Log.e("CIS 3334", "textViewHeartRate is null!");
        if (textViewDescription == null) Log.e("CIS 3334", "textViewDescription is null!");
    }
}
