package com.fullsail.mitchellantoine_dank_tank.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fullsail.mitchellantoine_dank_tank.R;
import com.fullsail.mitchellantoine_dank_tank.helper.StrainGridAdapter;
import com.fullsail.mitchellantoine_dank_tank.object.StrainListener;
import com.fullsail.mitchellantoine_dank_tank.object.Strains;


public class StrainGridFragment extends Fragment implements AdapterView.OnItemClickListener {

    public static final String TAG = "StrainGridFragment";
    StrainListener mListener;
    GridView gridView = null;

    public static StrainGridFragment newInstance() {
        return new StrainGridFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof StrainListener) {
            mListener = (StrainListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_strain_grid, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated: View Created" );

        refresh();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Open Detailed View
        Strains strains = (Strains) parent.getAdapter().getItem(position);
        if (mListener != null) {
            mListener.getSelectedStrain(strains);
        }

    }

    public void refresh() {

        StrainGridAdapter adapter = new StrainGridAdapter(requireContext(),mListener.getStrains());

        gridView = requireView().findViewById(R.id.strain_grid);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);

    }

}
