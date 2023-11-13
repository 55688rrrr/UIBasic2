package com.example.uibasic;

import static com.example.uibasic.CalendarUtils.daysInMonthArray;
import static com.example.uibasic.CalendarUtils.monthYearFromDate;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import androidx.fragment.app.Fragment;


public class CalendarFragment extends Fragment implements CalendarAdapter.OnItemListener



        {
private TextView monthYearText;
private RecyclerView calendarRecyclerView;

private LocalDate selectedDate;

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {

        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        initWidgets(view);
        CalendarUtils.selectedDate = LocalDate.now();
        setMonthView();

        Button previousButton = view.findViewById(R.id.previousButton);
        Button nextButton = view.findViewById(R.id.nextButton);
        Button weeklyAction = view.findViewById(R.id.weeklyAction);

        previousButton.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        previousMonthAction(v);
        }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        nextMonthAction(v);
        }
        });
        weeklyAction.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        weeklyAction(v);
        }
        });

        return view;
        }

//Implement each method you want to use.
//    public String findViewById(int id);
//    {
//        return appCompatActivity.findViewById(id);
//    }

private void initWidgets(View view)
        {
//        calendarRecyclerView = appCompatActivity.findViewById(R.id.calendarRecyclerView);
//        monthYearText = appCompatActivity.findViewById(R.id.monthYearTV);
        calendarRecyclerView = view.findViewById(R.id.calendarRecyclerView);
        monthYearText = view.findViewById(R.id.monthYearTV);
        }

private void setMonthView()
        {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> daysInMonth = daysInMonthArray(CalendarUtils.selectedDate);
        System.out.println(daysInMonth);
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(requireContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
        }



public void previousMonthAction(View view)
        {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusMonths(1);
        setMonthView();
        }

public void nextMonthAction(View view)
        {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusMonths(1);
        setMonthView();
        }


@Override
public void onItemClick(int position, LocalDate date)
        {
        if(date != null)
        {
        CalendarUtils.selectedDate = date;
        setMonthView();
        }
        }


public void weeklyAction(View view) {
        Intent intent = new Intent(requireContext(), WeekViewActivity.class);
        startActivity(intent);    }
        }
