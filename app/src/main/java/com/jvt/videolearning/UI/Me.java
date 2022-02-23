package com.jvt.videolearning.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jvt.videolearning.R;
import com.jvt.videolearning.databinding.FragmentMeBinding;

public class Me extends Fragment {
    private FragmentMeBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMeBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }
}