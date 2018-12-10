package lab.dsvnkna.mobile_lab;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.Map;

import lab.dsvnkna.mobile_lab.entities.Product;

import static lab.dsvnkna.mobile_lab.activities.MainActivity.FAVOURITES;

public class SharedPreferenceStorage {
    private final SharedPreferences mPrefs;

    public SharedPreferenceStorage(Context context) {
        mPrefs = context.getSharedPreferences(FAVOURITES, Context.MODE_PRIVATE);
    }

    public void addToStorage(Product product) {
        mPrefs.edit().putString(product.getName(), new Gson().toJson(product)).apply();
    }

    public void removeFromStorage(Product product) {
        mPrefs.edit().remove(product.getName()).apply();
    }

    public boolean ifStorageContainsItem(String fileName) {
        return mPrefs.contains(fileName);
    }

    public Map<String, ?> getAllFromStorage() {
        return mPrefs.getAll();
    }
}
