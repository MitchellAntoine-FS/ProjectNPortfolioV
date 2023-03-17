package com.fullsail.mitchellantoine_dank_tank.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fullsail.mitchellantoine_dank_tank.R;
import com.fullsail.mitchellantoine_dank_tank.object.DetailsListener;
import com.fullsail.mitchellantoine_dank_tank.object.Strains;
import com.squareup.picasso.Picasso;

public class StrainDetailsFragment extends Fragment {

    public static final String TAG = "StrainDetailsFragment";
    DetailsListener mListener;

    public static StrainDetailsFragment newInstance() {
        return new StrainDetailsFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof DetailsListener) {
            mListener = (DetailsListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_strain_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Strains strain = mListener.getStrain();

        ImageView ivStrainImage = view.findViewById(R.id.details_strain_image);
        Picasso.get()
                .load(strain.getImageUrl())
                .into(ivStrainImage);

        TextView tvStrainName = view.findViewById(R.id.details_strain_name);
        tvStrainName.setText(strain.getName());
        TextView tvEffects = view.findViewById(R.id.details_effects_textView);
        tvEffects.setText(strain.getEffects());
        TextView tvHelps = view.findViewById(R.id.details_helps_textView);
        tvHelps.setText(strain.getHelps());
        TextView tvType = view.findViewById(R.id.details_type_textView);
        tvType.setText(strain.getType());
        TextView tvDesc = view.findViewById(R.id.details_desc_textView);
        tvDesc.setText(strain.getDescription());

    }
}
