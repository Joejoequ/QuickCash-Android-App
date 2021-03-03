package com.example.project1.ui.post;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.project1.MainActivity;
import com.example.project1.R;
import com.example.project1.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Before;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class PostFragment extends Fragment {

    private PostViewModel postViewModel;
    private Button selectDate;
    private Button postBtn;
    private String userName;
    private Date workDate;
    private TextView date;
    private DatabaseReference dbTask;
    private TextView statusLabel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        postViewModel =
                new ViewModelProvider(this).get(PostViewModel.class);
        View root = inflater.inflate(R.layout.fragment_post, container, false);

        postViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        dbTask = FirebaseDatabase.getInstance().getReference("Task");
        MainActivity parentActivity = (MainActivity) getActivity();
        userName = parentActivity.getUserName();

        EditText titleEdit = root.findViewById(R.id.taskTitle);
        EditText descriptionEdit = root.findViewById(R.id.taskDescription);
        EditText wageEdit = root.findViewById(R.id.taskWage);


        selectDate = root.findViewById(R.id.selectDate);
        postBtn = root.findViewById(R.id.postBtn);
        date = root.findViewById(R.id.dateView);
        statusLabel = root.findViewById(R.id.postStatus);
        int yearInput, month, day;
        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                DatePickerDialog dateDialog = new DatePickerDialog(getActivity(), DatePickerDialog.THEME_DEVICE_DEFAULT_LIGHT, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        String desc = String.format("%d-%2d-%2d", year, month + 1, day).replace(" ", "0");
                        ;
                        date.setText(desc);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        simpleDateFormat.setTimeZone(TimeZone.getDefault());
                        try {
                            workDate = simpleDateFormat.parse(desc + " 23:59:59");
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

                DatePicker picker = dateDialog.getDatePicker();
                picker.setMinDate(System.currentTimeMillis() - 1000);
                dateDialog.show();


            }
        });


        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEdit.getText().toString().trim();
                String description = descriptionEdit.getText().toString().trim();
                String wage = wageEdit.getText().toString().trim();

                if (!title.isEmpty() && !description.isEmpty() && !wage.isEmpty() && workDate != null) {
                    Task t = new Task(title, description, workDate, Integer.parseInt(wage), userName);
                    dbTask.child(t.getTaskId()).setValue(t);
                } else {
                    Toast.makeText(getContext(), "Please Fill all the Blanks", Toast.LENGTH_SHORT).show();
                    statusLabel.setText("Please Fill all the Blanks");

                }
            }
        });

        return root;
    }


}