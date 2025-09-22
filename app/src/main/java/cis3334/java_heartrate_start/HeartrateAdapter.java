package cis3334.java_heartrate_start;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class HeartrateAdapter extends RecyclerView.Adapter<HeartrateViewHolder> {

    private List<Heartrate> heartrateList;

    public HeartrateAdapter(Application application, MainViewModel mainViewModel) {

    }

    @SuppressLint("NotifyDataSetChanged")
    public void setHeartrates(List<Heartrate> heartrates) {
        this.heartrateList = heartrates;
        Log.d("CIS 3334", "HeartrateAdapter -- setHeartrates -- Number of Heartrates = " + heartrateList.size());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HeartrateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.heartrate_row, parent, false);
        return new HeartrateViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HeartrateViewHolder holder, int position) {
        if (heartrateList != null && position < heartrateList.size()) {
            Heartrate heartrate = heartrateList.get(position);

            // Basic formatting first
            holder.textViewPulse.setText(heartrate.pulse + " bpm (Age: " + heartrate.age + ")");
            holder.textViewHeartRate.setText(heartrate.getRangeName());
            holder.textViewDescription.setText(heartrate.getRangeDescrtiption());

            Log.d("CIS 3334", "Binding: " + heartrate.toString());

        }
    }

    @Override
    public int getItemCount() {
        return heartrateList != null ? heartrateList.size() : 0;
    }
}
