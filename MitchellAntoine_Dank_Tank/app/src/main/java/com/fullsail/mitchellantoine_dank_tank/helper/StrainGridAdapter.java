package com.fullsail.mitchellantoine_dank_tank.helper;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.fullsail.mitchellantoine_dank_tank.R;
import com.fullsail.mitchellantoine_dank_tank.object.Strains;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StrainGridAdapter extends BaseAdapter implements Filterable {
    private static final String TAG = "StrainGridAdapter";
    private final ArrayList<Strains> strainsArray;
    private ArrayList<Strains> strainsFiltered;
    CustomFilter filter;

    private final Context mContext;

    public StrainGridAdapter(Context _context, ArrayList<Strains> _strains) {
        this.strainsArray = _strains;
        this.mContext = _context;
        this.strainsFiltered = strainsArray;
    }

    @Override
    public int getCount() {
        Log.i(TAG, "getCount: " + strainsArray.size());
        return strainsFiltered.size();
    }

    @Override
    public Object getItem(int position) {
        return strainsFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View gridItemView = convertView;
        if (gridItemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            gridItemView = LayoutInflater.from(mContext).inflate(R.layout.fragment_grid_view, parent, false);
        }

        ImageView iv = gridItemView.findViewById(R.id.grid_item_imageView);
        Picasso.get()
                .load(strainsFiltered.get(position).getImageUrl())
                .resize(400, 400)
                .centerCrop()
                .into(iv);

        TextView tv = gridItemView.findViewById(R.id.strain_name_textView);
        tv.setText(strainsFiltered.get(position).getName());

        Log.i(TAG, "getView: " + strainsFiltered.get(position).getName() + "\n" + strainsFiltered.size());

        return gridItemView;
    }

    @Override
    public Filter getFilter() {

        if (filter == null) {
            filter = new CustomFilter();
        }

        Log.i(TAG, "getFilter: " + filter);

        return filter;
    }

    // Inner class
    class CustomFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();

            if (constraint != null) {
                if (constraint.length() > 0) {
                    // Constraint to lowercase
                    constraint = constraint.toString().toLowerCase();

                    ArrayList<Strains> filters = new ArrayList<>();

                    // Filtering
                    for (int i = 0; i < strainsArray.size(); i++) {
                        if (strainsArray.get(i).getName().toLowerCase().contains(constraint)) {
                            Strains s = new Strains(strainsArray.get(i).getName(), strainsArray.get(i).getImageUrl());
                            filters.add(s);
                        }
                    }

                    results.values = filters;
                    results.count = filters.size();
                }else {

                    results.values = strainsFiltered;
                }
                Log.i(TAG, "Filtering Results Count: " + results.count + " Results Value: " + results.values);
            }
            return results;
        }

        @Override
        @SuppressWarnings("unchecked")
        protected void publishResults(CharSequence constraint, FilterResults results) {

            strainsFiltered = (ArrayList<Strains>) results.values;
            Log.i(TAG, "publishResults: " + strainsFiltered.size());

            notifyDataSetChanged();
        }
    }


}






