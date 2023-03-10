package com.fullsail.mitchellantoine_dank_tank.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fullsail.mitchellantoine_dank_tank.LoginActivity;
import com.fullsail.mitchellantoine_dank_tank.R;
import com.fullsail.mitchellantoine_dank_tank.SignUpActivity;


public class LoginSighupFragment extends Fragment {

    public static final String TAG = "LoginSighupFragment";
    Button login, signup;

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
        return inflater.inflate(R.layout.fragment_login_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        login = (Button) view.findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(requireContext(), LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        signup = (Button) view.findViewById(R.id.signup_button);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent = new Intent(requireContext(), SignUpActivity.class);
                startActivity(signupIntent);
            }
        });

    }
}