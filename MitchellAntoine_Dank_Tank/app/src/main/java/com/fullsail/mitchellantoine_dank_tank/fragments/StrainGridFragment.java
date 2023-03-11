package com.fullsail.mitchellantoine_dank_tank.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fullsail.mitchellantoine_dank_tank.R;
import com.fullsail.mitchellantoine_dank_tank.object.StrainListener;
import com.fullsail.mitchellantoine_dank_tank.object.Strains;

import java.util.ArrayList;


public class StrainGridFragment extends Fragment implements AdapterView.OnItemClickListener {

    public static final String TAG = "StrainGridFragment";
    StrainListener mListener;
    GridView gridView;

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

        gridView = view.findViewById(R.id.strain_grid);
        gridView.setAdapter(new StrainGridAdapter(requireContext(), 0, mListener.getStrains()));
        gridView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // ToDo: Open Detailed View

    }

    public static class StrainGridAdapter extends ArrayAdapter<Strains> {

        public StrainGridAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Strains> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View gridItemView = convertView;
            if (gridItemView == null) {
                // Layout Inflater inflates each item to be displayed in GridView.
                gridItemView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_grid_view, parent, false);
            }

            Strains strains = getItem(position);
            ImageView iv = gridItemView.findViewById(R.id.grid_item_imageView);
            iv.setImageURI(Uri.parse(strains.getImageUrl()));

            TextView tv = gridItemView.findViewById(R.id.strain_name_textView);
            tv.setText(strains.getName());

            return gridItemView;
        }
    }
}
