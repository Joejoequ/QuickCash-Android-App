package com.example.project1.ui.mytask;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.project1.R;

public class MyTaskFragment extends Fragment {

    private MyTaskViewModel MytaskViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MytaskViewModel =
                new ViewModelProvider(this).get(MyTaskViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mytask, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        MytaskViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }
}