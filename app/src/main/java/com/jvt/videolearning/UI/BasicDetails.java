package com.jvt.videolearning.UI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jvt.videolearning.Common;
import com.jvt.videolearning.HomeActivity;
import com.jvt.videolearning.Model.UserModel;
import com.jvt.videolearning.R;
import com.jvt.videolearning.databinding.FragmentBasicDetailsBinding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BasicDetails extends Fragment {
    private FragmentBasicDetailsBinding binding;
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());
    //final FragmentManager fm = getChildFragmentManager();
    public static Fragment activeFragment;
    String name, email, address;
    String mobileNumber, emailAddress, homeAddress, fullName, profilePic, userId;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBasicDetailsBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        binding.submit.setOnClickListener(view1 -> {
            name = binding.name.getText().toString();
            email = binding.email.getText().toString();
            address = binding.address.getText().toString();
            if (name.isEmpty()){
                Toast.makeText(getContext(), "Enter full name.", Toast.LENGTH_SHORT).show();
            }else if (email.isEmpty()){
                Toast.makeText(getContext(), "Enter email.", Toast.LENGTH_SHORT).show();
            }else if (address.isEmpty()){
                Toast.makeText(getContext(), "Enter address.", Toast.LENGTH_SHORT).show();
            }else {
                mobileNumber = Common.phone;
                userId = firebaseAuth.getCurrentUser().getUid();
                profilePic = "";
                fullName = name;
                homeAddress = address;
                emailAddress = email;
                UserModel userModel = new UserModel(mobileNumber, emailAddress, homeAddress, fullName, profilePic, userId);
                databaseReference.child("Users").child(userId).setValue(userModel);
                Toast.makeText(getContext(), "Logged in.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), HomeActivity.class));
                getActivity().finish();
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