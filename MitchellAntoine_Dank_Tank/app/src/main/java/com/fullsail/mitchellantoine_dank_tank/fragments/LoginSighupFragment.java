package com.fullsail.mitchellantoine_dank_tank.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fullsail.mitchellantoine_dank_tank.R;


public class LoginSighupFragment extends Fragment {

    public static final String TAG = "LoginSighupFragment";

    public LoginSighupFragment() {
        // Required empty public constructor
    }

    public static LoginSighupFragment newInstance() {
        return new LoginSighupFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_sighup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}