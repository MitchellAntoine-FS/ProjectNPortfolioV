package com.fullsail.mitchellantoine_dank_tank.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fullsail.mitchellantoine_dank_tank.R;
import com.fullsail.mitchellantoine_dank_tank.object.LogInListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginFragment extends Fragment {
    public static final String TAG = "LoginFragment.TAG";
    private FirebaseAuth mAuth;
    EditText etEmail, etPassword;
    LogInListener mListener;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof LogInListener){
            mListener = (LogInListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Instantiate objects
        mAuth = FirebaseAuth.getInstance();

        // Get User email entry
        etEmail = view.findViewById(R.id.email_login_entry);
        String email = String.valueOf(etEmail.getText());

        if (TextUtils.isEmpty(email) || !email.contains("@") || !email.contains(".com")) {
            etEmail.setError("Required.");
        }else {
            etEmail.setError(null);
        }

        // Get user password entry
        etPassword = view.findViewById(R.id.password_login_entry);
        String password = String.valueOf(etPassword.getText());

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Required.");
        }else {
            etPassword.setError(null);
        }

        // Sign in with email and password
        signInWithEmailPassword(email, password);

    }

    private void signInWithEmailPassword(String email, String password) {
        Log.i(TAG, "signInWithEmailPassword: " + email);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success
                            Log.d(TAG, "signInWithEmail: success");
                            Toast.makeText(getContext(), "Logged In.", Toast.LENGTH_SHORT).show();

                            mListener.closeLogIn();

                        }else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}
