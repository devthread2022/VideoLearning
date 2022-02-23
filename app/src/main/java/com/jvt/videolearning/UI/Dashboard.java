package com.jvt.videolearning.UI;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jvt.videolearning.R;
import com.jvt.videolearning.databinding.FragmentDashboardBinding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Dashboard extends Fragment {
    private FragmentDashboardBinding binding;
    FirebaseAuth firebaseAuth;
    String uid;
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());
    //final FragmentManager fm = getChildFragmentManager();
    public static Fragment activeFragment;
    DatabaseReference databaseReference, databaseReference1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference1 = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        uid = firebaseAuth.getCurrentUser().getUid();
        databaseReference.child("Users").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String name = snapshot.child("fullName").getValue().toString();
                    binding.name.setText("Hi, "+name+"!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
                fragmentTransaction.replace(R.id.container, fragment, tag).addToBackStack(tag).commit();

            }
            handler.post(() -> {
                activeFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.container);
            });
        });
    }
}