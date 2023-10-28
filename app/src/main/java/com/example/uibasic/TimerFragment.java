package com.example.uibasic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TimerFragment extends Fragment {

    private ProgressBar progressBar;
    private TextView progressText;
    int i = 0;

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        // Find the ProgressBar and TextView within the inflated view
        progressBar = view.findViewById(R.id.progress_bar);
        progressText = view.findViewById(R.id.progress_text);

        // Timer
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Set the limitations for the numeric
                // text under the progress bar
                if (i <= 100) {
                    progressText.setText("" + i);
                    progressBar.setProgress(i);
                    i++;
                    handler.postDelayed(this, 600);
                } else {
                    handler.removeCallbacks(this);
                }
            }
        }, 200);

        return view;
    }
}