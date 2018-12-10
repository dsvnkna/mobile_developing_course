package lab.dsvnkna.mobile_lab;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.google.gson.Gson;

import java.util.ArrayList;

import lab.dsvnkna.mobile_lab.activities.MainActivity;
import lab.dsvnkna.mobile_lab.entities.Product;
import lab.dsvnkna.mobile_lab.entities.ProductList;
import lab.dsvnkna.mobile_lab.fragments.ProductDetailsFragment;

import static lab.dsvnkna.mobile_lab.activities.MainActivity.DETAILS;

public class FragmentNavigation {

    private final MainActivity activity;
    private Fragment currentFragment;

    public FragmentNavigation(MainActivity activity) {
        this.activity = activity;
    }

    public void setFragment(final Fragment fragment) {
        activity.getSupportFragmentManager().
                beginTransaction()
                .replace(R.id.frame_layout_container, fragment)
                .addToBackStack(null)
                .commit();
        currentFragment = fragment;
    }

    public Product getArguments() {
        if (currentFragment instanceof ProductDetailsFragment) {
            final Bundle arguments = currentFragment.getArguments();
            if (arguments != null) {
                return new Gson().fromJson(arguments.getString(DETAILS),
                        Product.class);
            }
        }
        return null;
    }

    public void setArguments(final Product imageItem) {
        final ProductDetailsFragment fragment = new ProductDetailsFragment();
        final Bundle bundle = new Bundle();
        bundle.putString(DETAILS, new Gson().toJson(imageItem));
        fragment.setArguments(bundle);
        setFragment(fragment);
    }
}
