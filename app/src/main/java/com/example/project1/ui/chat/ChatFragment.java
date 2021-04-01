package com.example.project1.ui.chat;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.project1.R;

public class ChatFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                                ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_chat, container, false);
        return root;
    }
}


