package lab.dsvnkna.mobile_lab.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import lab.dsvnkna.mobile_lab.ApplicationEx;
import lab.dsvnkna.mobile_lab.FragmentNavigation;
import lab.dsvnkna.mobile_lab.R;
import lab.dsvnkna.mobile_lab.fragments.ProductListFragment;

public class MainActivity extends AppCompatActivity {
    public static final String DETAILS = "product_details";
    public static final String FAVOURITES = "favourite_product";

    protected @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        FragmentNavigation manageFragments = new FragmentNavigation(this);
        ApplicationEx.getInstance().setFragmentManager(manageFragments);
        ApplicationEx.getInstance().getFragmentManager().setFragment(new ProductListFragment());
    }
}