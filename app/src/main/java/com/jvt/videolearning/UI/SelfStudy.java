package com.jvt.videolearning.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jvt.videolearning.R;
import com.jvt.videolearning.databinding.FragmentSelfStudyBinding;

public class SelfStudy extends Fragment {
    private FragmentSelfStudyBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentSelfStudyBinding.inflate(inflater,container,false);
       View view = binding.getRoot();
       return view;
    }
}