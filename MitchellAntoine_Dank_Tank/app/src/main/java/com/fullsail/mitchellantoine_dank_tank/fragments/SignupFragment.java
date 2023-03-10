package com.fullsail.mitchellantoine_dank_tank.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fullsail.mitchellantoine_dank_tank.R;

public class SignupFragment extends Fragment {
    public static final String TAG = "SignupFragment";

    public SignupFragment() {
        // Required empty public constructor
    }

    public static SignupFragment newInstance() {

        return new SignupFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }
}