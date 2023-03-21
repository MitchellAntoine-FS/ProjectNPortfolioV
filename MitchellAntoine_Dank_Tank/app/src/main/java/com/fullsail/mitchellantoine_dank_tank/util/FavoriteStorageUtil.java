package com.fullsail.mitchellantoine_dank_tank.util;

import android.content.Context;
import android.util.Log;

import com.fullsail.mitchellantoine_dank_tank.object.Strains;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FavoriteStorageUtil {

    public static final String FILE_NAME_FAV = "favorite.dat";

    public static void saveStrain(Context _context, Strains _strain) {

        ArrayList<Strains> strains = loadStrains(_context);

            strains.add(_strain);

            saveStrains(_context, strains);
    }

    public static void deleteStrain(Context _context, int _strain) {

        ArrayList<Strains> strains = loadStrains(_context);

        Log.i("FAV", "deleteStrain: " + _strain);

        strains.remove(_strain);

        saveStrains(_context, strains);
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Strains> loadStrains(Context context) {

        ArrayList<Strains> strains = new ArrayList<>();

        try {
            FileInputStream fis = context.openFileInput(FILE_NAME_FAV);
            ObjectInputStream ois = new ObjectInputStream(fis);
            strains = (ArrayList<Strains>) ois.readObject();
            ois.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

        return strains;
    }

    private static void saveStrains(Context _context, ArrayList<Strains> _strains) {
        try {
            FileOutputStream fos = _context.openFileOutput(FILE_NAME_FAV, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(_strains);
            oos.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
