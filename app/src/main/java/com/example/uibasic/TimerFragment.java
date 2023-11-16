package com.example.uibasic;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class TimerFragment extends Fragment {

    private ProgressBar progressBar;
    private TextView progressText;
    private Button startButton;
    private CountDownTimer pomodoroTimer;
    private boolean isRunning = false;
    private EditText repetitionEditText;
    private int repetitions;
    private TextView timerStatusText;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        progressBar = view.findViewById(R.id.progress_bar);
        progressText = view.findViewById(R.id.progress_text);
        startButton = view.findViewById(R.id.startButton);
        repetitionEditText = view.findViewById(R.id.repetitionEditText);
        timerStatusText = view.findViewById(R.id.timerStatusText);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRunning) {
                    // Pause or stop the timer
                    pomodoroTimer.cancel();
                    isRunning = false;
                    startButton.setText("開始計時");
                } else {
                    // Start or resume the timer
                    repetitions = Integer.parseInt(repetitionEditText.getText().toString());
                    startPomodoroTimer();
                }
            }
        });

        return view;
    }

    private void startPomodoroTimer() {
        pomodoroTimer = new CountDownTimer(25 * 60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update the circular progress bar
                int progress = (int) ((25 * 60 * 1000 - millisUntilFinished) * 100 / (25 * 60 * 1000));
                progressBar.setProgress(progress);

                // Update the UI with the remaining time
                int minutes = (int) (millisUntilFinished / 1000) / 60;
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                progressText.setText(String.format("%02d:%02d", minutes, seconds));

                timerStatusText.setText("工作中");
            }

            @Override
            public void onFinish() {
                // Pomodoro session is over; handle break session or other logic
                Toast t = Toast.makeText(requireContext(), "開始休息!", Toast.LENGTH_SHORT);
                t.show();
                startBreakTimer();
            }
        };

        pomodoroTimer.start();
        isRunning = true;
        startButton.setText("停止計時");
    }

    private void startBreakTimer() {
        pomodoroTimer = new CountDownTimer(5 * 60 * 1000, 1000) { // 5 minutes break timer (adjust as needed)
            @Override
            public void onTick(long millisUntilFinished) {
                // Update the circular progress bar for the break timer
                int progress = (int) ((5 * 60 * 1000 - millisUntilFinished) * 100 / (5 * 60 * 1000));
                progressBar.setProgress(progress);

                // Update the UI with the remaining time
                int minutes = (int) (millisUntilFinished / 1000) / 60;
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                progressText.setText(String.format("%02d:%02d", minutes, seconds));

                timerStatusText.setText("休息中");
            }

            @Override
            public void onFinish() {
                // Break session is over; you can either stop the timer or handle the next work session
                if(repetitions > 1){
                    Toast t = Toast.makeText(requireContext(), "開始工作!", Toast.LENGTH_SHORT);
                    t.show();
                    repetitions--;
                    startPomodoroTimer();
                }
                else {
                    Toast t = Toast.makeText(requireContext(), "本次工作完成!", Toast.LENGTH_SHORT);
                    t.show();
                    isRunning = false;
                    startButton.setText("開始計時");
                    timerStatusText.setText("閒置中");
                }
            }
        };

        pomodoroTimer.start();
        isRunning = true;
        startButton.setText("停止計時");
    }
}