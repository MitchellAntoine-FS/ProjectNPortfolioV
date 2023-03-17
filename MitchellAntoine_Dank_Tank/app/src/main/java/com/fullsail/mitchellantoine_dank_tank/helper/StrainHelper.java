package com.fullsail.mitchellantoine_dank_tank.helper;


import android.content.Context;
import android.util.Log;

import com.fullsail.mitchellantoine_dank_tank.R;
import com.fullsail.mitchellantoine_dank_tank.object.Strains;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class StrainHelper {


    public static ArrayList<Strains> getStrainsFromJson(String json) {
        ArrayList<Strains> strains = new ArrayList<>();

        try {

            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject mObj = jsonArray.getJSONObject(i);

                String name = mObj.getString("name");
                String description = mObj.getString("description");
                String effect = mObj.getString("effect");
                String imageUrl = mObj.getString("image");
                String helps = mObj.getString("helps");
                String type = mObj.getString("type");

                strains.add(new Strains(name, description, effect, imageUrl, helps, type));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("JSON", "getStrainsFromJson: Array size: " + strains.size());
        return strains;
    }

    public static String getJsonFromUri(Context context) {

        InputStream is = context.getResources().openRawResource(R.raw.strains);

        return new Scanner(is).useDelimiter("\\A").next();
    }


}
