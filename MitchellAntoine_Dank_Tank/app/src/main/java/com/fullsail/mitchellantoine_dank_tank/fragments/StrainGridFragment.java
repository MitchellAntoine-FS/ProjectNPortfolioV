package com.fullsail.mitchellantoine_dank_tank.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fullsail.mitchellantoine_dank_tank.R;
import com.fullsail.mitchellantoine_dank_tank.object.StrainListener;
import com.fullsail.mitchellantoine_dank_tank.object.Strains;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


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
        gridView = view.findViewById(R.id.strain_grid);
        gridView.setAdapter(new StrainGridAdapter(requireContext(), 0, mListener.getStrains()));
        gridView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // ToDo: Open Detailed View

    }

    public void searchStrains(MenuItem item) {

        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                (StrainGridAdapter) gridView.getAdapter().getFilter();

                return true;
            }
        });

    }



    public static class StrainGridAdapter extends ArrayAdapter<Strains> {

        private final ArrayList<Strains> strainsArray;
        private ArrayList<Strains> strainsFiltered;

        public StrainGridAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Strains> objects) {
            super(context, resource, objects);

            this.strainsArray = objects;
            this.strainsFiltered = objects;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View gridItemView = convertView;
            if (gridItemView == null) {
                // Layout Inflater inflates each item to be displayed in GridView.
                gridItemView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_grid_view, parent, false);
            }

            ImageView iv = gridItemView.findViewById(R.id.grid_item_imageView);
            Picasso.get()
                    .load(strainsFiltered.get(position).getImageUrl())
                    .resize(400, 400)
                    .centerCrop()
                    .into(iv);

            TextView tv = gridItemView.findViewById(R.id.strain_name_textView);
            tv.setText(strainsFiltered.get(position).getName());

            return gridItemView;
        }

        @NonNull
        @Override
        public Filter getFilter() {

            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {

                    FilterResults filterResults = new FilterResults();

                    if (constraint == null || constraint.length() == 0) {
                        filterResults.count = strainsArray.size();
                        filterResults.values = strainsArray;
                    }else {
                        String searchString = constraint.toString().toLowerCase();
                        List<Strains> resultData = new ArrayList<>();

                        for (Strains strains: strainsArray) {
                            if (strains.getName().contains(searchString) || strains.getType().contains(searchString)) {
                                resultData.add(strains);
                            }

                            filterResults.count = resultData.size();
                            filterResults.values = resultData;
                        }
                    }
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {

                    strainsFiltered = (ArrayList<Strains>) results.values;
                    notifyDataSetChanged();
                }
            };
        }
    }
}
