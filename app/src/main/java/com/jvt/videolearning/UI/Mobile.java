package com.jvt.videolearning.UI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.jvt.videolearning.Common;
import com.jvt.videolearning.R;
import com.jvt.videolearning.databinding.FragmentMobileBinding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Mobile extends Fragment {
    private FragmentMobileBinding binding;
    private FirebaseAuth mAuth;
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());
    //final FragmentManager fm = getChildFragmentManager();
    public static Fragment activeFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMobileBinding.inflate(inflater,container,true);
        View view = binding.getRoot();
        mAuth = FirebaseAuth.getInstance();
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(binding.mobileNumber.getText().toString())) {
                    Toast.makeText(getContext(), "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
                } else {
                    String code = "+91";
                    String phone = code + binding.mobileNumber.getText().toString();
                    Common.phone = phone;
                    Fragment details = new OTP();
                    loadFragment(details,"OTP");

                }
            }
        });
        return view;
    }
    @SuppressLint("StaticFieldLeak")
    private void loadFragment(Fragment fragment, String tag)
    {
        executorService.execute(() -> {
            if (fragment != null) {

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment, tag).addToBackStack(tag).commit();

            }
            handler.post(() -> {
                activeFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            });
        });
    }
}