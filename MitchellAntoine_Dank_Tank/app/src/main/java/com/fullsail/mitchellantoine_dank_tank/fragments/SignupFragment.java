package com.fullsail.mitchellantoine_dank_tank.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.fullsail.mitchellantoine_dank_tank.R;
import com.fullsail.mitchellantoine_dank_tank.object.SignupListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupFragment extends Fragment {
    public static final String TAG = "SignupFragment";
    private FirebaseAuth mAuth;
    SignupListener mListener;

    public SignupFragment() {
        // Required empty public constructor
    }

    public static SignupFragment newInstance() {

        return new SignupFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SignupListener) {
            mListener = (SignupListener) context;
        }
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        EditText etFirstNam = view.findViewById(R.id.first_name_entry);
        String firstName = etFirstNam.getText().toString();

        if (TextUtils.isEmpty(firstName)) {
            etFirstNam.setError("Required.");
        }else {
            etFirstNam.setError(null);
        }

        EditText etLastNam = view.findViewById(R.id.last_nam_entry);
        String lastName = etLastNam.getText().toString();

        if (TextUtils.isEmpty(lastName)) {
            etLastNam.setError("Required.");
        }else {
            etLastNam.setError(null);
        }

        EditText etEmail = view.findViewById(R.id.signup_email_entry);
        String email = etEmail.getText().toString();

        if (TextUtils.isEmpty(email) || !email.contains("@") || !email.contains(".com")) {
            etEmail.setError("Required.");
        }else {
            etEmail.setError(null);
        }

        EditText etPassword = view.findViewById(R.id.signup_pwd_entry);
        String pwd = etPassword.getText().toString();

        if (TextUtils.isEmpty(pwd) || (pwd.toCharArray().length <= 9) ) {
            etPassword.setError("Required.");
        }else {
            etPassword.setError(null);
        }
        
        createAccount(email, pwd);

    }

    private void createAccount(String email, String pwd) {

        mAuth.createUserWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign up success
                            Log.d(TAG, "createUserWithEmail: success");
                            Toast.makeText(getContext(), "Account Created", Toast.LENGTH_SHORT).show();
                            mListener.closeSignup();

                        } else {
                            // If sign up fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail: failure", task.getException());
                            Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}