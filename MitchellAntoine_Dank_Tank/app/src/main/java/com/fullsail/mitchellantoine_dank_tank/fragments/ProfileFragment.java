package com.fullsail.mitchellantoine_dank_tank.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.ListFragment;

import com.fullsail.mitchellantoine_dank_tank.ProfileActivity;
import com.fullsail.mitchellantoine_dank_tank.R;
import com.fullsail.mitchellantoine_dank_tank.object.ProfileListener;
import com.fullsail.mitchellantoine_dank_tank.object.Strains;
import com.fullsail.mitchellantoine_dank_tank.util.FavoriteStorageUtil;
import com.fullsail.mitchellantoine_dank_tank.util.ImageStorageUtility;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class ProfileFragment extends ListFragment {
    public static final String TAG = "ProfileFragment";

    ProfileListener mListener;
    FirebaseUser user;
    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ProfileListener) {
            mListener = (ProfileListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvUserName = view.findViewById(R.id.users_name);

        user = FirebaseAuth.getInstance().getCurrentUser();

        tvUserName.setText(Objects.requireNonNull(user).getDisplayName());

        ImageView iv = view.findViewById(R.id.profile_image);

        // Get image file reference
        File imageFile = ImageStorageUtility.getImageFileReference(requireActivity(),
                ProfileActivity.IMAGE_NAME, ProfileActivity.IMAGE_FOLDER);
        if (imageFile.exists()) {

            // Get image uri
            Uri imageUri = FileProvider.getUriForFile(requireActivity(), getString(R.string.authority), imageFile);
            // Assign image to profile
            iv.setImageURI(imageUri);
        }
        refresh();

    }

    public void refresh() {
        ArrayList<Strains> favStrains = FavoriteStorageUtil.loadStrains(getActivity());

        if (favStrains != null){
            setListAdapter(new StrainAdapter(getActivity(), favStrains));
        }
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Strains strain = (Strains) l.getAdapter().getItem(position);

        if (mListener != null) {

            mListener.getStrainPosition(strain, position);
        }

    }

    private static class StrainAdapter extends BaseAdapter {

        private final ArrayList<Strains> mStrains;
        private final Context mContext;

        public StrainAdapter(Context context, ArrayList<Strains> _strains) {

            this.mStrains = _strains;
            this.mContext = context;
        }

        @Override
        public int getCount() {
            return mStrains.size();
        }

        @Override
        public Object getItem(int position) {
            return mStrains.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressLint("InflateParams")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_layout, null);
            }

            Strains strains = (Strains) getItem(position);

            ImageView ivStrain = convertView.findViewById(R.id.profile_list_imageView);
            Picasso.get()
                    .load(strains.getImageUrl())
                    .resize(400, 400)
                    .centerCrop()
                    .into(ivStrain);

            TextView tvName = convertView.findViewById(R.id.profile_strain_name);
            tvName.setText(strains.getName());

            TextView tvType = convertView.findViewById(R.id.profile_strain_type);
            tvType.setText(strains.getType());

            return convertView;
        }
    }
}
