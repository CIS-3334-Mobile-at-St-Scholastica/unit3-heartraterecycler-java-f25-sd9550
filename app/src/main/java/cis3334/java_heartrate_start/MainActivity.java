package cis3334.java_heartrate_start;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editTextPulse;
    EditText editTextAge;
    EditText editTextDisplay;           // used to display the heart rates from the database
    // TODO: In Unit 5 will will replace the editText with a RecycleView
    Button buttonInsert;
    MainViewModel mainViewModel;
    RecyclerView recyclerView;
    HeartrateAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        editTextAge = findViewById(R.id.editTextAge);
        editTextPulse = findViewById(R.id.editTextPulse);
        editTextDisplay = findViewById(R.id.editTextDisplay);
        recyclerView = findViewById(R.id.recyclerView);

        adapter = new HeartrateAdapter(getApplication(), mainViewModel);

        List<Heartrate> testData = new ArrayList<>();
        testData.add(new Heartrate(72, 25));
        testData.add(new Heartrate(85, 30));
        testData.add(new Heartrate(120, 40));
        adapter.setHeartrates(testData);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        //recyclerView.setBackgroundColor(Color.RED); // DEBUG: Set the background color of the RecyclerView for testing purposes

        setupInsertButton();            // Set up the OnClickListener for the insert button
        setupLiveDataObserver();
    }

    private void setupLiveDataObserver() {
        // Create the observer for the list of heart rates
        mainViewModel.getAllHeartrates().observe(this, new Observer<>() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onChanged(@Nullable List<Heartrate> allHeartrates) {
                // SAFE null check instead of assert
                if (allHeartrates == null) {
                    Log.d("CIS 3334", "LiveData Observer -- Heartrates list is NULL");
                    editTextDisplay.setText("No heart rates found (null)");
                    return;
                }

                Log.d("CIS 3334", "MainActivity -- LiveData Observer -- Number of Heartrates = " + allHeartrates.size());
                editTextDisplay.setText(String.format("Number of heart rates = %d", allHeartrates.size()));

//                // DEBUG: Check what's in the list
//                for (Heartrate hr : allHeartrates) {
//                    Log.d("CIS 3334", "Heartrate: " + hr.toString());
//                }

                // Pass the observed data to the adapter
                adapter.setHeartrates(allHeartrates);
            }
        });
    }

    /**
     *  Set up the Insert Heartrate button so it adds a new heart rate reading to the database
     */
    private void setupInsertButton() {
        buttonInsert = findViewById(R.id.buttonInsert);
        buttonInsert.setOnClickListener(v -> {
            Integer pulse = Integer.parseInt(editTextPulse.getText().toString());
            Integer age = Integer.parseInt(editTextAge.getText().toString());
            mainViewModel.insert(pulse, age);
        });
    }
}